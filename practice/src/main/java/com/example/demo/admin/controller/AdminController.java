package com.example.demo.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.admin.entity.Admin;
import com.example.demo.admin.form.AdminSigninForm;
import com.example.demo.admin.form.AdminSignupForm;
import com.example.demo.admin.service.AdminService;
import com.example.demo.contact.entity.Contact;
import com.example.demo.contact.form.ContactForm;
import com.example.demo.contact.service.ContactService;

@Controller
public class AdminController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private AdminService adminService;

    // 新規登録画面
    @GetMapping("/admin/signup")
    public String adminSignupForm(Model model) {
        model.addAttribute("adminSignupForm", new AdminSignupForm());
        return "signup";
    }

    // 新規登録処理
    @PostMapping("/admin/signup") 
    public String signup(@Validated @ModelAttribute AdminSignupForm form,
            BindingResult errorresult, Model model) {
        if (errorresult.hasErrors()) {
            return "signup";
        }

        Admin admin = new Admin();
        admin.setLastName(form.getLastName());
        admin.setFirstName(form.getFirstName());
        admin.setEmail(form.getEmail());
        admin.setPassword(form.getPassword());

        adminService.create(admin);

        return "redirect:/admin/signin";  
    }

    // ログイン画面
    @GetMapping("/admin/signin")  
    public String adminSigninForm(Model model) {
        model.addAttribute("adminSigninForm", new AdminSigninForm());
        return "signin";
    }


    // お問い合わせ一覧
    @GetMapping("/admin/contacts")
    public String contactList(Model model) {
        List<Contact> contacts = contactService.findAll();
        model.addAttribute("contacts", contacts);
        return "contactList";
    }

    // お問い合わせ詳細画面
    @GetMapping("/admin/contacts/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Contact contact = contactService.findById(id);
        model.addAttribute("contact", contact);
        return "detail";
    }

    // お問い合わせ編集
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

    // お問い合わせ内容編集更新
    @PostMapping("/admin/contacts/{id}/update")
    public String update(@PathVariable Long id, @ModelAttribute ContactForm form) {
        contactService.updateContact(id, form);
        return "redirect:/admin/contacts/" + id;
    }

    // お問い合わせ内容削除
    @PostMapping("/admin/contacts/{id}/delete")
    public String delete(@PathVariable Long id) {
        contactService.deleteContact(id);
        return "redirect:/admin/contacts";
    }
}