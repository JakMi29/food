package food_app.bussines;

import Food_app.business.OrderNumberGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderNumberGeneratedTest {

    @Test
    void testGenerateOrderNumber() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        OffsetDateTime when = OffsetDateTime.parse("2023-07-03T12:34:56+00:00");


        String orderNumber = OrderNumberGenerator.generateOrderNumber(when);

        String expectedOrderNumber = "ORD620231235634";
        Assertions.assertEquals(expectedOrderNumber, orderNumber.substring(0,orderNumber.length()-2));

    }

    @Test
    void testRandomInt() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int min = 10;
        int max = 100;
        OrderNumberGenerator orderNumberGenerator = new OrderNumberGenerator();
        Method randomIntMethod = OrderNumberGenerator.class.getDeclaredMethod("randomInt", int.class, int.class);
        randomIntMethod.setAccessible(true);

        for (int i = 0; i < 1000; i++) {
            int random = (int) randomIntMethod.invoke(orderNumberGenerator, min, max);
            assertTrue(random >= min && random < max);
        }
    }
}
