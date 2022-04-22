package Easy.最短补全词;

import java.util.*;

public class ShortestCompletingWord {
    /*
        1. 暴力破解
        对licensePlate进行留字母取小写
        对words进行长短排序，然后转字符数组挨个判断
     */
    public String shortestCompletingWord(String licensePlate, String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(licensePlate);
        for (int i = 0; i < licensePlate.length(); i++) {
            if (!((licensePlate.charAt(i) >= 'a' && licensePlate.charAt(i) <= 'z')
                    || (licensePlate.charAt(i) >= 'A' && licensePlate.charAt(i) <= 'Z'))) {
                sb.deleteCharAt(sb.indexOf(licensePlate.charAt(i) + ""));
            }
        }
        char[] license = sb.toString().toLowerCase().toCharArray();
        mergeSort(words,0, words.length-1);

        List<Map<Character, Integer>> set = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            char[] temp = words[i].toLowerCase().toCharArray();
            Map<Character, Integer> map = new HashMap<>();
            for (char c : temp) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
            map.put('A', i);
            set.add(map);
        }

        for (Map<Character, Integer> m : set) {
            boolean flag = true;
            for (char c : license) {
                if (m.containsKey(c)) {
                    m.put(c, m.get(c) - 1);
                    if (m.get(c) == 0) {
                        m.remove(c);
                    }
                } else {
                    flag = false;
                }
            }
            if (flag) {
                return words[m.get('A')];
            }
        }


        return "";
    }

    // 归并排序
    public static String[] mergeSort(String[] words, int start, int end) {
        if (start>=words.length || start<0){
            return new String[]{};
        }
        if (start == end) {
            return new String[]{words[start]};
        }

        String[] left = mergeSort(words, start, (end - start) / 2 + start);
        String[] right = mergeSort(words, (end - start) / 2 + start + 1, end);

        String[] all = new String[left.length+right.length];
        int lIndex = 0;
        int rIndex = 0;
        int index = 0;
        while (lIndex<left.length && rIndex<right.length){
            if (left[lIndex].length()<=right[rIndex].length()){
                all[index++] = left[lIndex++];
            } else {
                all[index++] = right[rIndex++];
            }
        }

        while (lIndex<left.length){
            all[index++] = left[lIndex++];
        }

        while (rIndex<right.length){
            all[index++] = right[rIndex++];
        }

        return all;
    }

    // 官解：统计词频
    public String shortestCompletingWord2(String licensePlate, String[] words) {
        int[] cnt = new int[26];
        for (int i = 0; i < licensePlate.length(); ++i) {
            char ch = licensePlate.charAt(i);
            if (Character.isLetter(ch)) {
                ++cnt[Character.toLowerCase(ch) - 'a'];
            }
        }
        int idx = -1;
        for (int i = 0; i < words.length; ++i) {
            int[] c = new int[26];
            for (int j = 0; j < words[i].length(); ++j) {
                char ch = words[i].charAt(j);
                ++c[ch - 'a'];
            }
            boolean ok = true;
            for (int j = 0; j < 26; ++j) {
                if (c[j] < cnt[j]) {
                    ok = false;
                    break;
                }
            }
            if (ok && (idx < 0 || words[i].length() < words[idx].length())) {
                idx = i;
            }
        }
        return words[idx];
    }

    // 三解
    public String shortestCompletingWord3(String licensePlate, String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(licensePlate);
        for (int i = 0; i < licensePlate.length(); i++) {
            if (!((licensePlate.charAt(i) >= 'a' && licensePlate.charAt(i) <= 'z')
                    || (licensePlate.charAt(i) >= 'A' && licensePlate.charAt(i) <= 'Z'))) {
                sb.deleteCharAt(sb.indexOf(licensePlate.charAt(i) + ""));
            }
        }
        char[] license = sb.toString().toLowerCase().toCharArray();
        Arrays.sort(license);
        mergeSort(words, 0, words.length-1);
        for (String s:words){
            char[] cur = s.toCharArray();
            Arrays.sort(cur);
            int i = 0;
            for (int j=0; i<license.length && j<cur.length; j++){
                if (cur[j]==license[i]){
                    i++;
                }
                if (cur[j]>license[i]){
                    break;
                }
            }
            if (i==license.length){
                return words[i];
            }
        }
        return "";
    }


        public static void main(String[] args) {
        System.out.println(Arrays.toString(mergeSort(new String[]{
                "looks", "pest", "stew", "show"
        }, 0, 4)));
    }
}
