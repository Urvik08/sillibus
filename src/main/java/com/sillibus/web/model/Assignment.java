package com.sillibus.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Transactional
@Table (name = "assignment")
public class Assignment {
	private             AssignmentType  assignmentType;
	@JsonIgnore private Course          course;
	private             String          dueDate;
	private             Long            id;
	private             ImportanceLevel importanceLevel;
	private             String          studentNotes;
	private             String          topic;

	public enum AssignmentType {
		Test, Homework, Quiz, ImportantDate, Project, Unknown
	}

	public enum ImportanceLevel {
		High, Medium, Low, Unknown
	}

	public Assignment (AssignmentType assignmentType, String dueDate, long id, ImportanceLevel importanceLevel, String studentNotes, String topic) {
		this.assignmentType = assignmentType;
		this.dueDate = dueDate;
		this.id = id;
		this.importanceLevel = importanceLevel;
		this.studentNotes = studentNotes;
		this.topic = topic;
	}

	public Assignment () {
		this.assignmentType = AssignmentType.Unknown;
		this.importanceLevel = ImportanceLevel.Unknown;
	}

	@Basic
	@Column (name = "assignmentType", nullable = false, insertable = true, updatable = true, length = 255)
	public AssignmentType getAssignmentType () {
		return assignmentType;
	}

	@ManyToOne
	@JoinColumn (name = "course_id", referencedColumnName = "id", nullable = false)
	public Course getCourse () {
		return course;
	}

	@Basic
	@Column (name = "dueDate", nullable = true, insertable = true, updatable = true)
	public String getDueDate () {
		return dueDate;
	}

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column (name = "id", nullable = false, insertable = true, updatable = true)
	public Long getId () {
		return id;
	}

	@Basic
	@Column (name = "importanceLevel", nullable = true, insertable = true, updatable = true, length = 255)
	public ImportanceLevel getImportanceLevel () {
		return importanceLevel;
	}

	@Basic
	@Column (name = "studentNotes", nullable = true, insertable = true, updatable = true, length = 2147483647)
	public String getStudentNotes () {
		return studentNotes;
	}

	@Basic
	@Column (name = "topic", nullable = true, insertable = true, updatable = true, length = 255)
	public String getTopic () {
		return topic;
	}

	@Override
	public int hashCode () {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (assignmentType != null ? assignmentType.hashCode() : 0);
		result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
		result = 31 * result + (studentNotes != null ? studentNotes.hashCode() : 0);
		result = 31 * result + (topic != null ? topic.hashCode() : 0);
		result = 31 * result + (importanceLevel != null ? importanceLevel.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) { return true; }
		if (o == null || getClass() != o.getClass()) { return false; }

		Assignment that = (Assignment) o;

		if (id != that.id) { return false; }
		if (course != null ? !course.equals(that.course) : that.course != null) { return false; }
		if (assignmentType != null ? !assignmentType.equals(that.assignmentType) : that.assignmentType != null) { return false; }
		if (dueDate != null ? !dueDate.equals(that.dueDate) : that.dueDate != null) { return false; }
		if (importanceLevel != null ? !importanceLevel.equals(that.importanceLevel) : that.importanceLevel != null) { return false; }
		if (studentNotes != null ? !studentNotes.equals(that.studentNotes) : that.studentNotes != null) { return false; }
		if (topic != null ? !topic.equals(that.topic) : that.topic != null) { return false; }

		return true;
	}

	@Override
	public String toString () {
		return "Assignment{" +
			   "id=" + id +
			   ", assignmentType=" + assignmentType +
			   ", dueDate=" + dueDate +
			   ", studentNotes='" + studentNotes + '\'' +
			   ", topic='" + topic + '\'' +
			   ", importanceLevel=" + importanceLevel +
			   ", course=" + course +
			   '}';
	}

	public void setAssignmentType (AssignmentType assignmentType) {
		this.assignmentType = assignmentType;
	}

	public void setCourse (Course course) {
		this.course = course;
	}

	public void setDueDate (String dueDate) {
		this.dueDate = dueDate;
	}

	public void setId (Long id) {
		this.id = id;
	}

	public void setImportanceLevel (ImportanceLevel importanceLevel) {
		this.importanceLevel = importanceLevel;
	}

	public void setStudentNotes (String studentNotes) {
		this.studentNotes = studentNotes;
	}

	public void setTopic (String topic) {
		this.topic = topic;
	}
}