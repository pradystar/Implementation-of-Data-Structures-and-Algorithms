package cs6301.g32;

import cs6301.g00.Timer;

import java.util.Scanner;

/**
 * @author prady
 * Ver 1.0:    9/10/17
 */

public class MutiUnzipTest {
    public static void main(String[] args) {
        int n = 10;
        SinglyLinkedList<Integer> lst = new SinglyLinkedList<>();
        for (int i = 1; i <= n; i++) {
            lst.add(new Integer(i));
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Original list: ");
        lst.printList();
        System.out.println("Enter chaining factor");
        int k = in.nextInt();
        Timer t = new Timer();
        lst.multiUnzip(k);
        System.out.println("Rearranged list: ");
        lst.printList();
        System.out.println(t.end());

    }
}
