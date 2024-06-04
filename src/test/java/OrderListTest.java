import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;


public class OrderListTest {

    @Test
    @Description("Получаю список сформированных заказов для курьера")
    public void shouldReturnOrderList() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        Response response =given()
                .contentType(ContentType.JSON)
                .baseUri("http://qa-scooter.praktikum-services.ru")
                .get("/api/v1/orders");
        response.then()
                .statusCode(200);
        System.out.println(response.then().assertThat().body("orders",  notNullValue()));
    }
}