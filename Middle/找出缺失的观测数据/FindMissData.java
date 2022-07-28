package Middle.找出缺失的观测数据;

public class FindMissData {
    /*
        1. rolls：已知的骰子数量
        2. mean：总骰子数量的均值
        3. n：还差的骰子数量
     */
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int total = mean*(rolls.length+n);  // 实际的
        int[] ans = new int[n];
        int other = 0;
        for (int i: rolls){
            other+=i;
        }
        total-=other;
        if ( total<=0 || (total*1.0/n)>6.0 || (total*1.0/n)<1.0 ) return new int[]{};
        process(ans, 0, n, total);
        return ans;
    }

    public void process(int[] arr, int idx,int n, int total){
        // base case
        if (arr[arr.length-1]!=0 || idx==arr.length || n==0) return;
        if (total-6>=n-1){
            arr[idx] = 6;
            process(arr, idx+1, n-1, total-6);
        }else if (total-5>=n-1){
            arr[idx] = 5;
            process(arr, idx+1, n-1, total-5);
        }else if (total-4>=n-1){
            arr[idx] = 4;
            process(arr, idx+1, n-1, total-4);
        }else if (total-3>=n-1){
            arr[idx] = 3;
            process(arr, idx+1, n-1, total-3);
        }else if (total-2>=n-1){
            arr[idx] = 2;
            process(arr, idx+1, n-1, total-2);
        }else if (total-1>=n-1){
            arr[idx] = 1;
            process(arr, idx+1, n-1, total-1);
        }
    }

    public int[] missingRolls2(int[] rolls, int mean, int n) {
        int total = mean*(rolls.length+n);  // 实际的
        int[] ans = new int[n];
        int other = 0;
        for (int i: rolls){
            other+=i;
        }
        total-=other;
        if ( total<=0 || (total*1.0/n)>6.0 || (total*1.0/n)<1.0 ) return new int[]{};
        int tn = n;
        for (int idx=0; idx<n; idx++){
            if (total-6>=tn-1){
                ans[idx] = 6;
                total = total-6;
            }else if (total-5>=tn-1){
                ans[idx] = 5;
                total = total-5;
            }else if (total-4>=tn-1){
                ans[idx] = 4;
                total = total-4;
            }else if (total-3>=tn-1){
                ans[idx] = 3;
                total = total-3;
            }else if (total-2>=tn-1){
                ans[idx] = 2;
                total = total-2;
            }else if (total-1>=tn-1){
                ans[idx] = 1;
                total = total-1;
            }
            tn--;
        }
        return ans;
    }

    /*
        m = rolls.length
        总和是：sum = mean*(m+n)
        丢失的和： missSum = sum - sum(rolls)
        因为骰子是1~6，所以合理范围是：n <= missSum <= 6*n
        那么令：quotient = missSum/n， remainder = missSum%n。
        即剩下的和，一定可以由 x 个 quotient 和 remainder 个 quotient+1 组成，其中 x+remainder=n
     */
    public int[] missingRolls3(int[] rolls, int mean, int n) {
        int m = rolls.length;
        int missSum = mean*(m+n);
        for (int i: rolls){
            missSum-=i;  // 剩下的和
        }
        if (missSum<n || missSum>6*n) return new int[0];
        int[] ans = new int[n];
        int quotient = missSum/n;
        int remainder = missSum%n;
        for (int i=0; i<n; i++){
            ans[i] = quotient + (i<remainder?1:0);
        }
        return ans;
    }
}
