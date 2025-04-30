package com.bnjmnbrmn;

import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

public class LastStoneWeight1046 {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int stone : stones) {
            pq.add(stone);
        }

        while(true) {
            Integer x = pq.poll();
            Integer y = pq.poll();
            if (y == null) {
                return Objects.requireNonNullElse(x, 0);
            }
            if (!Objects.equals(x, y))
                pq.add(Math.abs(x - y));
        }
    }
}
