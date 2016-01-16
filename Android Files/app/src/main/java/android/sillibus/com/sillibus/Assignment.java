package android.sillibus.com.sillibus;

import java.util.Date;

/**
 * Created by urvik on 1/16/2016.
 */
public class Assignment {
    private AssignmentType  assignmentType;
    private Course          course;
    private String dueDate;
    private long            id;
    private ImportanceLevel importanceLevel;
    private String          studentNotes;
    private String          topic;

    public void setCourse(Course course) {
        this.course = course;
    }

    public enum AssignmentType {
        Test, Homework, Quiz, ImportantDate, Project, Unknown
    }

    public enum ImportanceLevel {
        High, Medium, Low, Unknown
    }

    public AssignmentType getAssignmentType() {
        return assignmentType;
    }

    public Course getCourse() {
        return course;
    }

    public String getDueDate() {
        return dueDate;
    }

    public long getId() {
        return id;
    }

    public ImportanceLevel getImportanceLevel() {
        return importanceLevel;
    }

    public String getStudentNotes() {
        return studentNotes;
    }

    public String getTopic() {
        return topic;
    }
}