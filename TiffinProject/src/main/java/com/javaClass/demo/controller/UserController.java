package com.javaClass.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaClass.demo.entity.Tiffinuser;
import com.javaClass.demo.entity.login;
import com.javaClass.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<?> AddStudent(@RequestBody Tiffinuser user) {
		Tiffinuser userSaved = userService.AddUser(user);
		return new ResponseEntity<Tiffinuser>(userSaved, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<?> AddStudent(@RequestBody login login) {
		Tiffinuser user = userService.Login(login);
		return new ResponseEntity<Tiffinuser>(user, HttpStatus.CREATED);
	}

	
	@GetMapping
	public ResponseEntity<List<Tiffinuser>> GetAllStudents() {
		List<Tiffinuser> listOfAllusers = userService.getAllUsers();
		return new ResponseEntity<List<Tiffinuser>>(listOfAllusers, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<Optional<Tiffinuser>> GetUser(@RequestParam int id) {
		Optional<Tiffinuser> user = userService.getUser(id);
		return new ResponseEntity<Optional<Tiffinuser>>(user, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Tiffinuser> Update(@RequestBody Tiffinuser user) {
		Tiffinuser listOfAllusers = userService.updateUser(user);
		return new ResponseEntity<Tiffinuser>(listOfAllusers, HttpStatus.OK);
	}

	@DeleteMapping
	public void Delete(@RequestParam int id) {
		userService.deleteUser(id);
		return;
	}

}
