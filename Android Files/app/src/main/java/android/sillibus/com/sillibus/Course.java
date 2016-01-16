package android.sillibus.com.sillibus;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by urvik on 1/16/2016.
 */
public class Course {
    private ArrayList<Assignment> assignments;
    private int                       crn;
    private long                      id;
    private String                    name;
    private User                      professor;
    private ArrayList<User>          students;

    public ArrayList<Assignment> getAssignments()
    {
        return assignments;
    }

    public String getSub() {
        return name;
    }
}