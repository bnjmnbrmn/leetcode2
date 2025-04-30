package com.bnjmnbrmn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum39 {
    public static void main(String[] args) {
        List<List<Integer>> lists = new CombinationSum39().combinationSum(new int[]{2,3,6,7}, 7);
        System.out.println("lists = " + lists);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<Integer> emptyCombo = new ArrayList<>();
        return combinationSum(candidates, target, 0, emptyCombo);
    }

    private List<List<Integer>> combinationSum(int[] candidates, int target, int leftBound, List<Integer> current) {
        List<List<Integer>> combos = new ArrayList<>();
        if (target < 0) {
            return combos;
        }
        if (target == 0) {
            combos.add(current);
            return combos;
        }


        for (int i = leftBound; i < candidates.length; i++) {
            if (candidates[i] > target) break;
            combos.addAll(combinationSum(candidates, target - candidates[i], i, appendImmut(current, candidates[i])));
        }
        return combos;
    }

    private List<Integer> appendImmut(List<Integer> current, int candidate) {
        List<Integer> toRet = new ArrayList<>(current);
        toRet.add(candidate);
        return toRet;
    }

//    public List<List<Integer>> combinationSum(int[] candidates, int target) {
//        Arrays.sort(candidates);
//        List<List<Integer>> result = new ArrayList<>();
//        List<Integer> emptyCombo = new ArrayList<>();
//        combinationSum(candidates, target, 0, emptyCombo, result);
//        return result;
//    }
//
//    private void combinationSum(int[] candidates, int target, int leftBound, List<Integer> current, List<List<Integer>> result) {
//        if (target < 0) {
//            return;
//        }
//        if (target == 0) {
//            result.add(new ArrayList<>(current));
//            return;
//        }
//
//
//        for (int i = leftBound; i < candidates.length; i++) {
//            if (candidates[i] > target) break;
//            current.add(candidates[i]);
//            combinationSum(candidates, target - candidates[i], i, current, result);
//            current.removeLast();
//        }
//    }


//    public List<List<Integer>> combinationSum(int[] candidates, int target) {
//        List<List<Integer>> result = new ArrayList<>();
//        Arrays.sort(candidates);
//        backtrack(candidates, target, 0, new ArrayList<>(), result);
//        return result;
//    }
//
//    private void backtrack(int[] candidates, int target, int leftBound, List<Integer> current, List<List<Integer>> result) {
//        if(target == 0) {
//            result.add(new ArrayList<>(current));
//            return;
//        }
//        if(target < 0) return;
//
//        for(int i = leftBound; i < candidates.length; i++) {
//            if(candidates[i] > target) break;
//            current.add(candidates[i]);
//            backtrack(candidates, target - candidates[i], i, current, result);
//            current.removeLast();
//        }
//    }


    //    public List<List<Integer>> combinationSum(int[] candidates, int target) {
//        return combinationSum(candidates, target, 0);
//    }
//
//    private List<List<Integer>> combinationSum(int[] candidates, int target, int leftBound) {
//
//        if (target <= 0 || leftBound >= candidates.length) {
//            return new ArrayList<>();
//        }
//
//        List<List<Integer>> combosNotInvolvingBound = combosNotInvolvingBound(candidates, target, leftBound);
//        if (candidates[leftBound] == target) {
//            List<Integer> singleton = new ArrayList<>();
//            singleton.add(target);
//            combosNotInvolvingBound.add(singleton);
//            return combosNotInvolvingBound;
//        }
//
//        combosNotInvolvingBound.addAll(combosInvolvingBound(candidates, target, leftBound));
//        return combosNotInvolvingBound;
//    }
//
//    private List<List<Integer>> combosNotInvolvingBound(int[] candidates, int target, int leftBound) {
//        return combinationSum(candidates, target, leftBound+1);
//    }
//
//    private List<List<Integer>> combosInvolvingBound(int[] candidates, int target, int leftBound) {
//        int candidate = candidates[leftBound];
//        List<List<Integer>> lists = combinationSum(candidates, target - candidate, leftBound);
//        lists.forEach(combo -> combo.add(candidate));
//        return lists;
//    }


//    public static void main(String[] args) {
//        List<List<Integer>> lists = new CombinationSum39().combinationSum(new int[]{4, 2, 8}, 8);
//        System.out.println("lists = " + lists);
//    }
//    public List<List<Integer>> combinationSum(int[] candidates, int target) {
//        return combinationSum(candidates, target, 0);
//    }
//
//    private List<List<Integer>> combinationSum(int[] candidates, int target, int leftBound) {
//
//        if (target <= 0 || leftBound >= candidates.length) {
//            return new ArrayList<>();
//        }
//
//        List<List<Integer>> combos = new ArrayList<>();
//        if (candidates[leftBound] == target) {
//            List<Integer> singleton = new ArrayList<>();
//            singleton.add(target);
//            combos.add(singleton);
//            combos.addAll(combosNotInvolvingBound(candidates, target, leftBound));
//            return combos;
//        }
//
//        combos.addAll(combosInvolvingBound(candidates, target, leftBound));
//        combos.addAll(combosNotInvolvingBound(candidates, target, leftBound));
//        return combos;
//    }
//
//    private List<List<Integer>> combosNotInvolvingBound(int[] candidates, int target, int leftBound) {
//        return combinationSum(candidates, target, leftBound+1);
//    }
//
//    private List<List<Integer>> combosInvolvingBound(int[] candidates, int target, int leftBound) {
//        return combinationSum(candidates, target - candidates[leftBound], leftBound).stream().map(combo -> {
//            List<Integer> newCombo = new ArrayList<>();
//            newCombo.add(candidates[leftBound]);
//            newCombo.addAll(combo);
//            return newCombo;
//        }).collect(Collectors.toList());
//    }
}
