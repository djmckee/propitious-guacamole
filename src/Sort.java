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
    public int compIS;
    /**
     * Global comparison count for Insertion Sort
     **/
    public int compQS;
    /**
     * Global comparison count for Quicksort
     **/
    public int compNewS; /** Global comparison count for new sort **/

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

            // And j is the current index...
            int j = i;

            // Loop whilst J's a valid array index and the key is smaller than the item on the left of J...
            // ARRAY COMPARISON: 1 comparison involving array here
            compIS++;
            while ((j > 0) && (key < A[j - 1])) {
                // Move A to the left once (because it's smaller than what's on its left)...
                A[j] = A[j - 1];

                // And decrement j to avoid an array 'underflow'
                j = j - 1;
            }

            // Set the value at index j to be equal to what was previously at A[i];
            A[j] = key;

        }
    }

    /******************************************************************************/
    /*** The Partition algorithm from the lecture notes, for use in quicksort   ***/
    /******************************************************************************/
    private int partition(int left, int right) {
        // Perform the partition...
        int p;

        int v = A[right];
        int pL = left;
        int pR = right;


        while (pL < pR) {

            // ARRAY COMPARISON: 1 comparison involving array here
            compQS++;
            while (A[pL] < v) {
                pL = pL + 1;
            }

            // ARRAY COMPARISON: 1 comparison involving array here
            compQS++;
            while ((A[pR] >= v) && (pR > left)) {
                pR = pR - 1;
            }

            if (pL < pR) {
                // Perform the swap of elements...
                swap(pL, pR);

            }

        }

        // And perform another swap...
        swap(pL, right);

        return pL;
    }


    /*****************************************************************************************/
    /*** A generic swap() method, for use in the quicksort and 'new sort' algorithms       ***/
    /*** to reduce code duplication and give a decent level of abstraction                 ***/
    /*****************************************************************************************/
    private void swap(int pL, int pR) {
        // Create the placeholder variables to hold the 'opposite' item during the swap
        int pLPlaceholder = A[pL];
        int pRPlaceholder = A[pR];

        // Perform the swap
        A[pR] = pLPlaceholder;
        A[pL] = pRPlaceholder;

    }

    /***********************/
    /*** Quick sort      ***/
    /***********************/
    private void quickSort(int L, int R) {
        if (R > L) {

            // Perform partition
            int p = partition(L, R);

            // And recursively sort both parts of the array
            quickSort(L, p - 1);
            quickSort(p + 1, R);
        }
    }

    public void performQuicksort() {
        quickSort(0, size - 1);
    }


    private int findMinFrom(int[] array, int pos) {
        int min = array[pos];
        for (int i = pos + 1; i < array.length; i++){
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;
    }

    public void newSort() {
        int pos = 0;
        while (pos < size) {
            int min = findMinFrom(A, pos);

            for (int i = pos; i < size; i++) {
                // ARRAY COMPARISON: 1 comparison involving array here
                compNewS++;
                if (A[i] == min) {
                    swap(i, pos);
                    pos = pos + 1;
                }
            }

        }
    }


}
/**
 * End of Sort Class
 **/