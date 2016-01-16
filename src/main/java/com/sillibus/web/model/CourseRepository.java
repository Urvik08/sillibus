package com.sillibus.web.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CourseRepository extends CrudRepository<Course, Long> {
	List<Course> findAllByProfessor (User professor);
	Course findByCrn (int crn);
}