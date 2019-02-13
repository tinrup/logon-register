package com.logon.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.logon.email.SendEmail;
import com.logon.entity.Customer;
import com.logon.logic.CustomerMethods;

@Controller
@RequestMapping("/register")
public class CustomerRegistration {
	
//	@InitBinder
//	public void initBinder(WebDataBinder dataBinder) {
//		
//		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
//		
//		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
//	}
	
	
	@RequestMapping("/showForm")
	public String showForm(Model theModel) {
		
		theModel.addAttribute("customer", new Customer());
	
		return "registration-form";
	}
	
	
	@RequestMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("customer") Customer theCustomer,
	BindingResult theBindingResult){
		
	SendEmail sendEmail = new SendEmail();
		CustomerMethods cm = new CustomerMethods();
		
		if(theBindingResult.hasErrors()) {
			return "registration-form";
		}else if(cm.findByEmail(theCustomer.getEmail()) == false) {

				return "registration-form";
			
		}else {
			try {
				cm.createStudent(theCustomer.getId(),theCustomer.getFirstName(), theCustomer.getLastName(),
						theCustomer.getPassword(), theCustomer.getEmail());
				cm.updateSignatureId(theCustomer.getEmail());
				sendEmail.sendIt(theCustomer.getEmail(),cm.findSignatureId(theCustomer.getEmail()),theCustomer.getFirstName(),theCustomer.getLastName());
			}catch(Exception e) {
				e.getMessage();
				return "registration-form";
			}
			return "registration-confirmation";
				
		}
	}
}
