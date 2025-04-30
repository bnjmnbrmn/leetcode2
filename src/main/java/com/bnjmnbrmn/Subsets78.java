package com.bnjmnbrmn;

import java.util.ArrayList;
import java.util.List;

public class Subsets78 {

    public static void main(String[] args) {
        List<List<Integer>> subsets = new Subsets78().subsets(new int[]{1, 2, 3});
        System.out.println("subsets = " + subsets);
    }

//    public List<List<Integer>> subsets(int[] nums) {
//        List<List<Integer>> output = new ArrayList<>();
//        output.add(new ArrayList<>());
//
//        for (int num : nums) {
//            List<List<Integer>> newSubsets = addToEachSet(num, output);
//            output.addAll(newSubsets);
//        }
//        return output;
//    }
//
//    private static List<List<Integer>> addToEachSet(int num, List<List<Integer>> sets) {
//        return sets.stream().map(set -> duplicatePlus(set, num))
//                .toList();
//    }
//
//    private static List<Integer> duplicatePlus(List<Integer> set, int num) {
//        List<Integer> toRet = new ArrayList<>(set);
//        toRet.add(num);
//        return toRet;
//    }

    //bitmasks
//    public List<List<Integer>> subsets(int[] nums) {
//        List<List<Integer>> output = new ArrayList();
//        int n = nums.length;
//
//        for (int i = (int) Math.pow(2, n); i < (int) Math.pow(2, n + 1); ++i) {
//            // generate bitmask, from 0..00 to 1..11
//            String bitmask = Integer.toBinaryString(i).substring(1);
//
//            // append subset corresponding to that bitmask
//            List<Integer> curr = new ArrayList();
//            for (int j = 0; j < n; ++j) {
//                if (bitmask.charAt(j) == '1') curr.add(nums[j]);
//            }
//            output.add(curr);
//        }
//        return output;
//    }


    //backtracking
//    private int n;
//    private List<List<Integer>> output = new ArrayList<>();
//
//    public List<List<Integer>> subsets(int[] nums) {
//        n = nums.length;
//
//        ArrayList<Integer> currCombo = new ArrayList<>();
//        backtrack(0, currCombo, nums);
//        return output;
//
//    }
//
//    private void backtrack(int first, ArrayList<Integer> curr, int[] nums) {
//        output.add(new ArrayList<>(curr));
//        for (int i = first; i < n; ++i) {
//            curr.add(nums[i]);
//            backtrack(i + 1, curr, nums);
//            curr.remove(curr.size() - 1);
//        }
//    }

    //backtracking 2
    public List<List<Integer>> subsets(int[] nums) {
        return subsetsOfSuffixThatAlsoInclude(nums, 0, new ArrayList<>());
    }

    private List<List<Integer>> subsetsOfSuffixThatAlsoInclude(int[] nums, int suffixStart, List<Integer> toInclude) {
        List<List<Integer>> toRet = List.of(toInclude);
        for (int i = suffixStart; i < nums.length; i++) {
            List<Integer> toIncludePlusIth = concat(toInclude, List.of(nums[i]));
            List<List<Integer>> suffixSubsetsPlus = subsetsOfSuffixThatAlsoInclude(nums, i + 1, toIncludePlusIth);
            toRet = concat(toRet,  suffixSubsetsPlus);
        }
        return toRet;
    }

    private <T> List<T> concat(List<T> listA, List<T> listB) {
        List<T> toRet = new ArrayList<>(listA);
        toRet.addAll(listB);
        return toRet;
    }

}
