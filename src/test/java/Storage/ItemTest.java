package Storage;

import org.junit.Assert;
import org.junit.Test;

public class ItemTest {
    @Test
    public void compareTest() {
        Item testItem1 = new Item("Test 1", 5.0);
        Item testItem2 = new Item("Test 2", 6.0);
        Assert.assertTrue(testItem1.compareTo(testItem2) > 0);
    }
}
