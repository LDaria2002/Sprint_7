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
import static org.hamcrest.Matchers.notNullValue;

public class LoginTest {

    String login = RandomStringUtils.randomAlphabetic(10);
    String password= RandomStringUtils.randomAlphabetic(10);

    @Test
    @Description("Авторизирусь")
    public void shouldReturnId(){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        new CourierSteps()
                .createCourier( login,password, "Сидоров")
                .then()
                .statusCode(201);

        new LoginSteps()
                .loginStep(login, password)
                .then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Test
    @Description("Авторизация под несуществующим пользователем")
    public void shouldReturn404Code(){
        String login = "bdhbxjscbicue97483";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        new LoginSteps()
                .loginStep(login, password)
                .then()
                .statusCode(404);
    }
    @Test
    @Description("Авторизация под пользователем с неправильным паролем")
    public void shouldReturn404Password(){

        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        new CourierSteps()
                .createCourier( login,password, "Сидоров")
                .then()
                .statusCode(201);
        new LoginSteps()
                .loginStep(login, "password")
                .then()
                .statusCode(404);
    }
    @Test
    @Description("Не хватает данных для авторизации")
    public void testWithoutData(){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        new LoginSteps()
                .loginStep(login, "" )
                .then()
                .statusCode(400);
    }
    @After
    public void tearDown(){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        Integer id = new LoginSteps()
                .loginStep(login,password)
                .then()
                .extract().body().path("id");
        if (id !=null){
            DeleteSteps.delete(id);
        }
    }
}
