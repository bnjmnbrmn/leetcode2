package com.bnjmnbrmn;

public class DiameterOfBinaryTree543 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode( 1,
                new TreeNode( 2), null);
//        TreeNode root = new TreeNode( 1,
//                new TreeNode( 2,
//                        new TreeNode(4),
//                        new TreeNode(5)
//                ),
//                new TreeNode(3)
//        );

        int diameter = new DiameterOfBinaryTree543().diameterOfBinaryTree(root);

        System.out.println("diameter = " + diameter);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        return maxDepthAndDiameter(root)[1];
    }

    private int[] maxDepthAndDiameter(TreeNode root) {
        if (root == null || root.left == null && root.right == null)
            return new int[]{0, 0};

        if (root.left == null) { // && root.right != null
            int[] maxDepthAndDiameterRight = maxDepthAndDiameter(root.right);
            int maxDepthRight = maxDepthAndDiameterRight[0];
            int diameterRight = maxDepthAndDiameterRight[1];
            int maxDepth = 1 + maxDepthRight;
            int maxDiameter = Math.max(maxDepth, diameterRight);
            return new int[] {maxDepth, maxDiameter};
        }

        if (root.right == null) { // && root.left != null
            int[] maxDepthAndDiameterLeft = maxDepthAndDiameter(root.left);
            int maxDepthLeft = maxDepthAndDiameterLeft[0];
            int diameterLeft = maxDepthAndDiameterLeft[1];
            int maxDepth = 1 + maxDepthLeft;
            int maxDiameter = Math.max(maxDepth, diameterLeft);
            return new int[] {maxDepth, maxDiameter};
        }

        int[] maxDepthAndDiameterLeft = maxDepthAndDiameter(root.left);
        int maxDepthLeft = maxDepthAndDiameterLeft[0];
        int diameterLeft = maxDepthAndDiameterLeft[1];
        int[] maxDepthAndDiameterRight = maxDepthAndDiameter(root.right);
        int maxDepthRight = maxDepthAndDiameterRight[0];
        int diameterRight = maxDepthAndDiameterRight[1];
        int maxDepth = 1 + Math.max(maxDepthLeft, maxDepthRight);
        int maxDiameter = Math.max(maxDepthLeft + 2 + maxDepthRight, Math.max(diameterLeft, diameterRight));
        return new int[] {maxDepth, maxDiameter};
    }

//    public int diameterOfBinaryTree(TreeNode root) {
//        if (root == null)
//            return 0;
//        if (root.left == null && root.right == null)
//            return 0;
//        if (root.left != null && root.right == null)
//            return Math.max(diameterOfBinaryTree(root.left), maxDepth(root.left) + 1);
//        if (root.left == null && root.right != null)
//            return Math.max(diameterOfBinaryTree(root.right), maxDepth(root.right) + 1);
//        return Math.max(
//                Math.max(maxDepth(root.left) + 2 + maxDepth(root.right),
//                        diameterOfBinaryTree(root.left)),
//                diameterOfBinaryTree(root.right));
//    }
//
//    private int maxDepth(TreeNode root) {
//        if (root == null)
//            return 0;
//        if (root.left == null && root.right == null)
//            return 0;
//        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
//    }
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
