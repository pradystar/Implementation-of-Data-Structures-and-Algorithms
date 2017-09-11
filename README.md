# Implementation of Data Structures and Algorithms
This repo contains java programs for the course Implementation of Advanced Data Structures and Algorithms

# Getting Started
To run any of the programs be sure that you have the right packgage structure and all the required files. Below are details of all the programs, I have included the name of the main file in the heading. Almost all the programs will use Timer.java so download it and if a code uses any other java file I will mention it in the description. The programs in the package g00 are all provided by the instructor.

## Merge Sort on generic Arrays - MergeSort.java
Implementation of merge sort algorithm on generic arrays and on an int array

   Signatures:
   
    // tmp array is used to store values during the merge operation.
    static<T extends Comparable<? super T>> void mergeSort(T[] arr, T[] tmp) { ... }
    
    static void mergeSort(int[] arr, int[] tmp) { ... }
    
    static<T extends Comparable<? super T>> void nSquareSort(T[] arr) { ... }
   
## Find diameter of a tree - TreeDiameter.java
Implementation breadth-first search (BFS), and solve the problem of finding the diameter of a tree that works as follows: 
Run BFS, starting at an arbitrary node as root.  Let u be a node at maximum distance from the root. Run BFS again, with u as the root. Output diameter: path from u to a node at maximum distance from u.

   Signature:
   
    // Return a longest path in g.  Algorithm is correct only if g is a tree.
    static LinkedList<Graph.Vertex> diameter(Graph g) { ... }
   
   Additionnal Files:
   * BFS.java
   * Graph.java

## Set Operations - SetOperations.java
This code provides the following set operations, all the opeartions are O(n) time and space. The list L1 and L2 are linked lists implementing sorted sets
   
    // Returns elements common to l1 and l2 in sorted order in the outList  
    public static<T extends Comparable<? super T>> void intersect(List<T> l1, List<T> l2, List<T> outList) { ... }
   
    // Returns union of l1 and l2 in sorted order in outList
    public static<T extends Comparable<? super T>> void union(List<T> l1, List<T> l2, List<T> outList) { ... }

    // Returns items in l1 that are not in l2 in sorted order in outList
    public static<T extends Comparable<? super T>> void difference(List<T> l1, List<T> l2, List<T> outList) { ... }
  
## Merge Sort on Linked List in O(lg n) extra space - SortableList.java
Merge sort on Linked List using only O(lg n) extra space for recursive calls

    // Merges this list with otherList
    void merge(SortableList<T> otherList) { ... }
    
    // Recursively divides this list
    void mergeSort() { ... }
    
    // Function used to externally sort the list
    public static<T extends Comparable<? super T>> void mergeSort(SortableList<T> list) {
	    list.mergeSort();
    }
    
   Additonal Files:
   * g32/SinglyLinkedList.java

## MultiUnzip - g32/SinglyLinkedList.java
Rearrange elements of a singly linked list by chaining together elements that are k apart. If the list has elements 1..10 in order, after multiUnzip(3), the elements will be rearranged as: 1 4 7 10 2 5 8 3 6 9.

    void multiUnzip(int k) { ... }
    
## Reverse Sinlgy Linked List - g32/SinglyLinkedList.java
Recursive and iterative versions of reversing a list and printing a list in reverse order without actually reversing it

    // This method reverses the list by making recursive calls
    public void rreverse() { ... }
    
    // This method reverses the list iteratively
    public void ireverse() { ... }
    
    // This method prints the list in reverse recursively without actually reversing it
    public void rprint_reverse() { ... }
    
    // This method prints the list in reverse without actually reversing it
    public void iprint_reverse() { ... }
    
## Resizable Array based queues (generic) - Queue.java
Implementation of methods offer, poll, peek, isEmpty with same behaviour as Java's Queue interface. The queue is resizable such that if the queue is more than 90% full then the size is doubled or if the queue is less than 25% occupied the halves it. At any point of time the queue will always have a minimum size of 16.
