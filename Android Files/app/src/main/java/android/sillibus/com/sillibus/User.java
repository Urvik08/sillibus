package android.sillibus.com.sillibus;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by urvik on 1/16/2016.
 */
public class User {
    private             String             email;
    private             String             firstName;
    private             long               id;
    private             String             lastName;
    private Timestamp memberSince;

    private ArrayList<Course> professorCourses;
    private ArrayList<Course> studentCourses;
    private             UserType           userType;
    private             String             username;

    public ArrayList<Course> getCourses() {
        return studentCourses;
    }

}
