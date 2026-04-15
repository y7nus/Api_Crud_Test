package client;

import base.BaseApi;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import static StepImplementations.PetStepImplementations.response;
import static io.restassured.RestAssured.given;

public class ApiClient extends BaseApi {

    public Response post(String path, String body) {
        response
                = given()
                .spec(spec)
                .body(body)
                .when()
                .post(path)
                .then()
                .extract()
                .response();
        return response;
    }

    public Response get(String path) {
        response =
                given()
                        .spec(spec)
                        .expect().defaultParser(Parser.JSON) // Ekstra güvenlik
                        .when()
                        .get(path)
                        .then()
                        .log().ifValidationFails() // sadece hata varsa loga gelir
                        .extract()
                        .response();
        return response;
    }


    public Response put(String path, String body) {
        response =
                given()
                        .spec(spec)
                        .log().all()
                        .body(body)
                        .when()
                        .put(path)
                        .then()
                        .extract()
                        .response();
        return response;
    }

    public Response delete(String path) {
        response =
                given()
                        .spec(spec)
                        .when()
                        .delete(path)
                        .then()
                        .extract()
                        .response();
        return response;
    }

}
