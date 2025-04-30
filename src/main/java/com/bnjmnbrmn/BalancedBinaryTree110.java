package com.bnjmnbrmn;

public class BalancedBinaryTree110 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(3,
                                new TreeNode(4),
                                new TreeNode(4)),
                        new TreeNode(3)),
                new TreeNode(2)
        );

        boolean balanced = new BalancedBinaryTree110().isBalanced(root);
        System.out.println("balanced = " + balanced);
    }

    public boolean isBalanced(TreeNode root) {
        return h(root) >= 0;
    }

    private int h(TreeNode root) {
        if (root == null)
            return 0;

        int hLeft = h(root.left);
        if (hLeft == -1)
            return -1;

        int hRight = h(root.right);
        if (hRight == -1)
            return -1;

        return hLeft <= hRight + 1 && hLeft >= hRight - 1 ? Math.max(hLeft,hRight) + 1 : -1;
    }
}
