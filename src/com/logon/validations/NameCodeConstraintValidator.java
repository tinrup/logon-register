package com.logon.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class NameCodeConstraintValidator 
		implements ConstraintValidator<NameValidCode, String>{

	private String coursePrefix;
	@Override
	public void initialize(NameValidCode theCourseCode) {
	}
	
	@Override					
	public boolean isValid(String theCode, 
			ConstraintValidatorContext theConstraintValidatorContext) {
									//test if the form data starts with our course prefix
	boolean result;
	
	if(theCode == null) {
	result = false;
	}else {
		result = true;
	}
	
	return result;
}

	
	
	
	

}
