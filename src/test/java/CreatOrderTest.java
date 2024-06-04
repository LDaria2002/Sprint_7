import io.qameta.allure.Description;
import org.junit.runner.RunWith;
import ru.praktikum.steps.CreateOrderSteps;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.Test;
import org.junit.runners.Parameterized;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

@RunWith(Parameterized.class)
public class CreatOrderTest {



    private final List<String> color;

    public CreatOrderTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {List.of("BLACK", "GREY")},
                {List.of("GREY")},
                {List.of("BLACK")},
                {List.of()},
        };
    }

    @Test
    @Description("Создаю заказ с разными цветами")
    public void shouldCreateOrderTest() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        new CreateOrderSteps()
                .createOrder("Горький", "Максим", "г. Санкт-Петербург, Невский пр-кт, д.11", 4, "+7(901)-577-99-44", "2024-06-03", "Не работает домофон, стучите!", 6, color)
                .then()
                .statusCode(201)
                .body("track", notNullValue());
    }
}
