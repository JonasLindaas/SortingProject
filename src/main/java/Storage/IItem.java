package Storage;

/** Interface for items, which store their name, score and any potential notes
 *  Does not use Setters to make immutable
 */
public interface IItem extends Comparable {
    /**
     * Get the name of the item
     * @return name
     */
    String getName();

    /**
     * Get the score of the item (also known as it's weight)
     * @return score
     */
    double getScore();

    /**
     * Get the notes (if there are any), or null if there are none attached to the item
     * @return notes (or null)
     */
    String getNotes();

    /**
     * Prints the item to console
     */
    void printToConsole();

    /**
     * Attempts to override the score (how it does this is left up to the implementation of the interface)
     */
    void activateOverride();
}