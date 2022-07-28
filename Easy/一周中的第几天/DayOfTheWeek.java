package Easy.一周中的第几天;

import java.util.*;

public class DayOfTheWeek {

    // 计算这一年到1971年一共有多少天，再把闰年多加一天，
    // 然后再计算这一年已经过去了多少天
    // 因为1971-1-1是星期五，所以+3再%7
    public String dayOfTheWeek(int day, int month, int year) {
        String[] weeks = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int[] months = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        // 年份
        int days = (year-1970)*365;
        boolean b = year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
        for (int i = 1971; i<year; i++){
            if (b){
                days++;
            }
        }
        // 月
        for (int i=0; i<month-1; i++){
            days+=months[i];
        }
        // 日期
        days+=day;

        // 今年是否是闰年
        if (b && month>=3){
            days++;
        }

        return weeks[(days+3)%7];
    }
}
