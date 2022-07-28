package Easy.二叉树的坡度;

public class BinaryTreeTilt {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(){}

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    // 需要知道左右子树的节点和，左右子树的坡度
    static class ReturnType{
        int sum;  // 和
        int tilt; // 坡度
        int tiltSum; // 坡度和

        public ReturnType(int sum, int tilt, int tiltSum) {
            this.sum = sum;
            this.tilt = tilt;
            this.tiltSum = tiltSum;
        }
    }
    public int findTilt(TreeNode root) {

        return process(root).tiltSum;
    }

    public ReturnType process(TreeNode root){
        if (root==null){
            return new ReturnType(0,0, 0);
        }
        ReturnType left = process(root.left);
        ReturnType right = process(root.right);

        int sum = root.val + left.sum + right.sum;
        int tilt = Math.abs(left.sum- right.sum);
        int tiltSum = tilt + left.tiltSum + right.tiltSum;
        return new ReturnType(sum ,tilt, tiltSum);
    }

    // 还可以dfs：每次返回当前节点的所在子树的节点和
    // 在返回节点和之前，先计算左右子树的节点和的差，也就是坡度，加在返回值里面
    int ans = 0;
    public int dfs(TreeNode head){
        if (head==null){
            return 0;
        }
        int left = dfs(head.left);
        int right = dfs(head.right);
        ans+=(Math.abs(left-right));
        return head.val + left + right;
    }
}
