package api.endpoints;

import api.payload.LoginDetails;
import api.payload.UserDetails;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UsersEndPoints {
    public static Response addUser(UserDetails payload) {
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.addUser);
    }

    public static Response getUserProfile(String token) {
        return given()
                .header("Authorization",token)
                .contentType(ContentType.JSON)
                .when()
                .get(Routes.getUserProfile);
    }

    public static Response updateUser(String token, UserDetails payload) {
        return given()
                .header("Authorization",token)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .patch(Routes.updateUser);
    }

    public static Response logOutUser(String token) {
        return given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .when()
                .post(Routes.logOutUser);
    }

    public static Response logInUser(LoginDetails loginDetails) {
        return given()
                .contentType(ContentType.JSON)
                .body(loginDetails)
                .when()
                .post(Routes.logInUser);
    }

    public static Response deleteUser(String token) {
        return given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .when()
                .delete(Routes.deleteUser);
    }
}