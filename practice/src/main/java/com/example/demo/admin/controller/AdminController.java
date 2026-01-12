package com.example.demo.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.contact.entity.Contact;
import com.example.demo.contact.form.ContactForm;
import com.example.demo.contact.service.ContactService;

@Controller
public class AdminController {
    
    @Autowired
    private ContactService contactService;
    
    @GetMapping("/admin/contacts")
    public String contactList(Model model) {
        List<Contact> contacts = contactService.findAll();
        model.addAttribute("contacts", contacts);
        return "contactList";
    }
    
    @GetMapping("/admin/contacts/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Contact contact = contactService.findById(id);
        model.addAttribute("contact", contact);
        return "detail";
    }
    
    @GetMapping("/admin/contacts/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Contact contact = contactService.findById(id);
        
        ContactForm form = new ContactForm();
        form.setLastName(contact.getLastName());
        form.setFirstName(contact.getFirstName());
        form.setEmail(contact.getEmail());
        form.setPhone(contact.getPhone());
        form.setZipCode(contact.getZipCode());
        form.setAddress(contact.getAddress());
        form.setBuildingName(contact.getBuildingName());
        form.setContactType(contact.getContactType());
        form.setBody(contact.getBody());
        
        model.addAttribute("form", form);
        model.addAttribute("id", id);
        return "edit";
    }
    
    @PostMapping("/admin/contacts/{id}/update")
    public String update(@PathVariable Long id, @ModelAttribute ContactForm form) {
        contactService.updateContact(id, form);
        return "redirect:/admin/contacts/" + id;
    }
    
    @PostMapping("/admin/contacts/{id}/delete")
    public String delete(@PathVariable Long id) {
        contactService.deleteContact(id);
        return "redirect:/admin/contacts";
    }
}