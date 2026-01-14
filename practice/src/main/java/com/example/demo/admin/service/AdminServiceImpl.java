package com.example.demo.admin.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.admin.entity.Admin;
import com.example.demo.admin.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
		private AdminRepository adminRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Admin create(Admin admin) {
		// passwordEncoder.encode();でパスワード暗号化
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		
		LocalDateTime now = LocalDateTime.now();
		admin.setCreatedAt(now);
		admin.setUpdatedAt(now);
		
		return adminRepository.save(admin);
	}

	@Override
	public Admin findByEmail(String email) {
		return adminRepository.findByEmail(email).orElse(null);
	}

	@Override
	public boolean authenticate(String email, String password) {
		Admin admin = findByEmail(email);
		
		if (admin == null) {
			return false;
		}
		return passwordEncoder.matches(password, admin.getPassword());
	}
	
	
}
