package com.javaClass.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaClass.demo.entity.Order;
import com.javaClass.demo.entity.Tiffinuser;
import com.javaClass.demo.repository.OrderRepository;
import com.javaClass.demo.repository.UserRepository;

@Service
public class OrderDemoService implements OrderService {

	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private UserRepository userRepo;

	@Override
	public Order AddOrder(Order order) {
		// TODO Auto-generated method stub
		Tiffinuser user = userRepo.findById(order.getUserId()).get();
		int remaining = user.getRemaining();
		if (remaining == 0) {
			return order;
		}
		user.setRemaining(remaining - 1);
		userRepo.save(user);

		long count = orderRepo.count();
		if (count == 0)
			order.setOrderId(1);
		else
			order.setOrderId((int) (count + 1));

		Order orderdb = orderRepo.save(order);
		return orderdb;
	}

	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		List<Order> orderList = orderRepo.findAll();
		return orderList;
	}

	@Override
	public Optional<Order> getOrder(int id) {
		// TODO Auto-generated method stub
		Optional<Order> orderList = orderRepo.findById(id);
		return orderList;
	}

	@Override
	public List<Order> getUserOrder(int id) {
		// TODO Auto-generated method stub
		List<Order> orders = orderRepo.findByUser(id);

		return orders;
	}

	@Override
	public Order updateOrder(Order user) {
		// TODO Auto-generated method stub
		int userId = user.getUserId();
		Order userToUpdate = orderRepo.findById(userId).orElse(null);
		if (userToUpdate != null) {
			Order userList = orderRepo.save(user);
			return userList;
		} else
			return user;
	}

	@Override
	public void deleteOrder(int id) {
		// TODO Auto-generated method stub
		Order order = orderRepo.findById(id).orElse(null);
		if (order != null) {
			orderRepo.deleteById(id);
		}
		return;
	}

}
