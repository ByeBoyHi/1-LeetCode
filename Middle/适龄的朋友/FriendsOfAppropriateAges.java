package Middle.适龄的朋友;

import java.util.Arrays;

public class FriendsOfAppropriateAges {

    public static void main(String[] args) {
        System.out.println(numFriendRequests3(new int[]{
//                4, 13, 48, 62, 1, 35, 44, 88, 92, 120
                4,13,48,62,1,35,99,88,120,54,68,44,88,92,120
        }));
    }

    public static int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int ans = 0;
        for (int i = 0; i < ages.length; i++) {
            for (int j = i + 1; j < ages.length; j++) {
                if (ages[j] < rangeAppropriate(ages[i])) {
                    ans++;
                    if (ages[i] == ages[j]) {  // 如果两个相等，那么可以互相发好友申请，因为不会回头判断，所以这里加一下
                        ans++;
                    }
                } else {
                    break;
                }
            }
        }
        return ans;
    }

    // y 是否会向 x 发送好友申请
    // 算出y会发出邀请的合理范围的下界
    public static int rangeAppropriate(int x) {
        return (x - 7) * 2;
    }


    public static int numFriendRequests2(int[] ages) {
        int n = ages.length;
        Arrays.sort(ages);
        int left = 0, right = 0, ans = 0;
        for (int age : ages) {
            if (age < 15) {
                continue;
            }
            while (ages[left] <= 0.5 * age + 7) {  // 过滤掉所有不合格，找到第一个合格的位置
                ++left;
            }
            while (right + 1 < n && ages[right + 1] <= age) {  // 过滤掉所有的合格，找到最后一个合格的位置
                ++right;
            }
            ans += right - left;  // 第一个合格的位置只有当前位置，这里没有加一是因为多算了一个当前位置，所以没必要+1
        }
        return ans;
    }

    // 有点儿问题还没解决
    public static int numFriendRequests3(int[] ages) {
        int ans = 0;
        int[] users = new int[121];
        // 年龄只有1~120岁
        for (int age : ages) {
            users[age]++;
        }
        // 只有大于等于15岁才能加好友
        for (int i=1; i<121; i++){
            if (users[i]!=0) {
                ans += users[i] * (users[i] - 1);
                for (int j = i + 1; j < (i - 7) * 2 && j < 121; j++) {
                    ans += users[j];
                }
            }
        }
        return ans;
    }
}

class Solution {

    public int numFriendRequests(int[] ages) {
        int l = ages.length, ans = 0;
        int[] user = new int[121];
        for (int age : ages) {
            user[age]++;
        }
        int sum = 0, left = 15;    //从15岁才会有朋友，15岁的朋友只有15岁
        for(int i = 15; i<121;i++){
            // 当前位置已经加不了好友，sum就删去这么多人
            if(left <= i*0.5+7){
                sum -= user[left];
                left ++;
            }
            if(user[i] == 0){
                continue;
            }
            // sum记录的是i之前有多少人可以加i为好友
            ans += user[i] * sum + user[i] * (user[i]-1);
            sum += user[i];
        }

        return ans;

    }
}
