package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Login;

import com.example.demo.repository.LoginRepository;

@Service
public class LoginService 
{
	@Autowired
	private LoginRepository userrepo;
	public List<Login> getUser() 
	   {
		  return userrepo.findAll();
	   }
	public void saveUser(Login le)
	   {
		  userrepo.save(le);
	   }
	public List<Login> getLogin()
	{
		return userrepo.findAll();
	}
	public void postLogin(Login le)
	{
		userrepo.save(le);
		
	}
	public void deleteLogin(int userId)
	{
		userrepo.deleteById(userId);
	}
	public Login updateLogin(Login le)
	{
		return userrepo.save(le);
		
	}
	
}
