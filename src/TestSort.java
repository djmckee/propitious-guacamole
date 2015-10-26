/*************************************************************************/
/*** A test class to test various sorting methods implemented in Sort  ***/
/***                                                                   ***/
/*** Author: Dylan McKee    26/10/2015                                 ***/
/*************************************************************************/


public class TestSort {

    public static void main(String[] args) {
        final Sort insertionSortTest = new Sort(100);

        /** Read in test data into array **/
        insertionSortTest.readIn("test3.txt");

        /** Display array **/
        insertionSortTest.display(10, "Unsorted Array of Integers");

        /** Perform insertion sort **/
        insertionSortTest.insertionSort();

        /** Display insertion sorted array **/
        insertionSortTest.display(10, "Insertion-sorted Array of Integers");


        /** And display comparison count **/
        System.out.println("\n\nInsertion sort comparison counter: " + insertionSortTest.compIS);



        Sort quickSortTest = new Sort(100);

        /** Read in test data into array **/
        quickSortTest.readIn("test3.txt");

        /** Display unsorted array **/
        quickSortTest.display(10, "\nUnsorted Array of Integers");

        /** Perform quick-sort **/
        quickSortTest.performQuicksort();

        /** Display quicksorted array **/
        quickSortTest.display(10, "Quicksorted Array of Integers");

        /** End with a blank line to improve readability in the console **/
        System.out.println("\n");

        /** Display comparison counters **/
        System.out.println("Quicksort comparison counter: " + quickSortTest.compQS);

        Sort newSortTest = new Sort(100);

        /** Read in test data into array **/
        newSortTest.readIn("test3.txt");

        /** Display unsorted array **/
        newSortTest.display(10, "\nUnsorted Array of Integers");

        /** Perform new sort **/
        newSortTest.newSort();

        /** Display new sorted array **/
        newSortTest.display(10, "'New sorted' Array of Integers");

        /** End with a blank line to improve readability in the console **/
        System.out.println("\n");

        /** Display comparison counters **/
        System.out.println("New sort comparison counter: " + newSortTest.compNewS);

    }

}