package ski.mashiro;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ski.mashiro.util.Utils;

import java.util.Calendar;
import java.util.Date;

@SpringBootTest
public class TestSomething {

    @Test
    public void testCalcWeek() {
        Calendar instance = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        instance.set(now.get(Calendar.YEAR), Calendar.SEPTEMBER, 1);
        String s = Utils.calcCurrentWeek(new Date(), instance.getTime());
        System.out.println(s);
    }
}
