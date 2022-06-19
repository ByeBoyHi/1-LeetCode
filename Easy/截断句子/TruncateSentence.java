package Easy.截断句子;

public class TruncateSentence {
    public String truncateSentence(String s, int k) {
        String[] strings = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<k; i++){
            sb.append(strings[i]).append(" ");
        }
        return sb.toString().trim();
    }

    // 找字符串的空格，第k个空格就是结束的位置
    public String truncateSentence2(String s, int k) {
        int end = 0;
        int count = 0;
        for (int i=0; i<s.length(); i++){
            if (s.charAt(i)==' '){
                count++;
                if (count==k){
                    end = i;
                    break;
                }
            }
        }
        if (count==k-1){  // 最后如果是取所有的单词，那么count只能走到k-1
            end = s.length();
        }
        return s.substring(0,end);
    }
}
