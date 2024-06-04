package ru.praktikum.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CreateOrderSteps {
    @Step("метод создания заказа")
    public Response createOrder (String firstName, String lastName, String address, int metroStation, String phone, String deliveryDate, String comment, int rentTime, List<String> color){
        return given()
                .contentType(ContentType.JSON)
                .baseUri("http://qa-scooter.praktikum-services.ru")
                .body("{\n" +
                        "    \"firstName\":\"" + firstName + "\",\n" +
                        "    \"lastName\": \"" + lastName + "\",\n" +
                        "    \"address\": \"" + address + "\",\n" +
                        "    \"metroStation\": \"" + metroStation + "\",\n" +
                        "    \"phone\": \"" + phone + "\",\n" +
                        "    \"rentTime\": \"" + rentTime + "\",\n" +
                        "    \"deliveryDate\": \"" + deliveryDate+ "\",\n" +
                        "    \"comment\": \"" + comment + "\",\n" +
                        "    \"color\": [\n" +
                        "        \"" + color + "\"\n" +
                        "    ]\n" +
                        "}" )
                .when()
                .post("/api/v1/orders");
    }
}
