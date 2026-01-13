package com.example.demo.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.admin.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
		private AdminRepository adminRepostitory;
	
}
