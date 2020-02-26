import IO.IInputHandler;
import IO.IOutputHandler;
import Processors.IParser;
import Processors.ISort;
import Storage.IItem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//TODO: needs comments
public class SortingProject implements ISortingProject {
    private ArrayList<String> input         = new ArrayList<>(); //TODO: Input should not be stored here, make it local
    private ArrayList<IItem> originalOrder  = new ArrayList<>();
    private ArrayList<IItem> sortedOrder    = new ArrayList<>();

    //TODO: should consider making a constructor that creates "default" objects for the various processors
    private IInputHandler inputHandler;
    private IParser parser;
    private ISort sorter;
    private IOutputHandler outputHandler;

    private boolean USE_OVERRIDES = true;

    public SortingProject(IInputHandler inputHandler, IParser parser, ISort sorter, IOutputHandler outputHandler) {
        this.inputHandler = inputHandler;
        this.parser = parser;
        this.sorter = sorter;
        this.outputHandler = outputHandler;
    }

    @Override
    public void readInput() {
        if(input.isEmpty()) {
            input = inputHandler.readInput();
        } else {
            throwIllegalAccessError("Input has already been read");
        }
    }

    private void throwIllegalAccessError(String msg) {
        throwIllegalAccessError(msg); //TODO: consider making exception
    }

    @Override
    public void parseInput() {
        if(input.isEmpty()) {
            System.out.println("Input was empty, attempting to read input:");
            //TODO: make this a try catch block (it's been a while since I wrote one ok?)
            readInput();
            System.out.println("Success! Continuing parsing");
        }

        if(originalOrder.isEmpty()) {
            for (String s : input)
                originalOrder.add(parser.createItem(s));
        } else {
            throwIllegalAccessError("Items have already been created, and can not be changed after this point");
        }
    }

    @Override
    public void printOutput(boolean sortedItems) {
        List<IItem> items = makeSureListIsNotEmpty(sortedItems);
        List<String> itemNames = new ArrayList<>();
        for(IItem item : items) {
            itemNames.add(item.getName());
        }
        outputHandler.outputWithSMFormatting(itemNames);
    }

    @Override
    public void printItems(boolean sortedItems) {
        List<IItem> items = makeSureListIsNotEmpty(sortedItems);
        for(IItem item : items) {
            item.printToConsole();
        }
    }

    private List<IItem> makeSureListIsNotEmpty(boolean sortedItems) {
        List<IItem> items;
        if(sortedItems) {
            items = sortedOrder;
        } else {
            items = originalOrder;
        }

        if(items.isEmpty()) { //Technically sorting might be redundant, but it doesn't hurt, and makes the logic simpler
            System.out.println("The given list was empty, attempting to sort:");
            //TODO: make this a try catch block (it's been a while since I wrote one ok?)
            sort();
            System.out.println("Success! Continuing to print output");
        }
        return items;
    }

    @Override
    public void setSorter(ISort sorter) {
        this.sorter = sorter;
    }

    @Override
    public void sort() {
        if(originalOrder.isEmpty()) {
            System.out.println("originalOrder was empty, attempting to parse:");
            //TODO: make this a try catch block (it's been a while since I wrote one ok?)
            parseInput();
            System.out.println("Success! Continuing to sort");
        }

        ArrayList<IItem> listToSort;
        if(!sortedOrder.isEmpty()) {
            listToSort = sortedOrder;
        } else {
            listToSort = (ArrayList<IItem>) originalOrder.clone();
        }

        sorter.sort(listToSort);
        sortedOrder = listToSort;
    }

    @Override
    public List<IItem> getItemsInOriginalOrder() { //TODO: consider making this and the next method iterators instead
        return originalOrder;
    }

    @Override
    public List<IItem> getItemsInSortedOrder() {
        return sortedOrder;
    }

    @Override
    public void performDefaultBehaviour() {
        readInput();    //TODO: can probably shorten this to simply call sort() (because all of the methods call each other if needed
        parseInput();
        sort();
        printOutput(true);
    }
}
