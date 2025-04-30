package com.bnjmnbrmn;

public class SearchA2DMatrix74 {
    public boolean searchMatrix(int[][] matrix, int target) {

        int m = matrix.length;
        int n = matrix[0].length;

        if (target < matrix[0][0] || target > matrix[m-1][n-1])
            return false;

        int row = findRow(matrix, target);

        return rowContains(matrix[row], target);
    }

    private boolean rowContains(int[] matrix, int target) {
        int left = 0;
        int right = matrix.length - 1;
        int mid = right / 2;

        while (right >= left) {
            if (matrix[mid] == target)
                return true;
            if (matrix[mid] > target) {
                right = mid - 1;
            } else { //matrix[mid] < target
                left = mid+1;
            }
            mid = left + (right - left)/2;
        }

        return false;
    }

    private int findRow(int[][] matrix, int target) {
        int top = 0;
        int bottom = matrix.length - 1;
        int mid = bottom / 2;

        while (bottom >= top) {
            if (rowStartValue(matrix, mid) == target)
                return mid;
            if (rowStartValue(matrix, mid) < target) {
                if (mid == matrix.length - 1) {
                    return mid;
                } else if (rowStartValue(matrix, mid+1) > target) {
                    return mid;
                } else { //rowStartValue(matrix, mid+1) <= target
                    top = mid+1;
                    mid = top + (bottom - top)/2;
                }
            } else { // rowStartValue(matrix, mid) > target
                if (mid == 0) {
                    return mid;
                } else if (rowStartValue(matrix, mid-1) < target) {
                    return mid-1;
                } else { //rowStartValue(matrix, mid-1) >= target
                    bottom = mid - 1;
                    mid = top + (bottom - top)/2;
                }
            }
        }

        return mid;
    }

    private int rowStartValue(int[][] matrix, int rowIndex) {
        return matrix[rowIndex][0];
    }
}
