package Middle.幂集;

import java.util.ArrayList;
import java.util.List;

public class PowerSetLCCI {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs(new ArrayList<>(), nums, 0);
        return ans;
    }
    //dfs、回溯皆可
    public void dfs(List<Integer> cur, int[] nums, int idx){
        List<Integer> temp = new ArrayList<>(cur);
        if (idx==nums.length) {
            ans.add(temp);
            return;
        }
        for (int i=idx; i<nums.length; i++){
            if (i>idx && nums[i]==nums[i-1]) continue;
            temp.add(nums[i]);
            dfs(temp, nums, i+1);
            temp.remove(temp.size()-1);
        }
        ans.add(temp);
    }


    public void dfs2(List<Integer> cur, int[] nums, int idx) {
        if (idx==nums.length){
            // 为了避免地址问题，新建一个列表重新填充
            ans.add(new ArrayList<>(cur));
            return;
        }
        cur.add(nums[idx]);
        dfs2(cur, nums, idx+1);
        cur.remove(cur.size()-1);
        dfs2(cur, nums, idx+1);
    }

    // 理解见图img_1
    public List<List<Integer>> subsets2(int[] nums) {
        // 这个是初始化的容量，便于后面减少扩充次数，但是最开始的时候，里面的size=0的，和容量无关
        List<List<Integer>> res = new ArrayList<>(1 << nums.length);
        //先添加一个空的集合
        res.add(new ArrayList<>());
        for (int num : nums) {
            //每遍历一个元素就在之前子集中的每个集合追加这个元素，让他变成新的子集
            // 这个size是动态变化的，每装填一个元素，大小多1，但是当前循环下是不变的
            int size = res.size();
            for (int i = 0; i < size; i++) {
                //遍历之前的子集，重新封装成一个新的子集
                List<Integer> list = new ArrayList<>(res.get(i));
                //然后在新的子集后面追加这个元素
                list.add(num);
                //把这个新的子集添加到集合中
                res.add(list);
            }
        }
        return res;
    }

    // 进制解————见位运算图
    public static List<List<Integer>> subsets3(int[] nums) {
        //子集的长度是2的nums.length次方，这里通过移位计算
        int length = 1 << nums.length;
        List<List<Integer>> res = new ArrayList<>(length);
        //遍历从0到length中间的所有数字，根据数字中1的位置来找子集
        for (int i = 0; i < length; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                //如果数字i的某一个位置是1，就把数组中对应的数字添加到集合
                if (((i >> j) & 1) == 1)
                    list.add(nums[j]);
            }
            res.add(list);
        }
        return res;
    }

    public static void main(String[] args) {
        PowerSetLCCI p = new PowerSetLCCI();
        System.out.println(p.subsets(new int[]{
                1,2,3
        }));
    }
}
