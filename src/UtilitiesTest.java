import static org.junit.Assert.*;

public class UtilitiesTest {

    @org.junit.Test
    public void testBinomial() throws Exception {
        for (int i = 1; i < 50; i++){
            assertEquals(i + "C1 should be" + i,i,Utilities.binomial(i,1));
        }

        for (int i = 1; i < 50; i++){
            for(int j = 1; j < i / 2; j++){
                assertEquals(i + "C" + j + " should equal "
                + i + "C" + (i-j),Utilities.binomial(i,j), Utilities.binomial(i,i-j));
            }
        }
    }

    @org.junit.Test
    public void testBezier() throws Exception {
        for (float t = 0; t < 10; t += 0.1){
            assertEquals("Bezier(2,t) must equal Bezier2(t)",Utilities.bezier(2,t),Utilities.bezier2(t),0.0001);
        }

        for (float t = 0; t < 10; t += 0.1){
            assertEquals("Bezier(3,t) must equal Bezier3(t)",Utilities.bezier(3,t),Utilities.bezier3(t),0.0001);
        }
    }
}