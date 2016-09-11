package no.westerdals;

import org.junit.Assert;
import org.junit.Test;

public class AppTest {
    @Test
    public void testLengthOfTheUniqueKey() {
        Assert.assertEquals(36, App.generateUniqueKey().length());
    }
}
