package Processors;

import Storage.IItem;

/**
 * Technically not a parser in the traditional sense, as it's built specifically for converting
 * input into Storage.Item objects. Contains several methods to facilitate this process, all related to
 * input or Storage.Item creation.
 */
public interface IParser {

    /**
     * Uses an appropriate String to create an Storage.Item
     * An Storage.Item in this context is a specific implementation of the Storage.IItem interface
     * Items are a very specific kind of object, so only certain inputs should be accepted
     *
     * Will throw an Exception if the String does not at least contain a "name"-identifier (aka input is empty)
     *
     * The format of String inputs should be: [Name |Notes], the input will not have any notes if there is no '|' char
     *
     * @param itemInput The String input used to create an Storage.Item
     * @return the Storage.Item created
     */
    IItem createItem(String itemInput);

    /**
     * Returns the standard filtride stored in the interface
     *
     * @return default filtride
     */
    String getDefaultFiltride();

    Double getDefaultScore();

}
