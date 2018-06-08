package mydatastructure.LinkedList;

import java.util.HashSet;

// A complete working Java program to demonstrate all insertion methods
// on linked list
class LinkedList
{
    static Node head;  // head of list

    /* Linked list Node*/
    class Node
    {
        int data;
        Node next;
        Node(int d) {data = d; next = null; }
    }

    /* Inserts a new Node at front of the list. */
    public void push(int new_data)
    {
        /* 1 & 2: Allocate the Node &
                  Put in the data*/
        Node new_node = new Node(new_data);

        /* 3. Make next of new Node as head */
        new_node.next = head;

        /* 4. Move the head to point to new Node */
        head = new_node;
    }

    /* Inserts a new node after the given prev_node. */
    public void insertAfter(Node prev_node, int new_data)
    {
        /* 1. Check if the given Node is null */
        if (prev_node == null)
        {
            System.out.println("The given previous node cannot be null");
            return;
        }

        /* 2 & 3: Allocate the Node &
                  Put in the data*/
        Node new_node = new Node(new_data);

        /* 4. Make next of new Node as next of prev_node */
        new_node.next = prev_node.next;

        /* 5. make next of prev_node as new_node */
        prev_node.next = new_node;
    }

    /* Appends a new node at the end.  This method is
       defined inside LinkedList class shown above */
    public void append(int new_data)
    {
        /* 1. Allocate the Node &
           2. Put in the data
           3. Set next as null */
        Node new_node = new Node(new_data);

        /* 4. If the Linked List is empty, then make the
              new node as head */
        if (head == null)
        {
            head = new Node(new_data);
            return;
        }

        /* 4. This new node is going to be the last node, so
              make next of it as null */
        new_node.next = null;

        /* 5. Else traverse till the last node */
        Node last = head;
        while (last.next != null)
            last = last.next;

        /* 6. Change the next of last node */
        last.next = new_node;
        return;
    }

    /* This function prints contents of linked list starting from
        the given node */
    public void printList()
    {
        Node tnode = head;
        while (tnode != null)
        {
            System.out.print(tnode.data+" ");
            tnode = tnode.next;
        }
    }

    /* Given a key, deletes the first occurrence of key in linked list */
    void deleteNode(int key)
    {
        // Store head node
        Node temp = head, prev = null;

        // If head node itself holds the key to be deleted
        if (temp != null && temp.data == key)
        {
            head = temp.next; // Changed head
            return;
        }

        // Search for the key to be deleted, keep track of the
        // previous node as we need to change temp.next
        while (temp != null && temp.data != key)
        {
            prev = temp;
            temp = temp.next;
        }

        // If key was not present in linked list
        if (temp == null) return;

        // Unlink the node from linked list
        prev.next = temp.next;
    }


    /* Given a reference (pointer to pointer) to the head of a list
       and a position, deletes the node at the given position */
    void deleteNodeByPosition(int position)
    {
        // If linked list is empty
        if (head == null)
            return;

        // Store head node
        Node temp = head;

        // If head needs to be removed
        if (position == 0)
        {
            head = temp.next;   // Change head
            return;
        }

        // Find previous node of the node to be deleted
        for (int i=0; temp!=null && i<position-1; i++)
            temp = temp.next;

        // If position is more than number of ndoes
        if (temp == null || temp.next == null)
            return;

        // Node temp->next is the node to be deleted
        // Store pointer to the next of node to be deleted
        Node next = temp.next.next;

        temp.next = next;  // Unlink the deleted node from list
    }

    /* Function deletes the entire linked list */
    void deleteList()
    {
        head = null;
    }

    /* Returns count of nodes in linked list */
    public int getCount()
    {
        Node temp = head;
        int count = 0;
        while (temp != null)
        {
            count++;
            temp = temp.next;
        }
        return count;
    }

    //Checks whether the value x is present in linked list
    public boolean search(Node head, int x)
    {
        Node current = head;    //Initialize current
        while (current != null)
        {
            if (current.data == x)
                return true;    //data found
            current = current.next;
        }
        return false;    //data not found
    }

    /* Takes index as argument and return data at index*/
    public int GetNth(int index)
    {
        Node current = head;
        int count = 0; /* index of Node we are
                          currently looking at */
        while (current != null)
        {
            if (count == index)
                return current.data;
            count++;
            current = current.next;
        }

        /* if we get to this line, the caller was asking
        for a non-existent element so we assert fail */
        assert(false);
        return 0;
    }

    /* Function to get the nth node from the last of a
       linked list */
    void printNthFromLast(int n)
    {
        int len = 0;
        Node temp = head;

        // 1) count the number of nodes in Linked List
        while (temp != null)
        {
            temp = temp.next;
            len++;
        }

        // check if value of n is not more than length of
        // the linked list
        if (len < n)
            return;

        temp = head;

        // 2) get the (len-n+1)th node from the begining
        for (int i = 1; i < len-n+1; i++)
            temp = temp.next;

        System.out.println(temp.data);
    }

    /* Function to print middle of linked list */
    void printMiddle()
    {
        Node slow_ptr = head;
        Node fast_ptr = head;
        if (head != null)
        {
            while (fast_ptr != null && fast_ptr.next != null)
            {
                fast_ptr = fast_ptr.next.next;
                slow_ptr = slow_ptr.next;
            }
            System.out.println("The middle element is [" +
                    slow_ptr.data + "] \n");
        }
    }

    /* Counts the no. of occurences of a node
    (search_for) in a linked list (head)*/
    int countOfelement(int search_for)
    {
        Node current = head;
        int count = 0;
        while (current != null)
        {
            if (current.data == search_for)
                count++;
            current = current.next;
        }
        return count;
    }

    // Returns true if there is a loop in linked
    // list else returns false.
    static boolean detectLoop(Node h)
    {
        HashSet<Node> s = new HashSet<Node>();
        while (h != null)
        {
            // If we have already has this node
            // in hashmap it means their is a cycle
            // (Because you we encountering the
            // node second time).
            if (s.contains(h))
                return true;

            // If we are seeing the node for
            // the first time, insert it in hash
            s.add(h);

            h = h.next;
        }

        return false;
    }

    /* Function to swap Nodes x and y in linked list by
       changing links */
    public void swapNodes(int x, int y)
    {
        /**
         * This may look a simple problem, but is interesting question as
            it has following cases to be handled.
         1) x and y may or may not be adjacent.
         2) Either x or y may be a head node.
         3) Either x or y may be last node.
         4) x and/or y may not be present in linked list.

         How to write a clean working code that handles all of the above possibilities.

         We strongly recommend to minimize your browser and try this yourself first.

         The idea it to first search x and y in given linked list.
         If any of them is not present, then return.
         While searching for x and y, keep track of current and previous pointers.
         First change next of previous pointers, then change next of current pointers.
         */

        // Nothing to do if x and y are same
        if (x == y) return;

        // Search for x (keep track of prevX and CurrX)
        Node prevX = null, currX = head;
        while (currX != null && currX.data != x)
        {
            prevX = currX;
            currX = currX.next;
        }

        // Search for y (keep track of prevY and currY)
        Node prevY = null, currY = head;
        while (currY != null && currY.data != y)
        {
            prevY = currY;
            currY = currY.next;
        }

        // If either x or y is not present, nothing to do
        if (currX == null || currY == null)
            return;

        // If x is not head of linked list
        if (prevX != null)
            prevX.next = currY;
        else //make y the new head
            head = currY;

        // If y is not head of linked list
        if (prevY != null)
            prevY.next = currX;
        else // make x the new head
            head = currX;

        // Swap next pointers
        Node temp = currX.next;
        currX.next = currY.next;
        currY.next = temp;
        /*
        Optimizations: The above code can be optimized to search x and y in single traversal.
        Two loops are used to keep program simple.
         */
    }
/*

Move last element to front of a given Linked List

Write a C function that moves last element to front in a given Singly Linked List. For example, if the given Linked List is 1->2->3->4->5, then the function should change the list to 5->1->2->3->4.

Algorithm:
Traverse the list till last node. Use two pointers: one to store the address of last node and other for address of second last node. After the end of loop do following operations.
i) Make second last as last (secLast->next = NULL).
ii) Set next of last as head (last->next = *head_ref).
iii) Make last as head ( *head_ref = last)
 */
    void moveToFront()
    {
        /* If linked list is empty or it contains only
           one node then simply return. */
        if(head == null || head.next == null)
            return;

        /* Initialize second last and last pointers */
        Node secLast = null;
        Node last = head;

        /* After this loop secLast contains address of
           second last  node and last contains address of
           last node in Linked List */
        while (last.next != null)
        {
            secLast = last;
            last = last.next;
        }

        /* Set the next of second last as null */
        secLast.next = null;

        /* Set the next of last as head */
        last.next = head;

        /* Change head to point to last node. */
        head = last;
    }


    void pairWiseSwap()
    {

        /**
         *
         Pairwise swap elements of a given linked list

         Given a singly linked list, write a function to swap elements pairwise.
         For example, if the linked list is 1->2->3->4->5
         then the function should change it to 2->1->4->3->5,
         and if the linked list is 1->2->3->4->5->6
         then the function should change it to 2->1->4->3->6->5.
         */
        Node temp = head;

        /* Traverse only till there are atleast 2 nodes left */
        while (temp != null && temp.next != null) {

            /* Swap the data */
            int k = temp.data;
            temp.data = temp.next.data;
            temp.next.data = k;
            temp = temp.next.next;
        }
    }

/*
Algorithm:
…1) Get pointer to the last node.
…2) Move all the odd nodes to the end.
……..a) Consider all odd nodes before the first even node and move them to end.
……..b) Change the head pointer to point to the first even node.
……..b) Consider all odd nodes after the first even node and move them to the end.
 */
    void segregateEvenOdd()
    {
        Node end = head;
        Node prev = null;
        Node curr = head;

        /* Get pointer to last Node */
        while (end.next != null)
            end = end.next;

        Node new_end = end;

        // Consider all odd nodes before getting first eve node
        while (curr.data %2 !=0 && curr != end)
        {
            new_end.next = curr;
            curr = curr.next;
            new_end.next.next = null;
            new_end = new_end.next;
        }

        // do following steps only if there is an even node
        if (curr.data %2 ==0)
        {
            head = curr;

            // now curr points to first even node
            while (curr != end)
            {
                if (curr.data % 2 == 0)
                {
                    prev = curr;
                    curr = curr.next;
                }
                else
                {
                    /* Break the link between prev and curr*/
                    prev.next = curr.next;

                    /* Make next of curr as null */
                    curr.next = null;

                    /*Move curr to end */
                    new_end.next = curr;

                    /*Make curr as new end of list */
                    new_end = curr;

                    /*Update curr pointer */
                    curr = prev.next;
                }
            }
        }

        /* We have to set prev before executing rest of this code */
        else prev = curr;

        if (new_end != end && end.data %2 != 0)
        {
            prev.next = end.next;
            end.next = null;
            new_end.next = end;
        }
    }

    /*

    Initialize three pointers prev as NULL, curr as head and next as NULL.
    Iterate trough the linked list. In loop, do following.

        // Before changing next of current,
        // store next node
        next = curr->next

        // This is where actual reversing happens
        curr->next = prev

        // Move prev and curr one step forward
        prev = curr
        curr = next


     */
    /* Function to reverse the linked list */
    Node reverse(Node node) {
        Node prev = null;
        Node current = node;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        node = prev;
        return node;
    }


    /* Function to print reverse of linked list without actually reversing
    *
    * Given a linked list, print reverse of it using a recursive function.
    * For example, if the given linked list is 1->2->3->4, then output should be 4->3->2->1.
    * */
    void printReverse(Node head)
    {
        if (head == null) return;

        // print list of head node
        printReverse(head.next);

        // After everything else is printed
        System.out.print(head.data+" ");
    }



    /* Driver program to test above functions. Ideally this function
       should be in a separate user class.  It is kept here to keep
       code compact */
    public static void main(String[] args)
    {
        /* Start with the empty list */
        LinkedList llist = new LinkedList();

        // Insert 6.  So linked list becomes 6->NUllist
        llist.append(6);

        // Insert 7 at the beginning. So linked list becomes
        // 7->6->NUllist
        llist.push(7);

        // Insert 1 at the beginning. So linked list becomes
        // 1->7->6->NUllist
        llist.push(1);

        // Insert 4 at the end. So linked list becomes
        // 1->7->6->4->NUllist
        llist.append(4);

        // Insert 8, after 7. So linked list becomes
        // 1->7->8->6->4->NUllist
        llist.insertAfter(llist.head.next, 8);

        System.out.println("\nCreated Linked list is: ");
        llist.printList();

        llist.push(7);
        llist.push(1);
        llist.push(3);
        llist.push(2);

        System.out.println("\nCreated Linked list is:");
        llist.printList();

        llist.deleteNode(1); // Delete node at position 4

        System.out.println("\nLinked List after Deletion at position 4:");
        llist.printList();

        llist.deleteNodeByPosition(4);  // Delete node at position 4

        System.out.println("\nLinked List after Deletion at position 4: ");
        llist.printList();

        System.out.println("Count of nodes is " +
                llist.getCount());

        /*Use push() to construct below list
        14->21->11->30->10  */
        llist.push(10);
        llist.push(30);
        llist.push(11);
        llist.push(10);
        llist.push(21);
        llist.push(14);
        System.out.println("Element at index 3 is "+llist.GetNth(3));
        if (llist.search(llist.head, 21))
            System.out.println("Yes");
        else
            System.out.println("No");


        /* Check the count function */
        System.out.println("Element at index 3 is "+llist.GetNth(3));
        System.out.println("Element at index 1000 is "+llist.GetNth(1000));

        llist.printNthFromLast(4);

        llist.printMiddle();

        /*Checking count function*/
        System.out.println("Count of 10 is "+llist.countOfelement(10));

        System.out.print("\n Linked list before calling swapNodes() ");
        llist.printList();

        llist.swapNodes(4, 3);

        System.out.print("\n Linked list after calling swapNodes() ");
        llist.printList();

        System.out.println("Linked List before calling pairWiseSwap() ");
        llist.printList();

        llist.pairWiseSwap();

        System.out.println("Linked List after calling pairWiseSwap() ");
        llist.printList();


        System.out.println("Linked List before moving last to front ");
        llist.printList();

        llist.moveToFront();

        System.out.println("Linked List after moving last to front ");
        llist.printList();

        System.out.println("Origional Linked List :: segregateEvenOdd");
        llist.printList();

        llist.segregateEvenOdd();

        System.out.println("Modified Linked List :: segregateEvenOdd");
        llist.printList();


        System.out.println("Given Linked list");
        llist.printList();
        head = llist.reverse(head);
        System.out.println("");
        System.out.println("Reversed linked list ");
        llist.printList();

        System.out.println("Given Linked list Reverse using a recursive function.");
        llist.printList();
        llist.printReverse(head);
        System.out.println("");
        System.out.println("Reversed linked list Reverse using a recursive function.");
        llist.printList();

//        /*Create loop for testing */
//        llist.head.next.next.next.next = llist.head;
//
//        if (detectLoop(head))
//            System.out.println("Loop found");
//        else
//            System.out.println("No Loop");

    }
}