package Easy.七进制数;

public class Base7 {
    public String convertToBase7(int num) {
        if (num==0) return "0";
        boolean flag = num<0;
        num = Math.abs(num);
        StringBuilder sb = new StringBuilder();
        while (num!=0){
            sb.append(num%7);
            num/=7;
        }
        return (flag?"-":"")+ sb.reverse();
    }

    // 递归
    public String convertBase7(int num){
        if (num==0) return "0";
        else return (num<0?"-":"") +process(Math.abs(num));
    }
    public String process(int num){
        if (num==0) return "";
        else return process(num/7)+num%7;
    }
}
