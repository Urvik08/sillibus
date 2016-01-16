package com.sillibus.web.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public interface AssignmentRepository extends CrudRepository<Assignment, Long> {
	//List<Assignment> findAllByAssignmentType (AssignmentType assignmentType);
	List<Assignment> findAllByCourse (Course course);
	@Query ("select a from Assignment a where a.course in ?1 ORDER BY a.dueDate ASC")
	List<Assignment> findAllInCoursesOrderByDueDateAsc (Collection<Course> courses);
}