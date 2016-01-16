package android.sillibus.com.sillibus.util;

/**
 * Created by joshua on 10/3/15.
 */
@SuppressWarnings("FieldCanBeLocal")
public class Paths {
    static public String BASE_API = "http://192.168.15.86:8080";
    static private String getUser = "/users?username=%s";

    static public String getUser(String username) {
        return String.format(getUser, username);
    }
}
