package com.vidal.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.vidal.springboot.entities.User;
import com.vidal.springboot.interfaces.UserRepository;

@Controller
public class AppController {
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String viewRegisterPage(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}
	
	@PostMapping("/action_register")
	public String actionRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encoderPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encoderPassword);
		
		userRepo.save(user);
		
		return "succeed";
	}
	
	@GetMapping("/login")
	public String viewLoginPage(Model model) {
		model.addAttribute("user", new User());
		return "signin";
	}
}
