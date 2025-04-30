package com.bnjmnbrmn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PrimsAlgorithm2 {

    record Edge(int target, int weight){}

    public static void primMST(List<List<Edge>> adjList, int n) {
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        int[] minWeightToConnectToMST = new int[n];
        Arrays.fill(minWeightToConnectToMST, Integer.MAX_VALUE);
        minWeightToConnectToMST[0] = 0;

        PriorityQueue<Edge> edgePQ = new PriorityQueue<>(Comparator.comparing(e -> e.weight));
        edgePQ.offer(new Edge(0, 0));

        while (!edgePQ.isEmpty()) {
            int currentNode = edgePQ.poll().target;
            if (visited[currentNode]) continue;
            visited[currentNode] = true;
            for (Edge neighborEdge : adjList.get(currentNode)) {
                int neighborNode = neighborEdge.target;
                int neighborEdgeWeight = neighborEdge.weight;
                if (!visited[neighborNode]
                        && neighborEdgeWeight < minWeightToConnectToMST[neighborNode]) {
                    minWeightToConnectToMST[neighborNode] = neighborEdgeWeight;
                    parent[neighborNode] = currentNode;
                    edgePQ.offer(new Edge(minWeightToConnectToMST[neighborNode], neighborNode));
                }
            }
        }



    }

}
