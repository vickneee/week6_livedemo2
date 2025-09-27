import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimeCalTest {
TimeCal timecal = new TimeCal();
    @Test
    public void calTime() {
        assertEquals(1.0, timecal.calTime(100,100), 0.001);

    }
}