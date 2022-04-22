package Middle.大礼包;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 官解
public class Solution {
    public static void main(String[] args) {
        System.out.println(0x3f3f3f3f);
    }

    // map:记录所有购物清单和对应价格
    // 因为可能会有一个礼包购买多次的可能，进行提前存储可以节省时间
    Map<List<Integer>, Integer> map = new HashMap<>();

    /**
     * 计算出购物清单需要花的最划算的价格
     *
     * @param price   商品的价格表，总长 n
     * @param special 大礼包的清单，special[i][j]表示礼包i的商品j的数量，special[i][n]表示礼包i的价格，总长 n+1
     * @param needs   购物清单，needs[i]表示商品i需要的数量
     * @return 返回购物清单所需要的最低价格。
     */
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        /*
            过滤掉不需要的大礼包：
            1. 大礼包并没有更实惠；
            2. 大礼包里没有东西。
         */
        List<List<Integer>> filterSpecial = new ArrayList<>();
        int n = price.size();
        for (List<Integer> list : special) {
            int sum = 0;
            int Number = 0;
            for (int i = 0; i < n; i++) {
                Number += list.get(i);
                sum += (list.get(i) * price.get(i));
            }
            if (Number >= 0 && sum > list.get(n)) {
                filterSpecial.add(list);
            }
        }
        return dfs(price, special, filterSpecial, needs, n);
    }

    // 记忆化搜索计算满足购物清单的所需花费的最低价格。
    public int dfs(List<Integer> price, List<List<Integer>> special, List<List<Integer>> filterSpecial, List<Integer> curNeeds, int n) {
        if (!map.containsKey(curNeeds)) {  // 记忆化里面没有这个购物记录对应价格，我们需要重新计算
            int minPrice = 0;
            // 计算不购买任何大礼包的价格
            for (int i = 0; i < n; i++) {
                minPrice += (curNeeds.get(i) * price.get(i));
            }
            // 计算大礼包是否有优惠
            for (List<Integer> curSpecial : filterSpecial) {
                int specialPrice = curSpecial.get(n);
                List<Integer> nextNeeds = new ArrayList<>();
                for (int i = 0; i < n; i++) {  // 计算当前礼包能用掉多少购物清单，把剩下的放进新的购物清单里面
                    if (curSpecial.get(i) > curNeeds.get(i))
                        break;
                    nextNeeds.add(curNeeds.get(i) - curSpecial.get(i));
                }
                if (nextNeeds.size() == n)   // 大礼包可以买
                    // 递归剩下的购物清单
                    minPrice = Math.min(minPrice, dfs(price, special, filterSpecial, nextNeeds, n) + specialPrice);
            }
            // 对当前购物清单需要的最低价格做记录
            map.put(curNeeds, minPrice);
        }
        // 返回当前购物清单的最低价格
        return map.get(curNeeds);
    }
}

// 超低时间复杂度：1ms
class Solution2 {
    int minPrice;
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        minPrice = directlyBuy(price, needs);
        helper(price, special, needs, 0, 0);
        return minPrice;
    }

    private int directlyBuy(List<Integer> price, List<Integer> needs){
        int total = 0;
        int n = price.size();
        for(int i = 0; i < n; i++){
            total += price.get(i) * needs.get(i);
        }
        return total;
    }

    // 判断能不能用这个offer， offer是嵌套在大礼包的某一条offer
    private boolean canUse(List<Integer> offer, List<Integer> needs){
        int n = needs.size();
        for(int i = 0; i < n; i++){
            if(needs.get(i) < offer.get(i)) //需要的东西数量小于礼包里面对应物品的数量（超额了）
                return false;
        }
        return true;
    }

    /**
     * @param price     商品的价格清单表，全程都是不变的。
     * @param special   大礼包价格清单详情表，全程都是不变的。
     * @param needs     购物清单需求表，每购买一个大礼包，都会更新为新的需求。
     * @param used      已经花费的金额，用于和minPrice比较。
     * @param index     目前正在使用第几个大礼包，大礼包的索引。
     */
    // 因为返回的minPrice是全局变量，所以返回空
    private void helper(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int used, int index){
        // ==base case==
        // 1. 花的钱大于你目前能花的最小价格，直接返回回溯，找下去没意义
        if(used >= minPrice) return;
        // 2. 找的第n个物品，已经不能用offer了，就直接不用offer买剩下的，并且更新minPrice
        if(index == special.size()){  // 已经遍历完了所有的大礼包，把剩下的物品直接加入金额里面。
            used += directlyBuy(price, needs);
            if(used < minPrice)
                minPrice = used;
            return;
        }

        List<Integer> offer = special.get(index);
        if(canUse(offer, needs)){
            // 使用这个offer， 并且更新needs需求的个数
            int n = needs.size();
            List<Integer> updateNeed = new ArrayList<>();
            for(int i = 0; i < n; i++){
                updateNeed.add(needs.get(i) - offer.get(i));
            }
            //index可以不变，因为offer可以重复使用，直到不能用，自动跳到else里面index+1
            helper(price, special, updateNeed, used + offer.get(n), index);
        }
        //不能写else：这里相当于保证了这个礼包不用，下个礼包会更好的可能性。
        helper(price, special, needs, used, index + 1);//不能用就index+1找下一个offer（下一个大礼包）
    }
}

// 最低空间消耗：38236kb
class Solution3 {
    int ans = Integer.MAX_VALUE;

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        List<Integer> now = new ArrayList<>();
        for(int i = 0; i < needs.size(); i++) now.add(0);
        dfs(price, special, needs, now, 0);
        return ans;
    }

    public void dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs, List<Integer> now, int cost){
        if(cost > ans) return; // 剪枝1：花费已经大于我们之前计算过的最低价格
        int sum = cost;  // 存储之前的花销
        for(int i = 0; i < needs.size(); i++){
            if(now.get(i) > needs.get(i)) return; // 剪枝2：现在已经持有的某个商品的数量如果大于我们的需求，就超额了，直接回溯
            else{ // 否则计算剩下的商品单独购买的价格之和。
                sum += (needs.get(i) - now.get(i)) * price.get(i);
            }
        }
        ans = Math.min(ans, sum);
        for(int i = 0; i < special.size(); i++){
            for(int j = 0; j < needs.size(); j++){ // 拥有当前礼包后，现有的商品数量now
                now.set(j, now.get(j) + special.get(i).get(j));
            }
            dfs(price, special, needs, now, cost + special.get(i).get(needs.size()));
            for(int j = 0; j < needs.size(); j++){  // 去除当前礼包后，现有的商品数量now
                now.set(j, now.get(j) - special.get(i).get(j));
            }
        }
    }
}

