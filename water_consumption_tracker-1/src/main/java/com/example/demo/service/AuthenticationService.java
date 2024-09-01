package com.example.demo.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.AuthenticationRequest;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.dto.response.AuthenticationResponse;
import com.example.demo.entity.Login;

import com.example.demo.entity.UserRegister;
import com.example.demo.entity.enumerate.Role;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.UserRegisterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private final UserRegisterRepository userRegisterRepository ; 
	 private final PasswordEncoder passwordEncoder;
	 private final JwtService jwtservice;
	 private final LoginRepository loginRepository;
	 private final AuthenticationManager authenticationManager;
	public AuthenticationResponse register(RegisterRequest request) {
		// TODO Auto-generated method stub
	
			
	    var user = UserRegister.builder()
	            .username(request.getUsername())
	            .emailid(request.getEmailid())
	            .mobileno(request.getMobileno())
	            .password(passwordEncoder.encode(request.getPassword()))
	            .role(Role.USER)
	            .build();
		Login build = Login.builder().username(request.getUsername()).password(passwordEncoder.encode(request.getPassword())).build();
		loginRepository.save(build);
	    userRegisterRepository.save(user); 
	    var jwtToken = jwtservice.generateToken(user);
	    
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
				request.getUsername(),
				request.getPassword()
				)
				);
				var user = userRegisterRepository.findByUsername(request.getUsername())
						.orElseThrow();
				
				 var jwtToken = jwtservice.generateToken(user);
				    
					return AuthenticationResponse.builder()
							.token(jwtToken)
							.build();
	}
	
	
	
	
	public void postRegister(UserRegister re)
	{
		userRegisterRepository.save(re);
	}
	public String deleteRegister(long userRegId)
	{
		userRegisterRepository.deleteById(userRegId);
		return "deleted" ;
	}
	public UserRegister updateRegister(UserRegister re)
	{
		return userRegisterRepository.save(re);
	}

	public List<UserRegister> getRegister() {
		
		return userRegisterRepository.findAll();
	}
//	public Login getUserByUsername(String username) {
//        return loginRepository.findByUsername(username);
//    }
//	
	
	public Login registerUser(String username, String password) {
		Login user = new Login();
		user.setUsername(username);
		user.setPassword(password);
		return loginRepository.save(user);
	}

	public Optional<Login> getUserByUsername(String username) {
		return loginRepository.findByUsername(username);
	}
		
	
	
	

}