package com.sillibus.web;

import com.sillibus.web.model.User;
import com.sillibus.web.model.UserRepository;
import com.sillibus.web.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 Created by joshua on 10/1/15.
 */
@RestController
@RequestMapping ("/users")
public class UserController {
	@Autowired UserRepository userRepository;

	@RequestMapping (value = { "/new", "/create" }, method = RequestMethod.PUT)
	public User create (HttpServletRequest request, @RequestBody UserDto userDto) {
		User user = new User();
		user.setFirstName(userDto.firstName);
		user.setLastName(userDto.lastName);
		user.setUsername(userDto.username);
		user.setEmail(userDto.email);
		user.setPassword(userDto.password);
		return userRepository.save(user);
	}

	@RequestMapping ("/findByLastName/{lastName}")
	public List<User> findByLastName (@PathVariable ("lastName") String lastName) {
		return userRepository.findByLastNameStartsWithIgnoreCase(lastName);
	}

	@RequestMapping ("")
	public User findByUsername (@RequestParam ("username") String username) {
		return userRepository.findByUsername(username);
	}

	@RequestMapping ("/greet/{id}")
	public String greeting (@PathVariable ("id") long id) {
		User user = userRepository.findOne(id);
		return "Greetings, " + user.getFirstName() + "!\n\n" + user.toString();
	}

	@RequestMapping ("/list")
	public Iterable<User> listAll () {
		return userRepository.findAll();
	}
}