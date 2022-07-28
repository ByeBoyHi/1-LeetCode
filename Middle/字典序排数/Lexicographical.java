package Middle.字典序排数;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Lexicographical {

    public static void main(String[] args) {
        System.out.println("2".compareToIgnoreCase("12"));
    }

    // 暴力破解
    // O(n*log n):红黑树每次调整都是O(log n)，一共有n个节点
    // O(n): 使用了一个红黑树
    public List<Integer> lexicalOrder(int n) {
        TreeSet<String> treeSet = new TreeSet<>(String::compareToIgnoreCase);
        for (int i = 1; i <= n; i++) {
            treeSet.add(i+"");
        }
        List<Integer> ans = new ArrayList<>();
        for (String cur : treeSet){
            ans.add(Integer.parseInt(cur));
        }
        return ans;
    }

    // O(n)时间，O(1)空间
    /*
        假设当前数字是Number
        1. 如果number*10<n，那么number*10就是下一个数字
        2. 否则，如果（number mod 10==9 或者 number+1>n），那么此时的number就是当前位的最后一个数字
            此时需要回滚到上一位数的数字，直到合格位置，到了合格位置后，让number+1
            因为我们是反着的，所以此时的+1是比其他的数字的字典序更小的，打个比方：
            10000 --rollback--> 1000 --+1--> 1001
            这个1001 肯定比 10**更小的字典序，因为我们不必回滚了
     */
    public List<Integer> lexicalOrder2(int n) {
        List<Integer> res = new ArrayList<>();
        int number = 1;
        for (int i = 1; i <= n; i++) {
            res.add(number);
            if(number*10<=n){ // 如果扩大10倍没有越界
                number = number*10;
            }else {  // 越界了，进行回滚
                while (number%10==9 || number+1>n){
                    number = number/10;
                }
                number++;  // 回滚到合适的位置，然后+1
            }
        }
        return res;
    }

    List<Integer> list = new ArrayList<>();
    // 递归
    public void dfs(int start, int n){
        list.add(start);
        for (int i = 0; i <= 9; i++) {  // 尝试加每一个数字
            if (start*10+i>n) break;
            dfs(start*10+i, n);
        }
    }

    // 递归处理每一个数字
    public List<Integer> lexicalOrder3(int n) {
        for (int i = 1; i <= 9; i++) {  // 尝试每一个起点
            dfs(i, n);
        }
        return list;
    }

}

