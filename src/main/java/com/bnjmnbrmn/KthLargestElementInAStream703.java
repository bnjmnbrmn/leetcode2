package com.bnjmnbrmn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class KthLargestElementInAStream703 {

}

class KthLargest {

    private final int k;
    private final PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (priorityQueue.isEmpty()) {
            priorityQueue.add(val);
            return val;
        }

        if (priorityQueue.size() < k) {
            priorityQueue.add(val);
        } else if (priorityQueue.peek() < val) {
            priorityQueue.add(val);
            priorityQueue.remove();
        }
        //noinspection DataFlowIssue
        return priorityQueue.peek();
    }
}
