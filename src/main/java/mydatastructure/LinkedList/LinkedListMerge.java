package mydatastructure.LinkedList;
/*

Merge two sorted linked lists such that merged list is in reverse order

Given two linked lists sorted in increasing order. Merge them such a way that the result list is in decreasing order (reverse order).

Examples:

Input:  a: 5->10->15->40
        b: 2->3->20
Output: res: 40->20->15->10->5->3->2

Input:  a: NULL
        b: 2->3->20
Output: res: 20->3->2



A Simple Solution is to do following.
1) Reverse first list ‘a’.
2) Reverse second list ‘b’.
3) Merge two reversed lists.

Another Simple Solution is first Merge both lists, then reverse the merged list.

Both of the above solutions require two traversals of linked list.

How to solve without reverse, O(1) auxiliary space (in-place) and only one traversal of both lists?
The idea is to follow merge style process. Initialize result list as empty. Traverse both lists from beginning to end. Compare current nodes of both lists and insert smaller of two at the beginning of the result list.

1) Initialize result list as empty: res = NULL.
2) Let 'a' and 'b' be heads first and second lists respectively.
3) While (a != NULL and b != NULL)
    a) Find the smaller of two (Current 'a' and 'b')
    b) Insert the smaller value node at the front of result.
    c) Move ahead in the list of smaller node.
4) If 'b' becomes NULL before 'a', insert all nodes of 'a'
   into result list at the beginning.
5) If 'a' becomes NULL before 'b', insert all nodes of 'a'
   into result list at the beginning.
 */



class LinkedListMerge {

    Node head;  // head of list
    static Node a, b;

    /* Node Class */
    static class Node {

        int data;
        Node next;

        // Constructor to create a new node
        Node(int d) {
            data = d;
            next = null;
        }
    }

    void printlist(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    Node sortedmerge(Node node1, Node node2) {

        // if both the nodes are null
        if (node1 == null && node2 == null) {
            return null;
        }

        // resultant node
        Node res = null;

        // if both of them have nodes present traverse them
        while (node1 != null && node2 != null) {

            // Now compare both nodes current data
            if (node1.data <= node2.data) {
                Node temp = node1.next;
                node1.next = res;
                res = node1;
                node1 = temp;
            } else {
                Node temp = node2.next;
                node2.next = res;
                res = node2;
                node2 = temp;
            }
        }

        // If second list reached end, but first list has
        // nodes. Add remaining nodes of first list at the
        // front of result list
        while (node1 != null) {
            Node temp = node1.next;
            node1.next = res;
            res = node1;
            node1 = temp;
        }

        // If first list reached end, but second list has
        // node. Add remaining nodes of first list at the
        // front of result list
        while (node2 != null) {
            Node temp = node2.next;
            node2.next = res;
            res = node2;
            node2 = temp;
        }

        return res;

    }

    public static void main(String[] args) {

        LinkedListMerge list = new LinkedListMerge();
        Node result = null;

        /*Let us create two sorted linked lists to test
         the above functions. Created lists shall be
         a: 5->10->15
         b: 2->3->20*/
        list.a = new Node(5);
        list.a.next = new Node(10);
        list.a.next.next = new Node(15);

        list.b = new Node(2);
        list.b.next = new Node(3);
        list.b.next.next = new Node(20);

        System.out.println("List a before merge :");
        list.printlist(a);
        System.out.println("");
        System.out.println("List b before merge :");
        list.printlist(b);

        // merge two sorted linkedlist in decreasing order
        result = list.sortedmerge(a, b);
        System.out.println("");
        System.out.println("Merged linked list : ");
        list.printlist(result);

    }
}