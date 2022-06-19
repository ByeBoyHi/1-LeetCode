package Middle.如果相邻两个颜色均相同则删除当前颜色;

public class Winner {

    /*
        因为是删除AAA的中间那个A，所以Alice删除A是不会让不相邻的B相邻的，也就是他俩的操作互不影响，那么直接贪心判断谁的操作次数多即可
     */
    public boolean winnerOfGame(String colors) {
        if (colors.length()<3) return false;
        int[] freq = new int[]{0,0};
        char cur = 'C';
        int cnt = 0;
        for (int i=0; i<colors.length(); i++){
            char c = colors.charAt(i);
            if (c!=cur){
                cur = c;
                cnt = 1;
            }else{  // 同一个字符
                cnt++;
                if (cnt>=3){
                    if (c=='A'){
                        freq[0]++;
                    }else {
                        freq[1]++;
                    }
                }
            }
        }

        return freq[0]>freq[1];
    }

    // 滑动窗口：控制窗口大小为3，然后往右活动，记录窗口A和B的个数
    public boolean winnerOfGame2(String colors) {
        if(colors.length()<3) return false;
        int A = 0;
        int B = 0;
        for (int i=0; i<3; i++){
            if (colors.charAt(i)=='A'){
                A++;
            }else {
                B++;
            }
        }
        int operA = 0;
        int operB = 0;
        if (A==3){
            operA++;
        }
        if (B==3){
            operB++;
        }
        for (int i=3; i<colors.length(); i++){
            if (colors.charAt(i)=='A'){
                A++;
            }else {
                B++;
            }
            if (colors.charAt(i-3)=='A'){
                A--;
            }else {
                B--;
            }
            if (A==3){
                operA++;
            }
            if (B==3){
                operB++;
            }
        }
        return operA>operB;
    }

    // 直接记录有多少连续的
    public boolean winnerOfGame3(String colors) {
        if (colors.length()<3) return false;
        char[] chars = (colors+"C").toCharArray();  // 最后加一个结尾元素，用来判断最后一批相同元素个数
        int A = 0;
        int B = 0;
        char c = colors.charAt(0);
        int pre = 0;
        for (int i=1; i<chars.length; i++){
            if (c!=chars[i]) {
                if (i-pre>=3){  // 当前元素和之前元素不一样
                    if (c=='A'){
                        A += i-pre-2;
                    }else {
                        B += i-pre-2;
                    }
                }
                c = chars[i];
                pre = i;
            }
        }
        return A>B;
    }
}
