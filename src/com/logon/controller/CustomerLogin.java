package com.logon.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.logon.entity.Customer;
import com.logon.logic.CustomerMethods;

@Controller
@RequestMapping("/login")
public class CustomerLogin {

	@RequestMapping("/showLoginForm")
	public String showForm(Model theModel) {

		theModel.addAttribute("loginCustomer", new Customer());

		return "login-form";
	}

	@RequestMapping("/processLoginForm")
	public String processForm(@Valid @ModelAttribute("loginCustomer") Customer theCustomer, BindingResult theBindingResult) {
		CustomerMethods cm = new CustomerMethods();
		System.out.println("Errors : " + theBindingResult);
		System.out.println("\n____________________________________________________");
//		if(theBindingResult.hasErrors()) {
//			return "login-form";}
		if(cm.findPasswordByEmail(theCustomer.getEmail(), theCustomer.getPassword()) == true) {
			
			return "login-confirmation";
			}else {

	  			return "login-form";
				}
		
	}

}
