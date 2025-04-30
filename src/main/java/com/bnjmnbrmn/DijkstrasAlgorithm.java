package com.bnjmnbrmn;

import java.util.*;

public class DijkstrasAlgorithm {

    static final class Graph {

        private final Map<Node, Set<EdgeTo>> edgeTosFromNodes;

        public Graph(Map<Node, Set<EdgeTo>> edgeTosFromNodes) {
            this.edgeTosFromNodes = edgeTosFromNodes;
        }

        private Set<EdgeTo> edgeTosFromNode(Node node) {
            return edgeTosFromNodes.get(node);
        }

        private Set<Node> getNodes() {
            return edgeTosFromNodes.keySet();
        }

        public Map<Node, Path> shortestPathsToNodesMap(Node src) {
            Map<Node, Path> minPathsToGivenNodeMap = new HashMap<>();
            Path pathFromSrcToSrc = new Path(src, new ArrayList<>());
            minPathsToGivenNodeMap.put(src, pathFromSrcToSrc);

            PriorityQueue<Path> pathsFromSourcePQ = new PriorityQueue<>(Comparator.comparing(Path::getTotalWeight));
            pathsFromSourcePQ.offer(pathFromSrcToSrc);

            while (!pathsFromSourcePQ.isEmpty()) {
                Path path = pathsFromSourcePQ.poll();
                Node pathEndNode = path.getDest();

                Optional<Path> optionalMinPathToEndNode = Optional.ofNullable(minPathsToGivenNodeMap.get(pathEndNode));
                if (optionalMinPathToEndNode.isPresent()
                        && path.getTotalWeight() > optionalMinPathToEndNode.get().getTotalWeight()) {
                    continue;
                }

                for (EdgeTo nextEdgeTo : edgeTosFromNode(pathEndNode)) {
                    int candidateMinTotalDistanceToNext
                            = minPathsToGivenNodeMap.get(pathEndNode).getTotalWeight() + nextEdgeTo.weight();

                    Path candidatePathToNext = path.extended(nextEdgeTo);

                    Optional<Path> optionalMinPathToNext = Optional.ofNullable(minPathsToGivenNodeMap.get(nextEdgeTo.node()));
                    if (optionalMinPathToNext.isEmpty()
                            || candidatePathToNext.getTotalWeight() < optionalMinPathToNext.get().getTotalWeight()) {
                        minPathsToGivenNodeMap.put(nextEdgeTo.node(), candidatePathToNext);
                        pathsFromSourcePQ.offer(candidatePathToNext);
                    }
                }
            }

            return minPathsToGivenNodeMap;

        }


        static class Node {}

        record EdgeTo(Node node, int weight) {}

        class Path {
            private final Node src;
            private final List<EdgeTo> edgeTos;
            private final Integer totalWeight;

            public Path(Node src, List<EdgeTo> edgeTos) {
                this.src = src;
                this.edgeTos = edgeTos;
                this.totalWeight = edgeTos.stream().map(et -> et.weight).reduce(Integer::sum)
                        .orElse(0);
            }

            public Node getSrc() {
                return src;
            }

            public List<EdgeTo> getEdgeTos() {
                return edgeTos;
            }

            public Integer getTotalWeight() {
                return totalWeight;
            }

            public Node getDest() {
                return edgeTos.getLast().node;
            }

            public Path extended(EdgeTo edgeTo) {
                List<EdgeTo> newEdgeTos = new ArrayList<>(edgeTos);
                newEdgeTos.add(edgeTo);
                return new Path(src, newEdgeTos);
            }
        }
    }

}
