package Processors;

import Storage.IItem;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.util.Random;

import static org.junit.Assert.*;

public class ParserTest {
    private Parser parser = new Parser();
    private String defaultFiltride = parser.getDefaultFiltride();
    private Double defaultScore = parser.getDefaultScore();
    private Random r = new Random();

    @Before
    public void initialize() {
        parser = new Parser();
    }

    /**
     * Generates a random string with only alphanumerics
     * @param minLength minimum length of string
     * @param maxLength maximum length of string
     * @return random string
     */
    private String generateRandomStringAlpha(int minLength, int maxLength) {
        int lowerLeftLimit  = 97;   // 'a'
        int lowerRightLimit = 122;  // 'z'
        int upperLeftLimit  = 65;   // 'A'
        int upperRightLimit = 90;   // 'z'

        int targetStringLength = minLength + r.nextInt(maxLength - minLength);
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for(int i = 0; i < targetStringLength; i++) {
            int leftLimit, rightLimit;
            if(r.nextBoolean()) {
                leftLimit = lowerLeftLimit;
                rightLimit = lowerRightLimit;
            } else {
                leftLimit = upperLeftLimit;
                rightLimit = upperRightLimit;
            }
            int randomLimitedInt = leftLimit + (int) (r.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    private String generateRandomString(int minLength, int maxLength) {
        return ""; //TODO: complete
    }

    private String generateRandomName(int firstNameMin, int firstNameMax, int lasteNameMin, int lastNameMax) {
        return generateRandomStringAlpha(firstNameMin, firstNameMax) + " " + generateRandomStringAlpha(lasteNameMin, lastNameMax);
    }

    private String generateStandardName() {
        return generateRandomName(3, 8, 5, 10);
    }

    private void nameTest(String name, IItem testItem) {
        assertEquals(name, testItem.getName());
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void nullInputTest() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("The given input was invalid");
        IItem testItem = parser.createItem(null);
    }

    @Test
    public void emptyStringTest() {
        String input = "";
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("The given input was invalid");
        IItem testItem = parser.createItem(input);
    }

    //TODO: there is way too much repeated code in these test
    @Test
    public void justNameTest() {
        String name = generateStandardName();
        IItem testItem = parser.createItem(name);
        assertEquals(name, testItem.getName());
    }

    @Test
    public void nameAndJustScoreTest() {
        double randomScore = r.nextDouble();
        String name = generateStandardName();
        String input = name + " | " + randomScore + defaultFiltride;
        IItem testItem = parser.createItem(input);
        assertEquals(name, testItem.getName());
        assertEquals(String.valueOf(randomScore), String.valueOf(testItem.getScore()));
    }

    @Test
    public void nameAndNegativeScoreTest() {
        double randomScore = r.nextDouble();
        String name = generateStandardName();
        String input = name + " | -" + randomScore + defaultFiltride;
        IItem testItem = parser.createItem(input);
        assertEquals(name, testItem.getName());
        assertEquals("-"+ randomScore, String.valueOf(testItem.getScore())); //TODO: this is a poor way of testing what I want
    }

    @Test
    public void nameAndScoreAndNotesTest() {
        double randomScore = r.nextDouble();
        String name = generateStandardName();
        String notes = generateRandomStringAlpha(5, 30);
        String input = name + " | " + randomScore + defaultFiltride + notes; //TODO:Should extract the " | " part
        IItem testItem = parser.createItem(input);
        assertEquals(name, testItem.getName());
        assertEquals(String.valueOf(randomScore), String.valueOf(testItem.getScore()));
        assertEquals(" "+notes, testItem.getNotes()); //TODO: should remove the whitespace (refer to above TODO)
    }

    @Test
    public void nameAndScoreWithSecondaryScoreWithOneFiltride() {
        double score1 = r.nextDouble();
        double score2 = r.nextDouble();
        String name = generateStandardName();
        String input = name + " | " + score1 + defaultFiltride + " " + score2;
        IItem testItem = parser.createItem(input);
        assertEquals(name, testItem.getName());
        assertEquals(String.valueOf(score1), String.valueOf(testItem.getScore()));
        assertEquals("  " + score2, testItem.getNotes());
    }

    @Test
    public void nameAndScoreWithSecondaryScoreWithTwoFiltrides() {
        double score1 = r.nextDouble();
        double score2 = r.nextDouble();
        String name = generateStandardName();
        String input = name + " | " + score1 + defaultFiltride + " " + score2 + defaultFiltride;
        IItem testItem = parser.createItem(input);
        assertEquals(name, testItem.getName());
        assertEquals(String.valueOf(score1), String.valueOf(testItem.getScore()));
        assertEquals("  " + score2 + defaultFiltride, testItem.getNotes());
    }

    @Test
    public void nameAndRandomNotesTest() {
        String name = generateStandardName();
        String notes = generateRandomStringAlpha(5, 50);
        String input = name + " | " + notes;
        IItem testItem = parser.createItem(input);
        assertEquals(name, testItem.getName());
        assertEquals(String.valueOf(0.0), String.valueOf(testItem.getScore()));
        assertEquals(" " + notes, testItem.getNotes());
    }

    @Test
    public void randomInputTest() {
        String randomInput = generateRandomStringAlpha(5, 50);
        IItem testItem = parser.createItem(randomInput);
        assertEquals(randomInput, testItem.getName());
        assertEquals(String.valueOf(0.0), String.valueOf(testItem.getScore()));
        assertNull(testItem.getNotes());
    }
}
