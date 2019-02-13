package com.logon.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.logon.validations.*;

@Entity
@Table(name = "customer")
public class Customer {
	

	private static final String EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "first_name")
	@NameValidCode
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Size(min = 6, message = "minimum 6 characters")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$", message = "digit,lower case and Upper case letters must occur at least once, and"
			+ " no space allowed in the entire string .")
	@Column(name = "password")
	private String password;

	@Pattern(regexp = EMAIL, message = "email is not correct")
	@Column(name = "email")
	@EmailValidCode
	private String email;

	@Column(name = "signature_Id")
	private String signatureId;

	public Customer() {

	}

	public Customer(int id, String firstName, String lastName, String password, String email) {
	
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		firstName = firstName.trim();
		this.firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		lastName = lastName.trim();
		this.lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
	}

	public String getPassword() {
	
		return password;
	}

	public void setPassword(String password) {
	

		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		email = email.trim();
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
				+ ", email=" + email + "]";
	}

}
