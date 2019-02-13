package com.logon.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


// @targer - where can i apply this new annotation--on metods or fields
// \retention - how long will mark annotation will be stored or used - retain this anotation in the bitecode and also use it at runtime by the JVM   
@Constraint(validatedBy = NameCodeConstraintValidator.class)
@Target( {ElementType.METHOD, ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface NameValidCode {
	

	

	public String message() default "*";

	public Class<?>[] groups() default {};
	
	
	//payloads provide custom details about validation failure (secutiry level, erro code etc)
//	default payloads 
	public Class<? extends Payload>[] payload() default{};

}
