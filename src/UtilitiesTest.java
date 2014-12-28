import static org.junit.Assert.*;

public class UtilitiesTest {

    @org.junit.Test
    public void testBinomial() throws Exception {
        for (int i = 1; i < 50; i++){
            assertEquals(i + "C1 should be" + i,i,Utilities.binomial(i,1));
        }
    }
}