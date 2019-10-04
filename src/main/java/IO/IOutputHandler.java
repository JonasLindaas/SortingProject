package IO;

import java.util.List;

/**
 * Handles input :-] I'll elaborate later, when this interface actually contains more method signatures
 */
public interface IOutputHandler {
    /**
     * Outputs a single string to the destination
     * @param s String to output
     */
    void outputString(String s);

    /**
     * Outputs a list of strings to the destination
     * @param s Strings to output
     */
    void outputStrings(List<String> s);

    /**
     * Outputs a list of strings with special "SM" formatting
     *
     * SM formatting is used for inputting items into another data system, the format goes as follows
     * $sm [first item]
     * $[all following items]
     *
     * Each input can at most be 2000 chars long, so multiple messages must be made
     *
     * @param s Strings to output
     */
    void outputWithSMFormatting(List<String> s);
}
