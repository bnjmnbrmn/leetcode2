package com.bnjmnbrmn;

public class MaxAreaOfIsland695 {

    public static final int UNEXPLORED_WATER = 0;
    public static final int UNEXPLORED_LAND = 1;
    public static final int EXPLORED = 2;

    public int maxAreaOfIsland(int[][] grid) {
        int maxIslandSize = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (isUnexploredLand(grid, i, j)) {
                    int islandSize = explore(grid, i, j);
                    if (islandSize > maxIslandSize) {
                        maxIslandSize = islandSize;
                    }
                }
            }
        }
        return maxIslandSize;
    }

    private int explore(int[][] grid, int i, int j) {
        grid[i][j] = EXPLORED;

        int exploredArea = 1;

        if (isUnexploredLand(grid, i, j-1)) {
            exploredArea += explore(grid, i, j-1);
        }
        if (isUnexploredLand(grid, i, j+1)) {
            exploredArea += explore(grid, i, j+1);
        }
        if (isUnexploredLand(grid, i-1, j)) {
            exploredArea += explore(grid, i-1, j);
        }
        if (isUnexploredLand(grid, i+1, j)) {
            exploredArea += explore(grid, i+1, j);
        }

        return exploredArea;
    }

    private boolean isUnexploredLand(int[][] grid, int i, int j) {
        if (i >= grid.length || i < 0)
            return false;
        if (j >= grid[0].length || j < 0)
            return false;

        return grid[i][j] == UNEXPLORED_LAND;
    }
}
