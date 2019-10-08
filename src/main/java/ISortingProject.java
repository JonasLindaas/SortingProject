import Processors.ISort;
import Storage.IItem;
import java.util.List;

/**
 * This is the interface for the class that binds all of the different classes together,
 * and performs relevant operations.
 *
 * Should be able to return items both in sorted and unsorted order.
 *
 * Implementation of methods should not rely on specific classes, only relevant interfaces.
 */
public interface ISortingProject {
    /**
     * Reads input using the attached InputHandler and stores it for later use.
     */
    void readInput();

    /**
     * Uses the attached parser to parse the input and create Item objects.
     * Places the newly created Item objects in the original order list.
     */
    void parseInput();

    /**
     * Attempts to print out one of the lists of items using the attached OutputHandler.
     * Lets you choose whether you want to print the items in sorted or original order.
     * Will throw an error if the given list list is empty.
     *
     * @param sortedItems True if you want sorted order, false if you want original order.
     */
    void printOutput(boolean sortedItems);

    /**
     * Attempts to print out one of the lists of items using IItem interface's print method.
     * Lets you choose whether you want to print the items in sorted or original order.
     * Will throw an error if the given list list is empty.
     *
     * @param sortedItems True if you want sorted order, false if you want original order.
     */
    void printItems(boolean sortedItems);

    /**
     * Lets the user set a new sorting class as the sorting method.
     */
    void setSorter(ISort sorter);

    /**
     * Sorts either the items in the original order list(if the sorted items list is empty) or
     * the items in the sorted list using the Sorter.
     */
    void sort();

    /**
     * Returns a list of Item objects in the original order they were read from input.
     * @return List of Item objects in original order.
     */
    List<IItem> getItemsInOriginalOrder();

    /**
     * Returns a list of Item objects in the newest sorted order they were read from input.
     * @return List of Item objects in the newest sorted order.
     */
    List<IItem> getItemsInSortedOrder();

    /**
     * A simple method that performs the expected default behaviour:
     * Read input -> Parse Input & Create Item Objects -> Create Sorted List -> Print to Output
     * Used for convenience, it's also possible to achieve this behaviour without using this method.
     */
    void performDefaultBehaviour();
}
