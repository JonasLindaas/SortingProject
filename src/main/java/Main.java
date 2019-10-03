import IO.ConsoleInputHandler;
import IO.IInputHandler;
import Processors.Parser;
import Storage.IItem;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        IInputHandler inputHandler = new ConsoleInputHandler();
        String input = inputHandler.readInput().get(0);
        IItem tmpItem = parser.createItem(input);
        tmpItem.printToConsole();
    }
}
