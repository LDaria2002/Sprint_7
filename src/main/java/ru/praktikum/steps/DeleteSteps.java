package ru.praktikum.steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteSteps {
    public static Response delete(int id){
        return given()
                .contentType(ContentType.JSON)
                .baseUri("http://qa-scooter.praktikum-services.ru")
                .pathParam("id", id)
                .when()
                .delete("/api/v1/courier/{id}");
    }

}
