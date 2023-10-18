package api.endpoints;

import api.payload.ContactDetails;
import api.payload.Names;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ContactsEndPoints {
    public static Response addContact(String token, ContactDetails payload) {
        return given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.addContact);
    }

    public static Response getContactList(String token) {
        return given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .when()
                .get(Routes.getContactList);
    }

    public static Response getContact(String token, String id) {
        return given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .when()
                .get(Routes.getContact + id);
    }

    public static Response getUpdateContact(String token, String id, ContactDetails payload) {
        return given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .put(Routes.updateContact + id);
    }

    public static Response updateName(String token, String id, Names names) {
        return given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .body(names)
                .when()
                .patch(Routes.updateName + id);
    }

    public static Response deleteContact(String token, String id) {
        return given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .when()
                .delete(Routes.deleteContact + id);
    }
}
