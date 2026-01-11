package com.example.demo.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.contact.entity.Contact;
import com.example.demo.contact.service.ContactService;


@Controller
public class AdminController {

	@Autowired
	private ContactService contactService;
	
	@GetMapping("/admin/contacts")
	public String contactList(Model model) {
		
		System.out.println("========================================");
		System.out.println("AdminController が呼ばれました！");
		System.out.println("========================================");
		
		List<Contact> contacts = contactService.findAll();
		
		model.addAttribute("contacts", contacts);
		
		return "contactList";
		
	}
}
