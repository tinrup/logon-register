package com.logon.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.logon.logic.CustomerMethods;

public class EmailCodeConstraintValidator 
		implements ConstraintValidator<EmailValidCode, String>{

	private String coursePrefix;
	@Override
	public void initialize(EmailValidCode theCourseCode) {
	}
	
	@Override					
	public boolean isValid(String theCode, 
			ConstraintValidatorContext theConstraintValidatorContext) {
									//test if the form data starts with our course prefix
	boolean result;
	CustomerMethods cm = new CustomerMethods();
		if(theCode != null) {
		result = cm.findByEmail(theCode);
		}else {
			result = true;
		}
		
		return result;
	}
	
	
	
	
	
	

}
