public class Item implements IItem, Comparable<IItem> {
    private final String name;
    private final double score;
    private final String notes;

    /**
     * The full constructor for items
     * @param name name of the item
     * @param score score of the item
     * @param notes notes attached to the item (if any)
     */
    public Item(String name, double score, String notes) {
        this.name = name;
        this.score = score;
        this.notes = notes;
    }

    /**
     * Constructor for items without notes
     * Sets notes to null
     *
     * @param name the name of the item
     * @param score the score of the item
     */
    public Item(String name, double score) {
        this(name, score, null);
    }

    /**
     * Constructor that only takes name and notes
     * Score is set to 0 to be as neutral as possible
     *
     * @param name the name of the character
     * @param notes the notes (if any) attached to the character
     */
    public Item(String name, String notes) {
        this(name, 0.0, notes);
    }

    /**
     * Constructors for items that only have a name
     * @param name name of the item
     */
    public Item(String name) {
        this(name, 0.0, null);
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    //TODO: Consider throwing an exception
    public String getNotes() {
        return notes;
    }

    //TODO: improve the   a e s t h e t i c s
    public void printToConsole() {
        String tmp = name + " | Score: " +  (int) score + "/10";
        if(notes != null)
            tmp += " | Notes: " + notes;
        System.out.println(tmp);
    }

    //TODO: revist and improve this once modifiers are implemented
    public int compareTo(IItem otherItem) {
        return Double.compare(this.getScore(), otherItem.getScore());
    }
}
