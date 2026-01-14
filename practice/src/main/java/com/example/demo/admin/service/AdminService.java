package com.example.demo.admin.service;

import com.example.demo.admin.entity.Admin;

public interface AdminService {
	
	Admin create(Admin admin);
	
	Admin findByEmail(String email);
	
	boolean authenticate(String email, String password);

}
