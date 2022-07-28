package Easy.赎金信;

import java.util.Arrays;
import java.util.HashMap;

public class RansomNote {

    // StringBuilder：3ms
    public boolean canConstruct(String ransomNote, String magazine) {
        if(magazine == null || magazine.equals("") || magazine.length()<ransomNote.length()){
            return false;
        }

        StringBuilder sb = new StringBuilder(magazine);
        for (int i=0; i<ransomNote.length(); i++){
            int index = sb.indexOf(String.valueOf(ransomNote.charAt(i)));
            if (index==-1){
                return false;
            }else {
                sb.deleteCharAt(index);
            }
        }
        return true;
    }

    // 哈希表：20ms
    public boolean canConstruct2(String ransomNote, String magazine) {
        if (magazine == null || magazine.equals("") || magazine.length() < ransomNote.length()) {
            return false;
        }

        char[] magas = magazine.toCharArray();
        char[] ransoms = ransomNote.toCharArray();
        int rLen = ransomNote.length();


        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : magas) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (int i=0; i<rLen; i++){
            if (map.containsKey(ransoms[i])){
                map.put(ransoms[i], map.get(ransoms[i])-1);
                if (map.get(ransoms[i])==0){
                    map.remove(ransoms[i]);
                }
            }else {
                return false;
            }
        }
        return true;
    }

    // 排序：5ms
    public boolean canConstruct3(String ransomNote, String magazine) {
        if (magazine == null || magazine.equals("") || magazine.length() < ransomNote.length()) {
            return false;
        }
        char[] magas = magazine.toCharArray();
        char[] ransoms = ransomNote.toCharArray();
        Arrays.sort(magas);
        Arrays.sort(ransoms);
        int rLen = ransomNote.length();
        int mLen = magas.length;

        int rIndex = 0;
        int mIndex = 0;
        while (rIndex<rLen && mIndex<mLen){
            if (magas[mIndex] == ransoms[rIndex]){
                rIndex++;
                mIndex++;
            }else if (magas[mIndex]>ransoms[rIndex]){
                return false;
            }else {
                mIndex++;
            }
        }

        return rIndex==rLen;
    }

    // 字母表：因为全是小写字母  1ms
    public boolean canConstruct4(String ransomNote, String magazine) {
        if (magazine == null || magazine.equals("") || magazine.length() < ransomNote.length()) {
            return false;
        }

        char[] chs = new char[26];
        for (int i=0; i<magazine.length(); i++){
            chs[magazine.charAt(i)-'a']++;
        }

        for (int i=0; i<ransomNote.length(); i++){
            int index = ransomNote.charAt(i)-'a';
            if (chs[index]==0){
                return false;
            }else {
                chs[index]--;
            }
        }
        return true;
    }
}
