package com.vidal.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vidal.springboot.entities.User;
import com.vidal.springboot.interfaces.UserRepository;

public class UserDetailsSession implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepo.findByEmail(username);
		
		// Define if user exists
		if(user == null) {
			throw new UsernameNotFoundException("Not Found");
		}
		
		return new UserSecurity(user);
	}
	
}
