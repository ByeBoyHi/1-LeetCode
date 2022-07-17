package Middle.����Ƕ��;


public class ArrayNesting {

    /**
     * ����{A[i], A[A[i]], A[A[A[i]]], ...}
     * ������Ȼ�Ƿ��ʵ��Ѿ����ʹ��Ľڵ�
     * ���ҽڵ��ֵ�ǲ��ᳬ�����鳤�ȵģ���˱�Ȼ�ỷ
     */
    public int arrayNesting(int[] nums) {
        int ans = 0;
        int n = nums.length;
        boolean[] vis = new boolean[n];
        for (int i=0; i<n; i++){
            int cnt = 0;
            while (!vis[i]){
                vis[i] = true;
                i = nums[i];
                ++cnt;
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }

    // ��nums[i]=n��ʵ��vis�Ĺ���
    // �����Ŀ�������ص��ǣ�
    // 1. ����ΪN
    // 2. ��ֵΪ 0~N-1
    // 3. ���������±��ֵ������ͬ
    // ��ô��Ȼ�������β�����ıջ������������һ������һ������һ������
    // ��Ϊ�����Ļ��ͳ���������λ�ö�ָ����ͬһ��λ��
    public int arrayNesting2(int[] nums) {
        int ans = 0;
        int n = nums.length;

        for (int i=0; i<n; i++){
            int cnt = 0;
            while (nums[i]<n){
                int num = nums[i];  // �����nums[i]��仯�����Լǵ���ȡ����
                nums[i] = n;
                cnt++;
                i = num;
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }
}
