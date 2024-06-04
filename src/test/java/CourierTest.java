import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import ru.praktikum.steps.CourierSteps;
import ru.praktikum.steps.DeleteSteps;
import ru.praktikum.steps.LoginSteps;

import static org.hamcrest.Matchers.is;

public class CourierTest {

    String login = RandomStringUtils.randomAlphabetic(10);
    String password = RandomStringUtils.randomAlphabetic(10);

    @Test
    @Description("Создаю пользователя")
    public void shouldReturnOkTrue() {

        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        new CourierSteps()
                .createCourier(login, password, "Сидоров")
                .then()
                .statusCode(201)
                .body("ok", is(true));
    }

    @Test
    @Description("Создаю существующего пользователя")
    public void shouldReturnCode409() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        new CourierSteps()
                .createCourier("login", "2323", "Иванов")
                .then()
                .statusCode(409);
    }

    @Test
    @Description("Передаю пустой параметр")
    public void shouldReturnCode400() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        new CourierSteps()
                .createCourier(login, "", "")
                .then()
                .statusCode(400);
    }

    @After
    public void tearDown() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        Integer id = new LoginSteps()
                .loginStep(login, password)
                .then()
                .extract().body().path("id");
        if (id != null) {
            DeleteSteps.delete(id);
        }
    }
}

