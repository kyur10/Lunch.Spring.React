package com.javaClass.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaClass.demo.entity.Tiffinuser;
import com.javaClass.demo.entity.login;
import com.javaClass.demo.repository.UserRepository;

@Service
public class UserDemoService implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public Tiffinuser AddUser(Tiffinuser user) {
		// TODO Auto-generated method stub
		user.setUserId(0);
		Tiffinuser userdb = userRepo.save(user);
		return userdb;
	}

	@Override
	public Tiffinuser Login(login login) {

	List<Tiffinuser> users =  	userRepo.findByUser(login.getName(), login.getPassword());
	Tiffinuser user = users.get(0);
	return user;
	}
	
	@Override
	public List<Tiffinuser> getAllUsers() {
		// TODO Auto-generated method stub
		List<Tiffinuser> userList = userRepo.findAll();
		return userList;
	}
	
	@Override
	public Optional<Tiffinuser> getUser(int id) {
		// TODO Auto-generated method stub
		Optional<Tiffinuser> userList = userRepo.findById(id);
		return userList;
	}

	@Override
	public Tiffinuser updateUser(Tiffinuser user) {
		// TODO Auto-generated method stub
		int userId = user.getUserId();
		Tiffinuser userToUpdate = userRepo.findById(userId).orElse(null);
		if (userToUpdate != null) {
			Tiffinuser userList = userRepo.save(user);
			return userList;
		} else
			return user;
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		Tiffinuser userToUpdate = userRepo.findById(id).orElse(null);
		if (userToUpdate != null) {
			userRepo.deleteById(id);
		}
		return;
	}

}
