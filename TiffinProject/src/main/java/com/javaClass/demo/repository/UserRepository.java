package com.javaClass.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaClass.demo.entity.Tiffinuser;

@Repository
public interface UserRepository extends JpaRepository<Tiffinuser, Integer> {
	@Query(value = "SELECT * FROM tiffinuser WHERE Name=? and Password= ?",nativeQuery = true)
    public List<Tiffinuser> findByUser(String username, String password);
}
