package com.example.demo.contact.service;

import java.util.List;

import com.example.demo.contact.entity.Contact;
import com.example.demo.contact.form.ContactForm;

public interface ContactService {

	void saveContact(ContactForm contactForm);
	
	List<Contact> findAll();
}
