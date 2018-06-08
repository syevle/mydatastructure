package mydatastructure.LinkedList;
/*

Write a function to get the intersection point of two Linked Lists.

There are two singly linked lists in a system. By some programming error the end node of
one of the linked list got linked into the second list, forming a inverted Y shaped list.
Write a program to get the point where two linked list merge.


Method 1(Simply use two loops)
Use 2 nested for loops. Outer loop will be for each node of the 1st list
and inner loop will be for 2nd list.
In the inner loop, check if any of nodes of 2nd list is same as the current node of
first linked list. Time complexity of this method will be O(mn) where m and n are the number
of nodes in two lists.

Method 2 (Mark Visited Nodes)
This solution requires modifications to basic linked list data structure.
Have a visited flag with each node. Traverse the first linked list
and keep marking visited nodes. Now traverse second linked list,
If you see a visited node again then there is an intersection point,
return the intersecting node. This solution works in O(m+n)
but requires additional information with each node.
A variation of this solution that doesn’t require modification to basic data structure
can be implemented using hash. Traverse the first linked list and store
the addresses of visited nodes in a hash. Now traverse the second linked list and
if you see an address that already exists in hash then return the intersecting node.

Method 3(Using difference of node counts)
1) Get count of the nodes in first list, let count be c1.
2) Get count of the nodes in second list, let count be c2.
3) Get the difference of counts d = abs(c1 – c2)
4) Now traverse the bigger list from the first node till d nodes so that
from here onwards both the lists have equal no of nodes.
5) Then we can traverse both the lists in parallel till we come across a common node.
(Note that getting a common node is done by comparing the address of the nodes)
 */



class LinkedListIntersection {

    static Node head1, head2;

    static class Node {

        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    /*function to get the intersection point of two linked
    lists head1 and head2 */
    int getNode() {
        int c1 = getCount(head1);
        int c2 = getCount(head2);
        int d;

        if (c1 > c2) {
            d = c1 - c2;
            return _getIntesectionNode(d, head1, head2);
        } else {
            d = c2 - c1;
            return _getIntesectionNode(d, head2, head1);
        }
    }

    /* function to get the intersection point of two linked
     lists head1 and head2 where head1 has d more nodes than
     head2 */
    int _getIntesectionNode(int d, Node node1, Node node2) {
        int i;
        Node current1 = node1;
        Node current2 = node2;
        for (i = 0; i < d; i++) {
            if (current1 == null) {
                return -1;
            }
            current1 = current1.next;
        }
        while (current1 != null && current2 != null) {
            if (current1.data == current2.data) {
                return current1.data;
            }
            current1 = current1.next;
            current2 = current2.next;
        }

        return -1;
    }

    /*Takes head pointer of the linked list and
    returns the count of nodes in the list */
    int getCount(Node node) {
        Node current = node;
        int count = 0;

        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }

    public static void main(String[] args) {
        LinkedListIntersection list = new LinkedListIntersection();

        // creating first linked list
        list.head1 = new Node(3);
        list.head1.next = new Node(6);
        list.head1.next.next = new Node(15);
        list.head1.next.next.next = new Node(15);
        list.head1.next.next.next.next = new Node(30);

        // creating second linked list
        list.head2 = new Node(10);
        list.head2.next = new Node(15);
        list.head2.next.next = new Node(30);

        System.out.println("The node of intersection is " + list.getNode());

    }
}
