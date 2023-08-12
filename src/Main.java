import java.util.Scanner;
public class Main {
    // Recursive method to perform merge sort and count inversions
    public static int mergeSort(int[] arr, int left, int right) {
        int inversions = 0;
        if (left < right) {
            int mid = (left + right) / 2;
            inversions += mergeSort(arr, left, mid);             // Count inversions in left subarray
            inversions += mergeSort(arr, mid + 1, right);   // Count inversions in right subarray
            inversions += merge(arr, left, mid, right);         // Merge and count split inversions
        }
        return inversions;
    }
    // Method to merge two sorted arrays and count split inversions
    public static int merge(int[] arr, int left, int mid, int right) {
        int inversions = 0;
        int[] temp = new int[right - left + 1];
        int i = left;       // Pointer for the left subarray
        int j = mid + 1;    // Pointer for the right subarray
        int k = 0;          // Pointer for the temporary array
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];   // Place the smaller element in the temporary array
            } else {
                inversions += (mid - i + 1); // Count inversions as all remaining elements in left subarray are greater
                temp[k++] = arr[j++];   // Place the smaller element in the temporary array
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];   // Copy remaining elements from left subarray
        }
        while (j <= right) {
            temp[k++] = arr[j++];   // Copy remaining elements from right subarray
        }
        for (int p = left; p <= right; p++) {
            arr[p] = temp[p - left];       // Copy elements back to the original array
        }
        return inversions;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the length of array:");
        int length = input.nextInt();
        int[] array = new int[length];
        System.out.println("Enter the elements inside array:");
        for (int i = 0; i < length; i++) {
            array[i] = input.nextInt();
        }
        System.out.println("Number of inversions: " + mergeSort(array, 0, array.length - 1));
    }
}

