import IO.ConsoleInputHandler;
import IO.ConsoleOutputHandler;
import IO.IInputHandler;
import IO.IOutputHandler;
import Processors.CollectionsSort;
import Processors.IParser;
import Processors.ISort;
import Processors.Parser;

public class Main {
    // Change these if you want to use another class instead
    private static final IInputHandler INPUT_HANDLER   = new ConsoleInputHandler();
    private static final IParser PARSER                = new Parser();
    private static final ISort SORTER                  = new CollectionsSort();
    private static final IOutputHandler OUTPUT_HANDLER = new ConsoleOutputHandler();



    public static void main(String[] args) {
        ISortingProject sortingProject = new SortingProject(INPUT_HANDLER, PARSER, SORTER, OUTPUT_HANDLER);
        sortingProject.performDefaultBehaviour();
    }
}
