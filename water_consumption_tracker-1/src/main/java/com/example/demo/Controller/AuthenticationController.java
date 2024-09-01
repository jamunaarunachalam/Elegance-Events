package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.AuthenticationRequest;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.dto.response.AuthenticationResponse;
import com.example.demo.entity.Login;

import com.example.demo.entity.UserRegister;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.LoginService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	private final LoginService li;
	private final AuthenticationService service;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
		service.register(request);
		return ResponseEntity.ok("Registered Successfully");
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
		return ResponseEntity.ok(service.authenticate(request));
	}

	

	@PostMapping("/postRegister")
	public String postRegister(@RequestBody UserRegister re) {
		service.postRegister(re);
		return "updated Successfully!";
	}

	@DeleteMapping("/deleteRegister")
	public String deleteRegister(int userRegId) {
		service.deleteRegister(userRegId);
		return "Deleted Successfully!";
	}

	@PutMapping("/updateRegister/{id}")
	public String updateRegister(@RequestBody UserRegister re, @PathVariable long userRegId) {
		re.setUserRegId(userRegId);
		service.updateRegister(re);
		return "updated Successfully!";
	}

}