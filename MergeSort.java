package cs6301.g32;

/*
 * @author prady
 * Ver 1.0:    9/1/17
 */

import cs6301.g00.Timer;

import java.util.Arrays;

public class MergeSort {

    /**
     * Helper Procedure for Merge Sort on an int array.
     *
     * @param arr : int array. Array to be sorted.
     * @param tmp : int array. Temporary array used to store value during the merge operation.
     */
    static void mergeSort(int[] arr, int[] tmp) {
        mergeSort(arr, tmp, 0, arr.length - 1);
    }

    /**
     * @param arr   : int array. Array to be sorted.
     * @param tmp   : int array. Temporary array used to store value during the merge operation.
     * @param start : int : Start index of the array.
     * @param end   : int : End index of the array.
     */
    private static void mergeSort(int[] arr, int[] tmp, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(arr, tmp, start, mid);
            mergeSort(arr, tmp, mid + 1, end);
            merge(arr, tmp, start, mid, end);
        }
    }

    /**
     * This Procedure merges two sorted arrays into a single sorted array
     *
     * @param arr   : int array. Array to be sorted
     * @param tmp   : int array. Array to store elements temporarily during the merge procedure
     * @param start : int : start index for left array
     * @param mid   : int : end of left array
     * @param end   : int : end of right array
     */
    private static void merge(int[] arr, int[] tmp, int start, int mid, int end) {
        int i = start, k = start;
        int j = mid + 1;
        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                tmp[k++] = arr[i++];
            } else {
                tmp[k++] = arr[j++];
            }
        }
        // now copy the remaining elements from the left and the right array
        while (i <= mid) {
            tmp[k++] = arr[i++];
        }
        while (j <= end) {
            tmp[k++] = arr[j++];
        }
        // now copy from temporary array onto the main array
        System.arraycopy(tmp, start, arr, start, end - start + 1);
    }

    /**
     * Helper Procedure for Merge Sort on an Generic array of Type that is Comparable using natural ordering.
     *
     * @param arr : generic array. Array to be sorted.
     * @param tmp : generic array. Temporary array used to store value during the merge operation.
     */
    static <T extends Comparable<? super T>> void mergeSort(T[] arr, T[] tmp) {
        mergeSort(arr, tmp, 0, arr.length - 1);
    }

    /**
     * @param arr   : generic array. Array to be sorted.
     * @param tmp   : generic array. Temporary array used to store value during the merge operation.
     * @param start : int : Start index of the array.
     * @param end   : int : End index of the array.
     */
    private static <T extends Comparable<? super T>> void mergeSort(T[] arr, T[] tmp, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(arr, tmp, start, mid);
            mergeSort(arr, tmp, mid + 1, end);
            merge(arr, tmp, start, mid, end);
        }
    }

    /**
     * This Procedure merges two sorted arrays into a single sorted array
     *
     * @param arr   : int array. Array to be sorted
     * @param tmp   : int array. Array to store elements temporarily during the merge procedure
     * @param start : int : start index for left array
     * @param mid   : int : end of left array
     * @param end   : int : end of right array
     */
    private static <T extends Comparable<? super T>> void merge(T[] arr, T[] tmp, int start, int mid, int end) {
        int i = start, k = start;
        int j = mid + 1;
        while (i <= mid && j <= end) {
            if (arr[i].compareTo(arr[j]) <= 0) {
                tmp[k++] = arr[i++];
            } else {
                tmp[k++] = arr[j++];
            }
        }
        // now copy the remaining elements from the left and the right array
        while (i <= mid) {
            tmp[k++] = arr[i++];
        }
        while (j <= end) {
            tmp[k++] = arr[j++];
        }
        // now copy from temporary array onto the main array
        System.arraycopy(tmp, start, arr, start, end - start + 1);
    }

    /**
     * This procedure sorts a generic array whose elements are comparable by natural ordering using insertion sort
     *
     * @param arr : array to be sorted
     * @param <T> : Type; comparable by natural ordering
     */
    static <T extends Comparable<? super T>> void nSquareSort(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            T key = arr[i];
            while (j >= 0 && arr[j].compareTo(key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{3,1,6, 75,23,23,43, 2};
        System.out.println(Arrays.toString(a));
    }
}
