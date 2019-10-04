import IO.ConsoleInputHandler;
import IO.ConsoleOutputHandler;
import IO.IInputHandler;
import IO.IOutputHandler;
import Processors.CollectionsSort;
import Processors.ISort;
import Processors.Parser;
import Storage.IItem;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
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

    }
}
