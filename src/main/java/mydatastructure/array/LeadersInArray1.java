package mydatastructure.array;

/*
Link : https://www.geeksforgeeks.org/leaders-in-an-array/
Write a program to print all the LEADERS in the array.
An element is leader if it is greater than all the elements to its right side.
And the rightmost element is always a leader.
For example int the array {16, 17, 4, 3, 5, 2}, leaders are 17, 5 and 2.

Method 1 (Simple)
Use two loops. The outer loop runs from 0 to size – 1 and one by one picks
all elements from left to right. The inner loop compares the picked element to
all the elements to its right side. If the picked element is greater than all
the elements to its right side, then the picked element is the leader.

Output:

17 5 2

Time Complexity: O(n*n)

 */

class LeadersInArray1
{
    /*Java Function to print leaders in an array */
    void printLeaders(int arr[], int size)
    {
        for (int i = 0; i < size; i++)
        {
            int j;
            for (j = i + 1; j < size; j++)
            {
                if (arr[i] <= arr[j])
                    break;
            }
            if (j == size) // the loop didn't break
                System.out.print(arr[i] + " ");
        }
    }

    /* Driver program to test above functions */
    public static void main(String[] args)
    {
        LeadersInArray1 lead = new LeadersInArray1();
        int arr[] = new int[]{16, 17, 4, 3, 5, 2};
        int n = arr.length;
        lead.printLeaders(arr, n);
    }
}