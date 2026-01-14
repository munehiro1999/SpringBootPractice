package com.example.demo.contact.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.contact.entity.Contact;


public interface ContactRepository extends JpaRepository<Contact, Long>{
}
