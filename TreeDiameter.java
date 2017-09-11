package cs6301.g32;

import cs6301.g00.Graph;
import cs6301.g00.Timer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author prady
 * Ver 1.0:    9/3/17
 */

public class TreeDiameter {

    /**
     * This calls BFS on Graph G with arbitrary source node and selects the last vertex traversed by BFS
     * as one end point: end1.
     * From that end point it call BFS again on the Graph G with end1 as the source and selects the last
     * vertex traversed by BFS as second end point: end2 of the diameter of the tree and finds the longest path from
     * end1 to end2.
     * This method will only return any one path forming the diameter.
     *
     * @param g : Graph : input graph whose diameter is to be found
     * @return : a list of vertices along the diameter
     */
    static LinkedList<Graph.Vertex> diameter(Graph g) {
        BFS.BFSVertex end1 = BFS.bfs(g, g.iterator().next()).getLast();
        BFS.BFSVertex end2 = BFS.bfs(g, end1.getVertex()).getLast();
        return getVerticesOnDiameter(end2);
    }

    /**
     * This method returns the vertices along the end points of the diameter.
     * @param end2 : BFS.BFSVertex : The second end point of the diameter.
     * @return : a list of vertices along the diameter.
     */
    private static LinkedList<Graph.Vertex> getVerticesOnDiameter(BFS.BFSVertex end2) {
        LinkedList<Graph.Vertex> path = new LinkedList<>();
        BFS.BFSVertex curr = end2;
        do{
            path.add(curr.getVertex());
            curr = curr.getParent();
        } while(curr != null);
        return path;
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
        assert g.size() != 0;
        Timer t = new Timer();
        t.start();
        LinkedList<Graph.Vertex> diameter = diameter(g);
        System.out.println("The diameter of the tree is " + diameter.size());
        System.out.println("the vertices along the diameter of the tree is " + diameter);
        System.out.println(t.end());
    }

}

/******************** Sample Output
$ java cs6301.g32.TreeDiameter
 16 15
 1 2 1
 1 3 1
 2 4 1
 2 5 1
 2 6 1
 5 7 1
 5 8 1
 8 9 1
 3 10 1
 10 11 1
 10 12 1
 11 13 1
 11 15 1
 15 16 1
 11 14 1
 The diameter of the tree is 10
 the vertices along the diameter of the tree is [9, 8, 5, 2, 1, 3, 10, 11, 15, 16]
 Time: 2 msec.
 Memory: 3 MB / 119 MB.

 ---------------------------------------------------------------------------------
 $ java cs6301.g32.TreeDiameter
 6 5
 5 6 1
 2 5 1
 2 4 1
 1 3 1
 1 2 1
 The diameter of the tree is 5
 the vertices along the diameter of the tree is [3, 1, 2, 5, 6]
 Time: 3 msec.
 Memory: 3 MB / 119 MB.


 */