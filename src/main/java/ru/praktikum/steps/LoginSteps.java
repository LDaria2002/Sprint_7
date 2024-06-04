package ru.praktikum.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class LoginSteps {
    @Step("Авторизация в пользователе")
    public Response loginStep(String login, String password){
        return
        given()
                .contentType(ContentType.JSON)
                .baseUri("http://qa-scooter.praktikum-services.ru")
                .body("{\n" +
                        "    \"login\": \"" + login + "\",\n" +
                        "    \"password\": \"" + password + "\"\n" +
                        "}")
                .when()
                .post("/api/v1/courier/login");
    }
}
