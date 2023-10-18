package api.testcases;

import api.endpoints.UsersEndPoints;
import api.payload.LoginDetails;
import api.payload.UserDetails;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTestCases {
    Faker faker;
    UserDetails userDetails;
    LoginDetails loginDetails;
    String token;

    @BeforeClass(groups = {"run-all", "sanity", "regression", "smoke"})
    public void setUp() {
        faker = new Faker();
        userDetails = new UserDetails();

        userDetails.setFirstName(faker.name().firstName());
        userDetails.setLastName(faker.name().lastName());
        userDetails.setEmail(faker.internet().emailAddress());
        userDetails.setPassword(faker.internet().password());
    }

    @Test(priority = 1, groups = {"run-all", "sanity", "regression", "smoke"})
    public void shouldAddUser() {
        Response response = UsersEndPoints.addUser(userDetails);
        Assert.assertEquals(response.getStatusCode(), 201, "User Not Added!!");
        token = response.jsonPath().getString("token");
    }

    @Test(priority = 2, groups = {"run-all", "smoke"})
    public void shouldGetUserProfile() {
        Response response = UsersEndPoints.getUserProfile(token);
        Assert.assertEquals(response.getStatusCode(), 200, "User Details Not Found!!");
    }

    @Test(priority = 3, groups = {"run-all", "regression"})
    public void shouldUpdateUserProfile() {
        userDetails.setFirstName(faker.name().firstName());
        userDetails.setLastName(faker.name().lastName());

        Response response = UsersEndPoints.updateUser(token, userDetails);
        Assert.assertEquals(response.statusCode(), 200, "User Details Not Updated!!");
    }

    @Test(priority = 4, groups = {"run-all", "sanity"})
    public void shouldUserLogOut() {
        Response response = UsersEndPoints.logOutUser(token);
        Assert.assertEquals(response.getStatusCode(), 200, "Logout Fail!!");
    }

    @Test(priority = 5, groups = {"run-all", "sanity"})
    public void shouldUserLogIn() {
        loginDetails = new LoginDetails();
        loginDetails.setEmail(userDetails.getEmail());
        loginDetails.setPassword(userDetails.getPassword());

        Response response = UsersEndPoints.logInUser(loginDetails);
        Assert.assertEquals(response.getStatusCode(), 200, "Login Fail!!");
        token = response.jsonPath().getString("token");
    }

    @Test(priority = 6, groups = {"run-all", "smoke"})
    public void shouldDeleteUserProfile() {
        Response response = UsersEndPoints.deleteUser(token);
        Assert.assertEquals(response.getStatusCode(), 200, "Fail To Delete User Profile!!");
    }
}
