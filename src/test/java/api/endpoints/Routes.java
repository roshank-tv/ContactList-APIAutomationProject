package api.endpoints;

public class Routes {
    public static String base_url = "https://thinking-tester-contact-list.herokuapp.com/";

    //users
    public static String addUser = base_url + "users";
    public static String getUserProfile = base_url + "users/me";
    public static String updateUser = base_url + "users/me";
    public static String logOutUser = base_url + "users/logout";
    public static String logInUser = base_url + "users/login";
    public static String deleteUser = base_url + "users/me";

    //contacts
    public static String addContact = base_url + "contacts";
    public static String getContactList = base_url + "contacts";
    public static String getContact = base_url + "contacts/";
    public static String updateContact = base_url + "contacts/";
    public static String updateContactName = base_url + "contacts/";
    public static String deleteContact = base_url + "contacts/";

}
