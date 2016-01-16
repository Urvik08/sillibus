package com.sillibus.web.model;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.sql.Timestamp;

@Entity
@Transactional
@Table (name = "topic_covered")
public class TopicsCovered {
	private Course    course;
	private Timestamp date;
	private String    description;
	private Long      id;

	@ManyToOne
	@JoinColumn (name = "course_id", referencedColumnName = "id", nullable = false)
	public Course getCourse () {
		return course;
	}

	@Basic
	@Column (name = "date", nullable = false, insertable = true, updatable = true)
	public Timestamp getDate () {
		return date;
	}

	@Basic
	@Column (name = "description", nullable = false, insertable = true, updatable = true, length = 255)
	public String getDescription () {
		return description;
	}

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column (name = "id", nullable = false, insertable = true, updatable = true)
	public Long getId () {
		return id;
	}

	@Override
	public int hashCode () {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) { return true; }
		if (o == null || getClass() != o.getClass()) { return false; }

		TopicsCovered that = (TopicsCovered) o;

		if (id != that.id) { return false; }
		if (course != null ? !course.equals(that.course) : that.course != null) { return false; }
		if (date != null ? !date.equals(that.date) : that.date != null) { return false; }
		if (description != null ? !description.equals(that.description) : that.description != null) { return false; }

		return true;
	}

	public void setCourse (Course course) {
		this.course = course;
	}

	public void setDate (Timestamp date) {
		this.date = date;
	}

	public void setDescription (String description) {
		this.description = description;
	}

	public void setId (Long id) {
		this.id = id;
	}
}