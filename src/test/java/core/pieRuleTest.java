package core;

import org.junit.Test;
import java.awt.*;
import static org.junit.Assert.*;

public class pieRuleTest {

    @Test
    public void checkQuery(){
        assertTrue(PieRule.query(2));
    }
}
