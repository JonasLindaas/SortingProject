package Processors;

import Storage.IItem;
import Storage.Item;
import javafx.util.Pair;

public class Parser<E> implements IParser {
    private final String DEFAULT_FILTRIDE = "/10";     //The standard filtride used in my scoring system, change if you don't use it

    private final String FILTRIDE = DEFAULT_FILTRIDE;  //Change this if you want to use your own custom filtride
    private final Double DEFAULT_SCORE = 0.0;           //The score used if no score is found in the notes

    /**
     * Follows the following procedure to creat an Item
     *  1: Check if input is null or empty, if so throws IllegalArgumentException
     *  2: Checks to see if there is a '|' char
     *      - If false, extracts everything and puts it into a string and creates the Item using that, then returns
     *      - If true, extracts everything up to that char and stores it in a name string
     *  2.5: Assuming point 2 evaluated as true, method continues to point 3
     *  3: Extracts the name using the extractName() helper method
     *  4: Attempts to extract the filtride (indicates the scale of the score, but shouldn't affect the score directly)
     *  5: Attempts to extract a score from the filtered notes
     *
     * @param itemInput The String input used to create an Item
     * @return the Item created
     */
    @Override
    public IItem createItem(String itemInput) {
        if(itemInput == null || itemInput.length() == 0)
            throw new IllegalArgumentException("The given input was invalid");

        if(!itemInput.contains("|"))
            return new Item(itemInput);

        Pair<String, String> tmp = extractName(itemInput);
        final String name = tmp.getKey();
        String notes = tmp.getValue();
        Double score = DEFAULT_SCORE;
        if(tmp.getValue().contains(FILTRIDE)) {
            notes = tryToFilterOut(notes, FILTRIDE);
            Pair<String, Double> notesAndScores = tryToExtractScore(notes);

            if(notesAndScores.getValue() != null) {
                notes = notesAndScores.getKey();
                score = notesAndScores.getValue();
            }
        }




        if(containsOnlyWhitespaces(notes))
            return new Item(name, score);

        return new Item(name, score, notes);
    }

    /**
     * Checks if a string only contains whitespaces (i.e. is empty for practical reasons)
     *
     * @param input String to check
     * @return true if there are only whitespaces, false if there are any characters that are not whitespaces
     */
    private Boolean containsOnlyWhitespaces(String input) {
        char[] inputChars = input.toCharArray();
        for(char c : inputChars) {
            if(c != ' ') {
                return false;
            }
        }

        return true;
    }

    //TODO: add comments to this
    private Pair<String, Double> tryToExtractScore(String input) {
        int startOfNumPos = 0;
        int endOfNumPos = input.length();
        boolean foundNum = false;
        boolean foundDot = false;

        for(int i = 0; i < input.length(); i++) {
            if(!foundNum) {
                if(Character.isDigit(input.charAt(i))) {
                    foundNum = true;
                    startOfNumPos = i;
                    if(input.charAt(i-1) == '-')
                        startOfNumPos--;
                }
            } else {
                if(input.charAt(i) == '.') {
                    if(!foundDot)
                        foundDot = true;
                    else {
                        endOfNumPos = i;
                        break;
                    }
                }

                if(!Character.isDigit(input.charAt(i)) && !(input.charAt(i) == '.')) {
                    endOfNumPos = i;
                    break;
                }
            }
        }
        Double score = null;
        if(startOfNumPos != 0)
            score = Double.valueOf(input.substring(startOfNumPos, endOfNumPos));

        String filtride = "";
        if(score != null)
            filtride = score.toString();

        return new Pair<>(tryToFilterOut(input, filtride), score);
    }


    /**
     * Helper method that will attempt to filter out a substring toFilter from the input
     * The method goes through the input linearly and consumes the filtride (toFilter) when it is found,
     * this means that a character will only be removed once (unless it appears multiple times in the filtride)
     * and that characters will be removed in order, and that filtration on characters following the first will
     * only apply to the rest of input.
     *
     * Note: does not have to consume all of the filtride
     *
     * Example: input = "Test string", toFilter = "tsi", result = "Tes trng"
     *
     *
     * @param input String to filter through
     * @param toFilter String of filtride to filter out, character sequence is important!
     * @return Filtered string
     */
    private String tryToFilterOut(String input, String toFilter) {
        String filtrides = toFilter;
        char[] output = input.toCharArray();
        String finalOutput = "";
        for(char c : output) {
            if(filtrides.length() != 0) {
                if (c == filtrides.charAt(0)) {
                    filtrides = filtrides.substring(1);
                    continue;
                }
            }
            finalOutput = finalOutput.concat(String.valueOf(c));
        }
        return finalOutput;
    }

    /**
     * Helper method to createItem used to extract names (from Items) from a string (assuming there is more than
     * just a name) and consumes it (the second part of the pair doesn't contain the name or the split char '|')
     *
     * Should not be called if there is just a name in the input
     *
     * @param input The string to extract the name from
     * @return a Pair where the key (first part) is the name, and the value (second part) is the rest of the input
     * without the name and the split char '|'
     */ //TODO: Should check if name extraction is possible on it's own, right now it's done before the call, which is bad
    private Pair<String, String> extractName(String input) {
        String[] tmp = input.split("\\|");
        String name = tmp[0].substring(0, tmp[0].length()-1); //Cuts off the trailing whitespace, since it is redundant
        String note = tmp[1];
        return new Pair<>(name, note);
    }

    @Override
    public String getDefaultFiltride() {
        return DEFAULT_FILTRIDE;
    }

    @Override
    public Double getDefaultScore() {
        return DEFAULT_SCORE;
    }
}
