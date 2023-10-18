package api.test;

import api.endpoints.ContactsEndPoints;
import api.endpoints.UsersEndPoints;
import api.payload.ContactDetails;
import api.payload.Names;
import api.payload.UserDetails;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import io.restassured.response.Response;
import org.checkerframework.checker.units.qual.N;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ContactTestCases {
    Faker faker;
    UserDetails userDetails;
    ContactDetails contactDetails;
    Names names;
    String token;
    String id;

    @BeforeClass
    public void setUp() {
       faker = new Faker();
       userDetails = new UserDetails();
       contactDetails = new ContactDetails();

       userDetails.setFirstName(faker.name().firstName());
       userDetails.setLastName(faker.name().lastName());
       userDetails.setEmail(faker.internet().emailAddress());
       userDetails.setPassword(faker.internet().password());

       Response response = UsersEndPoints.addUser(userDetails);
       token = response.jsonPath().getString("token");

       contactDetails.setFirstName(faker.name().firstName());
       contactDetails.setLastName(faker.name().lastName());

       Date fakeBirthday = faker.date().birthday();
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
       contactDetails.setBirthdate(dateFormat.format(fakeBirthday));

       contactDetails.setEmail(faker.internet().emailAddress());
       contactDetails.setPhone(faker.number().digits(10));
       contactDetails.setStreet1(faker.address().streetAddress());
       contactDetails.setStreet2(faker.address().streetAddress());
       contactDetails.setCity(faker.address().city());
       contactDetails.setStateProvince(faker.address().state());
       contactDetails.setPostalCode(faker.address().zipCode());
       contactDetails.setCountry(faker.address().country());
    }

    @Test(priority = 1)
    public void shouldAddContact() {
        Response response = ContactsEndPoints.addContact(token, contactDetails);
        Assert.assertEquals(response.getStatusCode(), 201, "Fail To Add Contact!!");
        id = response.jsonPath().getString("_id");
        System.out.println(id);
    }

    @Test(priority = 2)
    public void shouldGetContactList() {
        Response response = ContactsEndPoints.getContactList(token);
        Assert.assertEquals(response.getStatusCode(), 200, "Failed To Get Contact List!!");
    }

    @Test(priority = 3)
    public void shouldGetContact() {
        Response response = ContactsEndPoints.getContact(token , id);
        Assert.assertEquals(response.getStatusCode(), 200, "Failed To Get Contact!!");
    }

    @Test(priority = 4)
    public void shouldUpdateContact() {
        contactDetails.setFirstName(faker.name().firstName());
        contactDetails.setLastName(faker.name().lastName());

        Date fakeBirthday = faker.date().birthday();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        contactDetails.setBirthdate(dateFormat.format(fakeBirthday));

        contactDetails.setEmail(faker.internet().emailAddress());
        contactDetails.setPhone(faker.number().digits(10));
        contactDetails.setStreet1(faker.address().streetAddress());
        contactDetails.setStreet2(faker.address().streetAddress());
        contactDetails.setCity(faker.address().city());
        contactDetails.setStateProvince(faker.address().state());
        contactDetails.setPostalCode(faker.address().zipCode());
        contactDetails.setCountry(faker.address().country());

        Response response = ContactsEndPoints.getUpdateContact(token, id, contactDetails);
        Assert.assertEquals(response.getStatusCode(), 200, "Fail To Update Contact!!");
    }

    @Test(priority = 5)
    public void shouldUpdateName() {
        names = new Names();
        names.setFirstName(faker.name().firstName());
        names.setLastName(faker.name().lastName());

        Response response = ContactsEndPoints.updateName(token, id, names);
        Assert.assertEquals(response.getStatusCode(), 200, "Fail To Update Name!!");
    }

    @Test(priority = 6)
    public void shouldDeleteContact() {
        Response response = ContactsEndPoints.deleteContact(token, id);
        Assert.assertEquals(response.getStatusCode(), 200, "Fail To Delete Contact!!");
    }
}
