package queue;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Queue q = new LinkedQueue();
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (sc.next().equals("+")) {
                System.out.print("prev size: " + q.size());
                q.enqueue(sc.nextInt());
                System.out.println(" new size: " + q.size());
            } else {
                System.out.println("prev size: " + q.size() + " element: " + q.dequeue() + " new size: " + q.size());
            }
        }
    }
}
