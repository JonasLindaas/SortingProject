package IO;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads input from the console using System.in and a Scanner. The scanner is a bit slow,
 * but provides the exact functionality I wanted. Sadly, due to the properties of the
 * inputStream you have to press Enter a couple of extra times
 */
public class ConsoleInputHandler implements IInputHandler {
    @Override
    public ArrayList<String> readInput() {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> stringList = new ArrayList<>();
        String s = "s"; //Initialised to a dummy value to make sure the while loop starts
        while(sc.hasNextLine() && !s.equals("")) {
            s = sc.nextLine();
            stringList.add(s);
        }

        while(stringList.get(stringList.size()-1).equals("")) //Removes empty items from the stringList
            stringList.remove(stringList.size()-1);

        return stringList;
    }
}
