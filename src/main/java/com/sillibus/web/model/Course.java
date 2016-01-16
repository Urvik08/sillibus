package com.sillibus.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Collection;

@Entity
@Transactional
@Table (name = "course")
public class Course {
	private             Collection<Assignment> assignments;
	private             int                    crn;
	private             Long                   id;
	private             String                 name;
	@JsonIgnore private User                   professor;
	@JsonIgnore private Collection<User>       students;

	public Course () { }

	public Course (int crn, long id, String name, User professor, Collection<User> students) {
		this.crn = crn;
		this.id = id;
		this.name = name;
		this.professor = professor;
		this.students = students;
	}

	@OneToMany (mappedBy = "course")
	public Collection<Assignment> getAssignments () {
		return assignments;
	}

	@Basic
	@Column (name = "crn", nullable = false, insertable = true, updatable = true)
	public int getCrn () {
		return crn;
	}

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column (name = "id", nullable = false, insertable = true, updatable = true)
	public Long getId () {
		return id;
	}

	@Basic
	@Column (name = "name", nullable = false, insertable = true, updatable = true, length = 255)
	public String getName () {
		return name;
	}

	@ManyToOne
	public User getProfessor () {
		return professor;
	}

	@ManyToMany
	@JoinTable (name = "course_students", joinColumns = @JoinColumn (name = "course_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn (name = "student_id", referencedColumnName = "id", nullable = false))
	public Collection<User> getStudents () {
		return students;
	}

	@Override
	public int hashCode () {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + crn;
		return result;
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) { return true; }
		if (o == null || getClass() != o.getClass()) { return false; }

		Course course = (Course) o;

		if (crn != course.crn) { return false; }
		if (id != course.id) { return false; }
		if (name != null ? !name.equals(course.name) : course.name != null) { return false; }

		return true;
	}

	public void setAssignments (Collection<Assignment> assignments) {
		this.assignments = assignments;
	}

	public void setCrn (int crn) {
		this.crn = crn;
	}

	public void setId (Long id) {
		this.id = id;
	}

	public void setName (String name) {
		this.name = name;
	}

	public void setProfessor (User professor) {
		this.professor = professor;
	}

	public void setStudents (Collection<User> students) {
		this.students = students;
	}
}