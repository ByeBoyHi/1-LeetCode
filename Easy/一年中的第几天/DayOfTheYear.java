package Easy.一年中的第几天;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class DayOfTheYear {

    public static void main(String[] args) {
        new DayOfTheYear();
    }

    HashMap<Integer, Integer> map;
    HashMap<Integer, Integer> days;

    public DayOfTheYear() {
        map = new HashMap<>();
        days = new HashMap<>();

        for (int i = 1; i < 13; i++) {
            if (i == 2) {
                map.put(i, map.getOrDefault(i - 1, 0) + 28);
                days.put(i, 28);
            } else if (i < 8) {
                if ((i & 1) == 1) { // 奇数
                    map.put(i, map.getOrDefault(i - 1, 0) + 31);
                    days.put(i, 31);
                } else {
                    map.put(i, map.getOrDefault(i - 1, 0) + 30);
                    days.put(i, 30);
                }
            } else {
                if ((i & 1) == 0) { // 偶数
                    map.put(i, map.getOrDefault(i - 1, 0) + 31);
                    days.put(i, 31);
                } else {
                    map.put(i, map.getOrDefault(i - 1, 0) + 30);
                    days.put(i, 30);
                }
            }
        }
    }

    public int dayOfYear(String date) {
        String[] d = date.split("-");
        int year = Integer.parseInt(d[0]);
        int month = Integer.parseInt(d[1]);
        int day = Integer.parseInt(d[2]);

        if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)) {
            if (month >= 3) {
                return map.get(month) - days.get(month) + day + 1;
            }
        }

        return map.get(month) - days.get(month) + day;
    }

    public int dayOfYear2(String date) {
        int[] count = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] d = date.split("-");
        int year = Integer.parseInt(d[0]);
        int month = Integer.parseInt(d[1]);
        int day = Integer.parseInt(d[2]);
        if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)) {
            count[1]++;
        }
        int ans = 0;
        for (int i=0; i<month-1; i++){
            ans+=count[i];
        }
        return ans+day;
    }
}
