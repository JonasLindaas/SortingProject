import IO.ConsoleInputHandler;
import IO.ConsoleOutputHandler;
import IO.IInputHandler;
import IO.IOutputHandler;
import Processors.CollectionsSort;
import Processors.IParser;
import Processors.ISort;
import Processors.Parser;
import Storage.IItem;

import java.util.ArrayList;
import java.util.List;

public class Main {
    // Change these if you want to use another class instead
    private static final IInputHandler INPUT_HANDLER   = new ConsoleInputHandler();
    private static final IParser PARSER                = new Parser();
    private static final ISort SORTER                  = new CollectionsSort();
    private static final IOutputHandler OUTPUT_HANDLER = new ConsoleOutputHandler();



    public static void main(String[] args) {
        ISortingProject sortingProject = new SortingProject(INPUT_HANDLER, PARSER, SORTER, OUTPUT_HANDLER);
        sortingProject.performDefaultBehaviour();

        /* Old code TODO: remove if default behaviour works
        Parser parser = new Parser();
        IInputHandler inputHandler = new ConsoleInputHandler();
        ArrayList<String> input = inputHandler.readInput();

        List<IItem> items = new ArrayList<>();
        for(String s : input) {
            items.add(parser.createItem(s));
        }

        ISort sorter = new CollectionsSort();
        sorter.sort(items);

        IOutputHandler outputHandler = new ConsoleOutputHandler();

        ArrayList<String> itemNames = new ArrayList<>();
        for(IItem item : items) {
            itemNames.add(item.getName());
        }
        outputHandler.outputWithSMFormatting(itemNames);
        */
    }
}
