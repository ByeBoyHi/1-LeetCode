package Easy.替换所有的问号;

public class RepeatingCharacters {
    public String modifyString(String s) {
        if (s.length()==1 && s.charAt(0)=='?') return "a";
        char[] chs = s.toCharArray();
        for (int i=0; i<chs.length; i++){
            if (chs[i]=='?'){
                if (i==0){
                    for (int j=0; j<26; j++){
                        if ((char)('a'+j)!=chs[i+1]){
                            chs[i]=(char)('a'+j);
                            break;
                        }
                    }
                }else if (i==chs.length-1){
                    for (int j=0; j<26; j++){
                        if ((char)('a'+j)!=chs[i-1]){
                            chs[i]=(char)('a'+j);
                            break;
                        }
                    }
                }else {
                    for (int j=0; j<26; j++){
                        if ((char)('a'+j)!=chs[i-1]&&(char)('a'+j)!=chs[i+1]){
                            chs[i]=(char)('a'+j);
                            break;
                        }
                    }
                }
            }
        }

        return new String(chs);
    }

    public String modifyString2(String s){
        int n = s.length();
        char[] chs = s.toCharArray();
        for (int i=0; i<n; i++){
            if (chs[i]=='?'){
                for (char ch='a'; ch<='z'; ch++){
                    /*
                     * 在头部的时候，会比较后面的，在尾部的时候会比较前面的
                     * 在中间的时候两个都会比较
                     * 只要有一个相等，都会跳过这次循环
                     */
                    if ((i>0 && ch==chs[i-1]) || (i<n-1 && ch==chs[i+1])){
                        continue;
                    }
                    chs[i] = ch;
                    break;
                }
            }
        }
        return new String(chs);
    }
}
