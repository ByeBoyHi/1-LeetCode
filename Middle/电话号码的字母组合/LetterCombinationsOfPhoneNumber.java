package Middle.电话号码的字母组合;

import java.util.*;

public class LetterCombinationsOfPhoneNumber {
    HashMap<Integer, char[]> map;
    Set<String> ans;
    public LetterCombinationsOfPhoneNumber(){
        ans = new HashSet<>();
        map = new HashMap<>();
        map.put(2,new char[]{'a','b','c'});
        map.put(3,new char[]{'d','e','f'});
        map.put(4,new char[]{'g','h','i'});
        map.put(5,new char[]{'j','k','l'});
        map.put(6,new char[]{'m','n','o'});
        map.put(7,new char[]{'p','q','r','s'});
        map.put(8,new char[]{'t','u','v'});
        map.put(9,new char[]{'w','x','y','z'});
    }
    // 可以重复，反正不取自己里面的就行
    public List<String> letterCombinations(String digits) {
        if (digits==null || digits.length()==0){
            return new ArrayList<>();
        }
        List<char[]> list = new ArrayList<>();
        int len = digits.length();
        for (int i=0; i<len; i++){
            list.add(map.get(Integer.parseInt(String.valueOf(digits.charAt(i)))));
        }
        process(list, 0, new StringBuilder());

        return new ArrayList<>(ans);
    }
    public void process(List<char[]> list, int index, StringBuilder pre){
        if (index==list.size()){
            if (pre.length()==list.size()){
                ans.add(pre.toString());
            }
            return;
        }
        for (char c:list.get(index)){
            pre.append(c);
            process(list, index+1, pre);
            pre.deleteCharAt(pre.length()-1);
        }
    }
}
