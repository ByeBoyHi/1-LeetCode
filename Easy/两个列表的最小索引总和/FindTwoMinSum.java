package Easy.两个列表的最小索引总和;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FindTwoMinSum {
    public String[] findRestaurant(String[] list1, String[] list2) {
        // 找出共同的餐厅
        Map<String, Integer> coRe = new HashMap<>();
        for(int i=0; i<list1.length; i++){
            coRe.put(list1[i], i);  // 记录每个餐厅的索引
        }
        int minIndex = Integer.MAX_VALUE;
        List<String> ans = new LinkedList<>();
        for (int i=0; i<list2.length; i++){
            if (coRe.containsKey(list2[i])){
                // 当前索引和
                int cur = i + coRe.get(list2[i]);
                if (minIndex>cur){
                    minIndex = cur;
                    ans.clear();
                    ans.add(list2[i]);
                }else if (minIndex==cur){
                    ans.add(list2[i]);
                }
            }
        }
        // 把集合转换为数组：参数里面穿需要转换的类型
        return ans.toArray(new String[0]);
    }
}
