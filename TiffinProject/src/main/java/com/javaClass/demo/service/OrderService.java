package com.javaClass.demo.service;

import java.util.List;
import java.util.Optional;

import com.javaClass.demo.entity.Order;

public interface OrderService {
	public Order AddOrder(Order order);

	public List<Order> getAllOrders();
	public Optional<Order> getOrder(int id);
	public List<Order> getUserOrder(int id);

	public Order updateOrder(Order user);

	public void deleteOrder(int id);

}
