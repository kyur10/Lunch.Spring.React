package com.javaClass.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaClass.demo.entity.Order;
import com.javaClass.demo.entity.Tiffinuser;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	@Query(value = "SELECT * FROM tiffinorder WHERE userId=?",nativeQuery = true)
    public List<Order> findByUser(int id);
}
