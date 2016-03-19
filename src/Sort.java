/*****************************************************/
/*** Purpose: To implement sort algorithms and     ***/
/***          count the number of comparisons.     ***/
/***     Initial Author: Jason Steggles 20/09/15   ***/
/***     Extended by: Dylan McKee       16/10/15   ***/
/*****************************************************/

import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Scanner;

public class Sort {

    /**
     * Size of array
     **/
    private int size;

    /**
     * Number of used elements in array
     **/
    private int usedSize;

    /**
     * Array of integers
     **/
    private int[] A;

    /**
     * Global variables for counting sort comparisons
     **/

    /**
     * Global comparison count for Insertion Sort
     **/
    public int compIS;

    /**
     * Global comparison count for Quicksort
     **/
    public int compQS;

    /**
     * Global comparison count for New Sort
     **/
    public int compNewS;

    /*****************/
    /** Constructor **/
    /*****************/
    Sort(int max) {
        /** Initialiase global sort count variables **/
        compIS = 0;
        compQS = 0;
        compNewS = 0;

        /** Initialise size variables **/
        usedSize = 0;
        size = max;

        /** Create Array of Integers **/
        A = new int[size];


    }

    /*********************************************/
    /*** Read a file of integers into an array ***/
    /*********************************************/
    public void readIn(String file) {
        try {
            /** Initialise loop variable **/
            usedSize = 0;

            /** Set up file for reading **/
            FileReader reader = new FileReader(file);
            Scanner in = new Scanner(reader);

            /** Loop round reading in data while array not full **/
            while (in.hasNextInt() && (usedSize < size)) {
                A[usedSize] = in.nextInt();
                usedSize++;
            }

        } catch (IOException e) {
            System.out.println("Error processing file " + file);
        }
    }

    /**********************/
    /*** Display array  ***/
    /**********************/
    public void display(int line, String header) {
        /*** Integer Formatter - three digits ***/
        NumberFormat FI = NumberFormat.getInstance();
        FI.setMinimumIntegerDigits(3);

        /** Print header string **/
        System.out.print("\n" + header);

        /** Display array data **/
        for (int i = 0; i < usedSize; i++) {
            /** Check if new line is needed **/
            if (i % line == 0) {
                System.out.println();
            }

            /** Display an array element **/
            System.out.print(FI.format(A[i]) + " ");
        }
    }

    /***********************/
    /*** Insertion sort  ***/
    /***********************/
    public void insertionSort() {
        // Iterate through the array
        for (int i = 0; i < size; i++) {
            // The key is the item at the current index...
            int key = A[i];

            // And j is the current index (copied to another placeholder variable so that it can be safely mutated)...
            int j = i;

            // Loop whilst J's a valid array index and the key is smaller than the item on the left of J...
            while ((j > 0) && (key < A[j - 1])) {
                // ARRAY COMPARISON: 1 comparison involving array here
                compIS++;

                // Move A to the left once (because it's smaller than what's on its left)...
                A[j] = A[j - 1];

                // And decrement j to avoid an array 'underflow'
                j = j - 1;
            }
            // ARRAY COMPARISON: When the while condition above is false, 1 comparison is still made
            compIS++;

            // Set the value at index j to be equal to what was previously at A[i];
            A[j] = key;

        }
    }

    /******************************************************************************/
    /*** The Partition algorithm from the lecture notes, for use in quicksort   ***/
    /******************************************************************************/
    private int partition(int left, int right) {
        // Perform the partition...

        // The pivot element will be the rightmost element
        int pivot = A[right];

        // Set up the left and right pointers too...
        int leftPointer = left;
        int rightPointer = right;


        // While the entire array hasn't yet been traversed by the pointers, carry on moving them through it...
        while (leftPointer < rightPointer) {

            // While the left pointer is 'further left' (i.e. less than) the pivot element, keep moving it to the right, closer to the pivot...
            while (A[leftPointer] < pivot) {
                // ARRAY COMPARISON: 1 comparison involving array here
                compQS++;

                // Move the left pointer 'to the right' by incrementing it by 1
                leftPointer = leftPointer + 1;
            }
            // ARRAY COMPARISON: When the while condition above is false, 1 comparison is still made
            compQS++;

            // While the right pointer is greater than/equal to the pivot, and still further right than the left pointer (i.e. the pointers haven't yet crossed),
            // Keep on moving the right pointer 'to the left' by decrementing it, moving it closer to the left pointer.
            while ((A[rightPointer] >= pivot) && (rightPointer > left)) {
                // ARRAY COMPARISON: 1 comparison involving array here
                compQS++;

                // Move the right pointer 'to the left' by decrementing it
                rightPointer = rightPointer - 1;
            }
            // ARRAY COMPARISON: When the while condition above is false, 1 comparison is still made
            compQS++;

            // If the right pointer's still 'to the right' of (i.e. greater than) the left pointer, swap the elements around
            if (leftPointer < rightPointer) {
                // Perform the swap of elements...
                swap(leftPointer, rightPointer);

            }

        }

        // And perform another swap...
        swap(leftPointer, right);

        // Return the left-most pointer...
        return leftPointer;
    }


    /*****************************************************************************************/
    /*** A generic swap() method, for use in the quicksort and 'new sort' algorithms       ***/
    /*** to reduce code duplication and give a decent level of abstraction                 ***/
    /*****************************************************************************************/
    private void swap(int pL, int pR) {
        // Create the placeholder variables to hold the 'opposite' item during the swap
        int leftPositionPlaceholder = A[pL];
        int rightPositionPlaceholder = A[pR];

        // Perform the swap by exchanging the elements in the array, using the placeholders...
        A[pR] = leftPositionPlaceholder;
        A[pL] = rightPositionPlaceholder;

    }

    /************************************************************************************************/
    /*** A public method to expose the quicksort algorithm to the test class so it can be tested  ***/
    /************************************************************************************************/
    public void quickSort() {
        // Call quicksort on the entire array (a range of 0 to size-1 to avoid going out of bounds!)
        quickSort(0, size - 1);
    }

    /**********************************************************************************/
    /*** The actual internal private implementation of the quicksort algorithm      ***/
    /**********************************************************************************/
    private void quickSort(int L, int R) {
        // Only carry on the sort if the right pointer is greater than the left pointer
        if (R > L) {
            // Perform partition
            int p = partition(L, R);

            // And recursively sort both parts of the array using the calculated partition
            quickSort(L, p - 1);
            quickSort(p + 1, R);
        }
    }

    /************************************************************************************************/
    /*** A 'find min. from array position onwards' method for use by the newSort() algorithm      ***/
    /************************************************************************************************/
    private int findMinFrom(int[] array, int pos) {
        // Assume the minimum value in the array is the one we're looking onwards from initially
        int min = array[pos];

        // Iterate through the array only looking onwards from the current point (not including the current point), to the end of it
        for (int i = pos + 1; i < array.length; i++) {
            // ARRAY COMPARISON: 1 comparison involving array here (this gets incremented even when the if's false)
            compNewS++;
            if (array[i] < min) {
                // If the current array element is greater than the min, then set the min value equal to the current array element
                min = array[i];
            }
        }

        // And return whatever minimum value has been found
        return min;
    }

    /********************************************************************************************************/
    /*** An implementation of the mysterious 'new sort' algorithm, as described in the assignment brief   ***/
    /********************************************************************************************************/
    public void newSort() {
        // Start at index 0 in the array
        int currentPosition = 0;

        // Carry on the sorting loop only while we're in the array bounds
        while (currentPosition < size) {
            // Find the minimum in the array from the current position onwards
            int min = findMinFrom(A, currentPosition);

            // Iterate through the array
            for (int i = currentPosition; i < size; i++) {
                // Check if the current item being iterated is the smallest item...
                // ARRAY COMPARISON: 1 comparison involving array here (this gets incremented even when the if's false)
                compNewS++;
                if (A[i] == min) {
                    // If the current item being iterated is the smallest item, swap it with the current position of the outer while loop,
                    // ensuring that the smallest item is at the front (in resepect to the position of the outer loop's current position) of the array
                    swap(i, currentPosition);

                    // Iterate onto the next position by incrementing
                    currentPosition = currentPosition + 1;
                }
            }

        }
    }


}
/**
 * End of Sort Class
 **/