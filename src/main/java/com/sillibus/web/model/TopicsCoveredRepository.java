package com.sillibus.web.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TopicsCoveredRepository extends CrudRepository<TopicsCovered, Long> {
	List<TopicsCovered> findAllByCourse (Course course);
}