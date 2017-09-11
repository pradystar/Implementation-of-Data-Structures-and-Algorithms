package cs6301.g32;

import cs6301.g00.Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author prady
 * Ver 1.0:    9/1/17
 */

public class BFS {

    //class to store information about a vertex in BFS
    public static class BFSVertex {
        private Graph.Vertex vertex;
        private BFSVertex parent;
        private boolean seen; // true if BFS has expanded the vertex
        private int depth; // depth of the vertex from the source of BFS

        public Graph.Vertex getVertex() {
            return vertex;
        }

        public BFSVertex getParent() {
            return parent;
        }

        public void setParent(BFSVertex parent) {
            this.parent = parent;
        }

        public boolean isSeen() {
            return seen;
        }

        public void setSeen(boolean seen) {
            this.seen = seen;
        }

        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }

        BFSVertex(Graph.Vertex vertex) {
            this.vertex = vertex;
            seen = false;
            depth = -1;
        }

        void initialiseBFS() {
            this.seen = true;
            this.depth = 0;
        }

        @Override
        public String toString() {
            return "" + vertex + " parent " + parent + " depth " + depth;
        }
    }

    /**
     * This method performs BFS on the Graph g starting from the vertex u(depth 0) and returns a list of vertices
     * with depth information
     *
     * @param g : Graph : Graph on which BFS has to be performed
     * @param s : Graph.Vertex   : name of source vertex
     * @return : LinkedList<BFSVertices> : vertices of the graph with depth information as from the starting vertex s
     */
    static LinkedList<BFSVertex> bfs(Graph g, Graph.Vertex s) {
        BFSVertex[] vertices = new BFSVertex[g.size()];
        Queue<Graph.Vertex> queue = new LinkedList<>(); // queue for performing BFS operation
        LinkedList<BFSVertex> traversalPath = new LinkedList<>();// keeps a trace of vertices in the order they are expanded
        for (Graph.Vertex v : g) {
            vertices[v.getName()] = new BFSVertex(v);
        }
        queue.add(s);
        vertices[s.getName()].initialiseBFS();
        while (!queue.isEmpty()) {
            Graph.Vertex u = queue.remove();
            BFSVertex curr = vertices[u.getName()];
            for (Graph.Edge e : u) {
                Graph.Vertex v = e.otherEnd(u);
                BFSVertex adj = vertices[v.getName()];
                if (!adj.isSeen()) {
                    queue.add(v);
                    adj.setSeen(true);
                    adj.setDepth(curr.getDepth() + 1);
                    adj.setParent(curr);
                }
            }
            traversalPath.add(curr);
        }
        return traversalPath;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in;
        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }
        Graph g = Graph.readGraph(in);
        cs6301.g00.Timer t = new cs6301.g00.Timer();
        t.start();
        LinkedList<BFSVertex> vertices = bfs(g, g.iterator().next());
        for (BFSVertex v : vertices) {
            System.out.println(v);
        }
        System.out.println(t.end());
    }
}
