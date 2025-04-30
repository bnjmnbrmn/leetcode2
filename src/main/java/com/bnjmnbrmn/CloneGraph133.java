package com.bnjmnbrmn;

import java.util.*;

public class CloneGraph133 {

    public static void main(String[] args) {
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);

        a.neighbors.add(b);
        a.neighbors.add(c);
        b.neighbors.add(a);
        b.neighbors.add(d);
        c.neighbors.add(a);
        c.neighbors.add(d);
        d.neighbors.add(b);
        d.neighbors.add(c);

        Node clone = new CloneGraph133().cloneGraph(a);
        System.out.println("clone = " + clone);
    }

    Map<Node, Node> map = new HashMap<>();
    Queue<Node> toVisit = new LinkedList<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Node currentClone = new Node(node.val);
        map.put(node, currentClone);
        Node rootClone = currentClone;
        for (Node currentNeighbor : node.neighbors) {
            Node currentNeighborClone = new Node(currentNeighbor.val);
            map.put(currentNeighbor, currentNeighborClone);
            currentClone.neighbors.add(currentNeighborClone);
            toVisit.add(currentNeighbor);
        }

        while (!toVisit.isEmpty()) {
            Node current = toVisit.poll();
            currentClone = map.get(current);
            for (Node currentNeighbor : current.neighbors) {
                if (map.containsKey(currentNeighbor)) {
                    currentClone.neighbors.add(map.get(currentNeighbor));
                } else {
                    Node currentNeighborClone = new Node(currentNeighbor.val);
                    map.put(currentNeighbor, currentNeighborClone);
                    currentClone.neighbors.add(currentNeighborClone);
                    toVisit.add(currentNeighbor);
                }
            }
        }
        return rootClone;
    }

//    public Node cloneGraph(Node node) {
//        if (node == null) {
//            return null;
//        }
//
//        Node clone = new Node(node.val);
//        map.put(node, clone);
//
//        for (Node neighbor : node.neighbors) {
//            if (map.containsKey(neighbor)) {
//                clone.neighbors.add(map.get(neighbor));
//            } else {
//                Node neighborClone = cloneGraph(neighbor);
//                clone.neighbors.add(neighborClone);
//            }
//        }
//
//        return clone;
//    }


}


// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
