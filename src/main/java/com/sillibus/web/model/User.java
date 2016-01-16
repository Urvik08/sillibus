package com.sillibus.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sillibus.web.model.dto.UserDto;
import com.sillibus.web.model.dto.UserType;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Collection;

@Entity
@Transactional
@Table (name = "user")
public class User {
	private             String             email;
	private             String             firstName;
	private             Long               id;
	private             String             lastName;
	@JsonIgnore private String             password;
	@JsonIgnore private Collection<Course> professorCourses;
	private             Collection<Course> studentCourses;
	private             UserType           userType;
	private             String             username;

	public User (UserDto userDTO) {
		setEmail(userDTO.email);
		setFirstName(userDTO.firstName);
		setLastName(userDTO.lastName);
		setUserType(userDTO.userType);
		setUsername(userDTO.username);
		setPassword(userDTO.password);
		// TODO
	}

	public void setEmail (String email) {
		this.email = email;
	}

	public void setFirstName (String firstName) {
		this.firstName = firstName;
	}

	public void setLastName (String lastName) {
		this.lastName = lastName;
	}

	public void setUserType (UserType userType) {
		this.userType = userType;
	}

	public void setUsername (String username) {
		this.username = username;
	}

	public void setPassword (String password) {
		this.password = password;
	}

	public User (long id, String email, String firstName, String lastName, String password, UserType userType, String username) {
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.userType = userType;
		this.username = username;
	}

	public User () {}

	public Collection<Course> allCoursesGet () {return getUserType().equals(UserType.Professor) ? getProfessorCourses() : getStudentCourses();}

	@Basic
	@Column (name = "userType", nullable = false, insertable = true, updatable = true, length = 255)
	public UserType getUserType () {
		return userType;
	}

	@OneToMany (mappedBy = "professor")
	public Collection<Course> getProfessorCourses () {
		return professorCourses;
	}

	@ManyToMany (mappedBy = "students")
	public Collection<Course> getStudentCourses () {
		return studentCourses;
	}

	@Basic
	@Column (name = "email", nullable = true, insertable = true, updatable = true, length = 255)
	public String getEmail () {
		return email;
	}

	@Basic
	@Column (name = "firstName", nullable = false, insertable = true, updatable = true, length = 255)
	public String getFirstName () {
		return firstName;
	}

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column (name = "id", nullable = false, insertable = true, updatable = true)
	public Long getId () {
		return id;
	}

	@Basic
	@Column (name = "lastName", nullable = false, insertable = true, updatable = true, length = 255)
	public String getLastName () {
		return lastName;
	}

	@Basic
	@Column (name = "password", nullable = true, insertable = true, updatable = true, length = 255)
	public String getPassword () {
		return password;
	}

	@Basic
	@Column (name = "username", nullable = false, insertable = true, updatable = true, length = 255)
	public String getUsername () {
		return username;
	}

	@Override
	public int hashCode () {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (username != null ? username.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (userType != null ? userType.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) { return true; }
		if (o == null || getClass() != o.getClass()) { return false; }

		User user = (User) o;

		if (id != user.id) { return false; }
		if (email != null ? !email.equals(user.email) : user.email != null) { return false; }
		if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) { return false; }
		if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) { return false; }
		if (password != null ? !password.equals(user.password) : user.password != null) { return false; }
		if (username != null ? !username.equals(user.username) : user.username != null) { return false; }

		return true;
	}

	@Override
	public String toString () {
		return "User{" +
			   "email='" + email + '\'' +
			   ", firstName='" + firstName + '\'' +
			   ", id=" + id +
			   ", lastName='" + lastName + '\'' +
			   ", password='" + password + '\'' +
			   ", professorCourses=" + professorCourses +
			   ", studentCourses=" + studentCourses +
			   ", userType=" + userType +
			   ", username='" + username + '\'' +
			   '}';
	}

	public void setCourses (Collection<Course> courses) {
		if (getUserType().equals(UserType.Professor)) { setProfessorCourses(courses); } else { setStudentCourses(courses); }
	}

	public void setProfessorCourses (Collection<Course> professorCourses) {
		this.professorCourses = professorCourses;
	}

	public void setStudentCourses (Collection<Course> studentCourses) {
		this.studentCourses = studentCourses;
	}

	public void setId (Long id) {
		this.id = id;
	}
}