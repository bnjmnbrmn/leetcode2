package com.bnjmnbrmn;

import java.util.Arrays;

public class PrimsAlgorithm {
    public static void main(String[] args) {
        Graph g = new Graph(8);

        g.addVertexData(0, "A");
        g.addVertexData(1, "B");
        g.addVertexData(2, "C");
        g.addVertexData(3, "D");
        g.addVertexData(4, "E");
        g.addVertexData(5, "F");
        g.addVertexData(6, "G");
        g.addVertexData(7, "H");

        g.addEdge(0, 1, 4);
        g.addEdge(0, 3, 3);
        g.addEdge(1, 2, 3);
        g.addEdge(1, 3, 5);
        g.addEdge(1, 4, 6);
        g.addEdge(2, 4, 4);
        g.addEdge(2, 7, 2);
        g.addEdge(3, 4, 7);
        g.addEdge(3, 5, 4);
        g.addEdge(4, 5, 5);
        g.addEdge(4, 6, 3);
        g.addEdge(5, 6, 7);
        g.addEdge(6, 7, 5);

        g.primsAlgorithm();
    }

    static class Graph {
        int size;
        int[][] adjMatrix;
        String[] vertexData;

        public Graph(int size) {
            this.size = size;
            this.adjMatrix = new int[size][];
            this.vertexData = new String[size];

            for (int i = 0; i < size; i++) {
                int[] row = new int[size];
                this.adjMatrix[i] = row;
                this.vertexData[i] = "";
            }
        }

        public void addEdge(int u, int v, int weight) {
            adjMatrix[u][v] = weight;
            adjMatrix[v][u] = weight;
        }

        public void addVertexData(int vertex, String data) {
            vertexData[vertex] = data;
        }

        public void primsAlgorithm() {
            boolean[] inMST = new boolean[size];
            int[] keyValues = new int[size];
            Arrays.fill(keyValues, Integer.MAX_VALUE);
            int[] parents = new int[size];
            Arrays.fill(parents, -1);
            keyValues[0] = 0;

            System.out.println("Edge \tWeight");
            for (int i = 0; i < size; i++) {
                int u = getNodeWithMinimumValueNotInSet(size, inMST, keyValues);

                inMST[u] = true;

                if (parents[u] != -1)
                    System.out.println(vertexData[parents[u]] + "-"
                            + vertexData[u] + "\t" + adjMatrix[u][parents[u]]);

                for (int v = 0; v < size; v++) {
                    if (0 < adjMatrix[u][v] && adjMatrix[u][v] < keyValues[v] && !inMST[v]) {
                        keyValues[v] = adjMatrix[u][v];
                        parents[v] = u;
                    }
                }
            }
        }

        private int getNodeWithMinimumValueNotInSet(int size, boolean[] inMST, int[] keyValues) {
            int min = -1;
            for (int i = 0; i < size; i++) {
                if ((min == -1 || keyValues[i] < keyValues[min]) && !inMST[i])
                    min = i;
            }
            return min;
        }
    }
}

