package Easy.将有序数组转换为二叉搜索树;

public class ArrToBiTree {
    // 要求生成Balance Search Tree
    public TreeNode sortedArrayToBST(int[] nums) {
        return process(nums, 0, nums.length-1);
    }

    // 在数组nums的[left,right]范围上递归生成树
    public TreeNode process(int[] nums, int left, int right){
        if (left==right){  // 只有一个点，自成一个点
            return new TreeNode(nums[left]);
        }
        if (left>right){
            return null;
        }
        int mid = left + ((right-left)>>1);
        TreeNode root = new TreeNode(nums[mid]);
        root.left=process(nums,left, mid-1);
        root.right=process(nums, mid+1, right);
        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
