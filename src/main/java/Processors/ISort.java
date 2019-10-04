package Processors;

import Storage.IItem;

import java.util.List;

/**
 * Interface for sorters, right now it will be very sparse due to the limitations of my first "sorter"
 */
public interface ISort {
    /**
     * Sorts the given input
     * @param input the input to sort
     */
    void sort(List<IItem> input);
}
