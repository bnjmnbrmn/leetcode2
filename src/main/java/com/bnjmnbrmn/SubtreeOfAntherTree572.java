package com.bnjmnbrmn;

public class SubtreeOfAntherTree572 {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null)
            return true;
        if (root == null || subRoot == null)
            return false;

        return identical(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean identical(TreeNode treeA, TreeNode treeB) {
        if (treeA == null && treeB == null)
            return true;
        if (treeA == null || treeB == null)
            return false;

        if (treeA.val != treeB.val)
            return false;

        return identical(treeA.left, treeB.left) && identical(treeA.right, treeB.right);
    }
}
