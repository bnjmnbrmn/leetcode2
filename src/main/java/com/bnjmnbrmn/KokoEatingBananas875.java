package com.bnjmnbrmn;


import java.util.Arrays;

public class KokoEatingBananas875 {
    public int minEatingSpeed(int[] piles, int h) {
        Arrays.sort(piles);
        int max = max(piles);

        int l = 0;
        int r = max;
        int m = max / 2;

        Boolean[] finArr = new Boolean[h+1];



        while (true) {
            if (m == 0) {
                if (finishes(piles, h, m, finArr)) {
                    return m + 1;
                } else {
                    l = m + 1;
                    m = l + (r - l)/2;
                }
            } else {
                if (finishes(piles, h, m, finArr) && !finishes(piles, h, m-1, finArr)) {
                    //should not happen
                    throw new RuntimeException();
                } else if (!finishes(piles, h, m, finArr) && finishes(piles, h, m-1, finArr)) {
                    return m + 1;
                } else if (!finishes(piles, h, m, finArr) && !finishes(piles, h, m-1, finArr)) {
                    l = m + 1;
                    m = l + (r - l)/2;
                } else { //finishes(piles, h, m, finArr) && finishes(piles, h, m-1, finArr)
                    l = m + 1;
                    m = l + (r - l)/2;
                }
            }

        }







    }

    private boolean finishes(int[] sortedPiles, int h, int k, Boolean[] finArr) {
        if (finArr[k] != null) {
            return finArr[k];
        }

        int i = sortedPiles.length - 1;
        int currentHours = 0;
        while (i >= 0) {
            currentHours += sortedPiles[i] / k;
            if (sortedPiles[i] % k > 0)
                currentHours++;

            if (currentHours > h)
                return false;
            i--;
        }


        return true;
    }

    int max(int[] arr) {
        int max = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        return max;
    }
}
