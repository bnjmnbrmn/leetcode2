package com.bnjmnbrmn;

import java.util.*;

public class NetworkDelayTime743 {

    public static void main(String[] args) {
        int[][] times = new int[][] {new int[] {2,1,1}, new int[] {2,3,1}, new int[] {3,4,1}};
        new NetworkDelayTime743().networkDelayTime(times, 4, 2);
    }

    public int networkDelayTime(int[][] times, int n, int k) {

        int[][] dijkstra = dijkstra(times, n, k);

        int[] minTotalDistances = dijkstra[0];

        int maxDist = 0;
        for (int minTotalDistance : minTotalDistances) {
            if (minTotalDistance > maxDist)
                maxDist = minTotalDistance;

        }
        if (maxDist == Integer.MAX_VALUE)
            return -1;

        return maxDist;
    }

    public static int[][] dijkstra(int[][] edgeList, int n, int k) {
        List<List<WeightedEdge>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edgeWithWeight : edgeList) {
            int u = edgeWithWeight[0], v = edgeWithWeight[1], w = edgeWithWeight[2];
            adjList.get(u-1).add(new WeightedEdge(u, v, w));
//            adjList.get(v).add(new Edge(u, v, w));
        }

        int[] minTotalDistances = new int[n];
        int[] prevNodesInShortedPath = new int[n];
        Arrays.fill(minTotalDistances, Integer.MAX_VALUE);
        Arrays.fill(prevNodesInShortedPath, -1);
        minTotalDistances[k - 1] = 0;

        PriorityQueue<WeightedEdge> artificialMinEdges = new PriorityQueue<>(Comparator.comparing(WeightedEdge::weight));
        artificialMinEdges.offer(new WeightedEdge(k, k,0));

        while (!artificialMinEdges.isEmpty()) {
            WeightedEdge currEdge = artificialMinEdges.poll();

            if (currEdge.weight() > minTotalDistances[currEdge.destId() - 1])
                continue;

            for (WeightedEdge nextEdge : adjList.get(currEdge.destId()-1)) {
                int candidateMinTotalDistance = minTotalDistances[currEdge.destId() - 1] + nextEdge.weight();
                if (candidateMinTotalDistance < minTotalDistances[nextEdge.destId() - 1]) {
                    int minTotalDistanceToNextEdgeDest = candidateMinTotalDistance;
                    minTotalDistances[nextEdge.destId() - 1] = minTotalDistanceToNextEdgeDest;
                    prevNodesInShortedPath[nextEdge.destId() - 1] = nextEdge.srcId();
                    artificialMinEdges.offer(new WeightedEdge(k, nextEdge.destId(), minTotalDistanceToNextEdgeDest));
                }
            }
        }

        return new int[][] {minTotalDistances, prevNodesInShortedPath};


    }
}

record WeightedEdge(int srcId, int destId, int weight) {}

