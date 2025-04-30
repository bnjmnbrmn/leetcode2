package com.bnjmnbrmn;

import com.bnjmnbrmn.PrimsAlgorithm3.Graph.EdgeTo;
import com.bnjmnbrmn.PrimsAlgorithm3.Graph.Node;

import java.util.*;
import java.util.stream.Collectors;

public class PrimsAlgorithm3 {

    public static void main(String[] args) {
        Node a = new Node();
        Node b = new Node();
        Node c = new Node();
        Node d = new Node();
        Graph graph = new Graph(
                Map.of(
                        a, Set.of(new EdgeTo(b, 1), new EdgeTo(c, 1), new EdgeTo(d, 1)),
                        b, Set.of(new EdgeTo(a, 1), new EdgeTo(c, 1), new EdgeTo(d, 1)),
                        c, Set.of(new EdgeTo(a, 1), new EdgeTo(b, 1), new EdgeTo(d, 1)),
                        d, Set.of(new EdgeTo(a, 1), new EdgeTo(b, 1), new EdgeTo(c, 1))
                ));
        Graph mst = graph.primMST();
        System.out.println("mst = " + mst);
    }

    static final class Graph {

        private final Map<Node, Set<EdgeTo>> edgeTosFromNodes;

        public Graph(Map<Node, Set<EdgeTo>> edgeTosFromNodes) {
            this.edgeTosFromNodes = edgeTosFromNodes;
        }

        private Set<EdgeTo> edgeTosFromNode(Node node) {
            return edgeTosFromNodes.get(node);
        }

        Graph primMST() {
            Optional<Node> optionalFirstNode = getNodes().stream().findAny();
            if (optionalFirstNode.isEmpty())
                return new Graph(new HashMap<>());
            Node firstNode = optionalFirstNode.get();


            Map<Node, Integer> minConnectionWeightsForNodes = new HashMap<>();
            for (Node node : getNodes()) {
                minConnectionWeightsForNodes.put(node, Integer.MAX_VALUE);
            }
            minConnectionWeightsForNodes.put(firstNode, 0);


            Map<Node, Set<EdgeTo>> mstEdgesForNodes = new HashMap<>();
            for (Node node : getNodes()) {
                mstEdgesForNodes.put(node, new HashSet<>());
            }


            Set<Node> visitedNodes = new HashSet<>();

            PriorityQueue<EdgeTo> artificialEdgeTosMinWeightPQ
                    = new PriorityQueue<>(Comparator.comparing(EdgeTo::weight));
            artificialEdgeTosMinWeightPQ.offer(new EdgeTo(firstNode, 0));

            while (!artificialEdgeTosMinWeightPQ.isEmpty()) {
                EdgeTo minWeightArtificialEdgeTo = artificialEdgeTosMinWeightPQ.poll();

                if (visitedNodes.contains(minWeightArtificialEdgeTo.node))
                    continue;

                visitedNodes.add(minWeightArtificialEdgeTo.node);

                Set<EdgeTo> unvisitedNodeEdgesTos =
                        edgeTosFromNode(minWeightArtificialEdgeTo.node)
                                .stream()
                                .filter(et -> !visitedNodes.contains(et.node))
                                .collect(Collectors.toSet());

                for (EdgeTo edgeToAnUnvisitedNode : unvisitedNodeEdgesTos) {
                    if (edgeToAnUnvisitedNode.weight
                            < minConnectionWeightsForNodes.get(edgeToAnUnvisitedNode.node)) {
                        minConnectionWeightsForNodes.put(edgeToAnUnvisitedNode.node,
                                edgeToAnUnvisitedNode.weight);
                        mstEdgesForNodes.get(minWeightArtificialEdgeTo.node)
                                .add(edgeToAnUnvisitedNode);
                        artificialEdgeTosMinWeightPQ.offer(edgeToAnUnvisitedNode);

                    }
                }

            }

            return new Graph(mstEdgesForNodes);

        }

        private Set<Node> getNodes() {
            return edgeTosFromNodes.keySet();
        }

        static class Node {
        }

        record EdgeTo(Node node, int weight) {
        }
    }
}


