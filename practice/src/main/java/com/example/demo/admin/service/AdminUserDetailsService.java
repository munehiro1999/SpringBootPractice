package com.example.demo.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.admin.entity.Admin;

@Service
public class AdminUserDetailsService implements UserDetailsService{
	
	@Autowired
	private AdminService adminService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Admin admin = adminService.findByEmail(email);
		if (admin == null) {
			throw new UsernameNotFoundException("User not found");
			
		}
		
		return User.builder()
				.username(admin.getEmail())
				.password(admin.getPassword())
				.roles("ADMIN")
				.build();
	}
}
