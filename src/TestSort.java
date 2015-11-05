/*************************************************************************/
/*** A test class to test various sorting methods implemented in Sort  ***/
/***                                                                   ***/
/*** Author: Dylan McKee    26/10/2015                                 ***/
/*************************************************************************/

public class TestSort {

    // Using constants to make switching out of data during the testing phase easier.
    private static final String FILE_NAME = "test3.txt";
    private static final int ARRAY_SIZE = 100;

    public static void main(String[] args) {

        // Instantiate a sort object for insertion sort testing
        Sort insertionSortTest = new Sort(ARRAY_SIZE);

        // Read the test data into array
        insertionSortTest.readIn(FILE_NAME);

        // Print unsorted array to the console
        insertionSortTest.display(10, "Unsorted Array of Integers");

        // Perform insertion sort algorithm on the array
        insertionSortTest.insertionSort();

        // Print insertion sorted array to the console
        insertionSortTest.display(10, "\nInsertion-sorted Array of Integers");


        // Print insertion sort comparison count
        System.out.println("\n\nInsertion sort comparison counter: " + insertionSortTest.compIS);

        // Instantiate a sort object for quicksort testing
        Sort quickSortTest = new Sort(ARRAY_SIZE);

        // Read the test data into array
        quickSortTest.readIn(FILE_NAME);

        // Print unsorted array to the console
        quickSortTest.display(10, "\nUnsorted Array of Integers");

        // Perform quicksort algorithm on the array
        quickSortTest.quickSort();

        // Print quicksorted array to the console
        quickSortTest.display(10, "\nQuicksorted Array of Integers");

        // Print a blank line to make results more readable
        System.out.println("\n");

        // Print quicksort comparison count
        System.out.println("Quicksort comparison counter: " + quickSortTest.compQS);

        // Instantiate a sort object for 'new sort' testing
        Sort newSortTest = new Sort(ARRAY_SIZE);

        // Read the test data into array
        newSortTest.readIn(FILE_NAME);

        // Print unsorted array to the console
        newSortTest.display(10, "\nUnsorted Array of Integers");

        // Perform 'new sort' algorithm on the array
        newSortTest.newSort();

        // Print 'new sorted' array to the console
        newSortTest.display(10, "\n'New sorted' Array of Integers");

        // Print a blank line to make results more readable
        System.out.println("\n");

        // Print 'new sort' comparison count
        System.out.println("New sort comparison counter: " + newSortTest.compNewS);


    }

}