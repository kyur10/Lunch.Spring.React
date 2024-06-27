package com.javaClass.demo.service;

import java.util.List;
import java.util.Optional;

import com.javaClass.demo.entity.Tiffinuser;
import com.javaClass.demo.entity.login;

public interface UserService {
	public Tiffinuser AddUser(Tiffinuser user);
	public Tiffinuser Login(login login);

	public List<Tiffinuser> getAllUsers();
	public Optional<Tiffinuser> getUser(int id);

	public Tiffinuser updateUser(Tiffinuser user);

	public void deleteUser(int id);
}
