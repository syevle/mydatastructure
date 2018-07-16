package mydatastructure.array;
/*
Link : https://www.geeksforgeeks.org/leaders-in-an-array/
Method 2 (Scan from right)
Scan all the elements from right to left in array and keep track of maximum
till now. When maximum changes it’s value, print it.

Time Complexity: O(n)
 */
public class LeadersInArray2 {
    /* Java Function to print leaders in an array */
    void printLeaders(int arr[], int size)
    {
        int max_from_right =  arr[size-1];

        /* Rightmost element is always leader */
        System.out.print(max_from_right + " ");

        for (int i = size-2; i >= 0; i--)
        {
            if (max_from_right < arr[i])
            {
                max_from_right = arr[i];
                System.out.print(max_from_right + " ");
            }
        }
    }

    /* Driver program to test above functions */
    public static void main(String[] args)
    {
        LeadersInArray2 lead = new LeadersInArray2();
        int arr[] = new int[]{16, 17, 4, 3, 5, 2};
        int n = arr.length;
        lead.printLeaders(arr, n);
    }
}


