package com.sillibus.web.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {
	List<User> findAllByLastName (String lastName);
	List<User> findAllByLastNameLike (String lastName);
	List<User> findByLastName (String lastName);
	List<User> findByLastNameStartsWithIgnoreCase (String lastName);
	User findByUsername (String username);
}