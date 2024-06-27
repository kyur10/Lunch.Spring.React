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

import com.javaClass.demo.entity.Order;
import com.javaClass.demo.entity.Tiffinuser;
import com.javaClass.demo.service.OrderService;
import com.javaClass.demo.service.UserService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping
	public ResponseEntity<?> AddOrder(@RequestBody Order user) {
		Order orderSaved = orderService.AddOrder(user);
		return new ResponseEntity<Order>(orderSaved, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Order>> GetAllOrders() {
		List<Order> listOfAllOrders = orderService.getAllOrders();
		return new ResponseEntity<List<Order>>(listOfAllOrders, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<Optional<Order>> GetOrder(@RequestParam int id) {
		Optional<Order> orders = orderService.getOrder(id);
		return new ResponseEntity<Optional<Order>>(orders, HttpStatus.OK);
	}

	@GetMapping("/user")
	public ResponseEntity<List<Order>> GetUserOrder(@RequestParam int id) {
		List<Order> orders = orderService.getUserOrder(id);
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Order> Update(@RequestBody Order user) {
		Order orders = orderService.updateOrder(user);
		return new ResponseEntity<Order>(orders, HttpStatus.OK);
	}

	@DeleteMapping
	public void Delete(@RequestBody int id) {
		orderService.deleteOrder(id);
		return;
	}

}
