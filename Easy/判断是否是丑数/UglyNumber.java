package Easy.判断是否是丑数;

import  java.util.Scanner;
public class UglyNumber {
    /**
     * 一个数字的因数只有2,3,5
     * 则称这个数字为丑数
     * 0不是丑数   1是丑数
     */
    /**
     * num/2/3/5==?   ?/2/3/5==0？
     * 如果能整除2/3/5，除到最后就是1
     * 如果不能整除，则通过return进行退出
     */
    //定义判断是否是丑数的两种方法函数
    //均是静态函数，方便用类名直接调用

    //循环
    public static boolean isUgly(int num){
        while(num!=1) {
            if (num / 2 == 0) {
                num = num / 2;
                // 进行continue判断，如果能整除2，则从循环开始从头再判断一次，就不会走到最后的return进行退出
                // continue只能用于循环中，用来返回当前循环的开头
                continue;
            }
            if (num / 3 == 0) {
                num = num / 3;
                continue;
            }
            if (num / 5 == 0) {
                num = num / 5;
                continue;
            }
            return false;
        }
        // 如果最后通过num==1退出的while循环，则不会执行到return false，然后就会跳出循环，执行下面的return true.
        return true;
    }

    //递归
    public static boolean isUgly1(int num){
        //先判断是否是0或1
        if(num==0) return false;
        if(num==1) return true;
        //然后判断是否能被2/3/5整除，如果可以被整除，则return这个函数本身，再次调用判断(递归)
        if(num/2==0) return isUgly1(num/2);
        if(num/3==0) return isUgly1(num/3);
        if(num/5==0) return isUgly1(num/5);
        // 如果反复调用判断后，最终不等于0/1,则说明这个数字不是丑数，返回false
        return false;
    }

    //同一个类的静态类可以互相调用
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(isUgly(n));
        System.out.println(isUgly1(n));
    }
}