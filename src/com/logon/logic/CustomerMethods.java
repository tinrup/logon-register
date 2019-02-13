package com.logon.logic;

import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.logon.entity.Customer;
import com.logon.interfaces.CustomerQuery;

public class CustomerMethods implements CustomerQuery {
	
	
	public static String passToHash(String password) {
		String str1 = "";
		String str2 = "";
		for (int i = 0; i < password.length(); i++) {
			if (Character.isLowerCase(password.charAt(i))) {

				str2 += password.charAt(i);

			}
			if (Character.isUpperCase(password.charAt(i))) {

				str1 += password.charAt(i);
			}

		}
		int a = password.hashCode();
		String s = Integer.toString(a);
		password = str1 + s.substring(0,s.length() /2) + str2.substring(0, 1) + s.substring(s.length()/2);
		
	return password;
	}
		

	@Override
	public void createStudent(int id, String name, String lastName, String password, String email) {
	
		
		SessionFactory sessionFactory = sessionFactory();
		Session session = sessionFactory.getCurrentSession();

		try {

//			System.out.println("Creating new student object...");
			Customer tempStudent = new Customer(id, name, lastName, passToHash(password), email.toLowerCase());

			session.beginTransaction();
//			System.out.println("Saving the student...");
			session.save(tempStudent);

			session.getTransaction().commit();

			System.out.println("Customer is savet into db");

		} finally {
			sessionFactory.close();

		}

	}

	@Override
	public boolean deleteStudentByName(String name) {
		boolean bool = false;
		SessionFactory sessionFactory = sessionFactory();
		Session session = sessionFactory.getCurrentSession();
		if (findByName(name) == true) {
			bool = true;
			try {
				session = sessionFactory.getCurrentSession();
				session.beginTransaction();

				System.out.println("Deleting Customer firstName=" + name);
				session.createQuery("delete from Customer where firstName=" + "'" + name + "'").executeUpdate();
				session.getTransaction().commit();
			} finally {
				sessionFactory.close();
			}
		} else {
			System.out.println("There is no student by the name " + name);
		}

		return bool;
	}

	@Override
	public boolean deleteStudentByLastName(String lastName) {
		boolean bool = false;
		SessionFactory sessionFactory = sessionFactory();
		Session session = sessionFactory.getCurrentSession();
		if (findByLastName(lastName) == true) {
			bool = true;
			try {
				session = sessionFactory.getCurrentSession();
				session.beginTransaction();

				System.out.println("Deleting student last name=" + lastName);
				session.createQuery("delete from Customer where lastName=" + "'" + lastName + "'").executeUpdate();
				session.getTransaction().commit();
			} finally {
				sessionFactory.close();
			}
		} else {
			System.out.println("There is no student by the last name " + lastName);
		}

		return bool;
	}

	@Override
	public boolean findPasswordByEmail(String email, String password) {
		boolean bool;
		SessionFactory sessionFactory = sessionFactory();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			List<Object> theStudents = session.createQuery("from Customer").getResultList();

			String name1 = "'" + email.toLowerCase() + "'";
			theStudents = session
					.createQuery("from Customer s where s.email=" + name1 + " AND s.password='" + passToHash(password) + "'")
					.getResultList();

			if (!theStudents.isEmpty()) {
				displayStudents(theStudents);
				session.getTransaction().commit();
				bool = true;
			} else {
				bool = false;
				System.out.println("username or password does not meet with DB");
				session.getTransaction().commit();
			}

		} finally {
			sessionFactory.close();
		}
		return bool;
	}

	@Override
	public boolean findByEmail(String email) {
		boolean bool = true;
		SessionFactory sessionFactory = sessionFactory();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			List<Object> theStudents = session.createQuery("from Customer").getResultList();

			String name1 = "'" + email + "'";
			theStudents = session.createQuery("from Customer s where s.email=" + name1).getResultList();

			if (!theStudents.isEmpty()) {

				bool = false;
				System.out.println("there is alredy student with this email");
				// display the students
				System.out.println("\n\nStudents who have the email" + email);

				displayStudents(theStudents);
				session.getTransaction().commit();

				System.out.println("Done!");
			} else {
				System.out.println("There is no student by the email " + email);
				session.getTransaction().commit();
			}

		} finally {
			sessionFactory.close();
		}
		return bool;
	}

	@Override
	public boolean deleteStudentByEmail(String email) {
		boolean bool = false;
		SessionFactory sessionFactory = sessionFactory();
		Session session = sessionFactory.getCurrentSession();
		if (findByEmail(email) == true) {
			bool = true;
			try {
				session = sessionFactory.getCurrentSession();
				session.beginTransaction();

				System.out.println("Deleting student email=" + email);
				session.createQuery("delete from Customer where email=" + "'" + email + "'").executeUpdate();
				session.getTransaction().commit();
			} finally {
				sessionFactory.close();
			}
		} else {
			System.out.println("There is no student by the email" + email);
		}

		return bool;
	}

	@Override
	public boolean findByName(String name) {
		boolean bool = false;
		SessionFactory sessionFactory = sessionFactory();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			List<Object> theStudents = session.createQuery("from Customer").getResultList();

			String name1 = "'" + name + "'";
			theStudents = session.createQuery("from Customer s where s.firstName=" + name1).getResultList();

			if (!theStudents.isEmpty()) {

				bool = true;

				// display the students
				System.out.println("\n\nStudents who have the first name " + name);

				displayStudents(theStudents);
				session.getTransaction().commit();

				System.out.println("Done!");
			} else {
				System.out.println("There is no student by the name " + name);
			}

		} finally {
			sessionFactory.close();
		}
		return bool;
	}

	@Override
	public boolean findByLastName(String lastName) {
		boolean bool = false;
		SessionFactory sessionFactory = sessionFactory();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			List<Object> theStudents = session.createQuery("from Customer").getResultList();

			String name1 = "'" + lastName + "'";
			theStudents = session.createQuery("from Customer s where s.lastName=" + name1).getResultList();

			if (!theStudents.isEmpty()) {

				bool = true;

				// display the students
				System.out.println("\n\nStudents who have the last name " + lastName);

				displayStudents(theStudents);
				session.getTransaction().commit();

				System.out.println("Done!");
			} else {
				System.out.println("There is no student by the last name " + lastName);
			}

		} finally {
			sessionFactory.close();
		}
		return bool;
	}

	@Override
	public boolean updateStudentName(String email, String name) {
		boolean bool = false;

		if (findByEmail(email) == true) {

			SessionFactory factory = sessionFactory();
			Session session = factory.getCurrentSession();

			try {

				session.beginTransaction();

				System.out.println("Update last name for student ");

				session.createQuery(
						"update Customer set firstName=" + "'" + name + "'" + "where email=" + "'" + email + "'")
						.executeUpdate();

				session.getTransaction().commit();

				System.out.println("Done!");

			} finally {
				factory.close();
			}
		} else {
			System.out.println("there is no email " + email);
		}
		return bool;
	}

	@Override
	public boolean updateStudentLastName(String email, String lastName) {
		boolean bool = false;

		if (findByEmail(email) == true) {

			SessionFactory factory = sessionFactory();
			Session session = factory.getCurrentSession();

			try {

				session.beginTransaction();

				System.out.println("Update last name for student ");

				session.createQuery(
						"update Customer set lastName=" + "'" + lastName + "'" + "where email=" + "'" + email + "'")
						.executeUpdate();

				session.getTransaction().commit();

				System.out.println("Done!");

			} finally {
				factory.close();
			}
		} else {
			System.out.println("there is no email " + email);
		}
		return bool;
	}

	@Override
	public void updateSignatureId(String email) {

		String signatureID = randomString().substring(0, 5);
		SessionFactory factory = sessionFactory();
		Session session = factory.getCurrentSession();
		try {

			session.beginTransaction();

			System.out.println("Update signatureId for Customer ");

			session.createQuery(
					"update Customer set signature_id=" + "'" + signatureID + "'" + "where email=" + "'" + email + "'")
					.executeUpdate();

			session.getTransaction().commit();

			System.out.println("Done!");

		} finally {
			factory.close();
		}

	}

	public String randomString() {
		String ran = "abcd_efghijklMNOPQRSTUVWX4567YZ" + "8ABCD90EF.GHIJKLmnopqrstuvwxy0123z";

		String b = "";
		for (int i = 1; i < ran.length(); i++) {
			int random = (int) (Math.random() * 12);
			char c = ran.charAt(i);
			if (random % i == 0) {
				b = b + c + random;
			}
		}
		return b;

	}

	@Override
	public String findSignatureId(String email) {
		String signature;
		SessionFactory sessionFactory = sessionFactory();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			List<Object> theStudents = session.createQuery("from Customer").getResultList();

			String name1 = "'" + email + "'";
			theStudents = session.createQuery("select signatureId from Customer s where s.email=" + name1)
					.getResultList();

			signature = theStudents.toString().substring(1, theStudents.toString().length() - 1);
			if (!theStudents.isEmpty()) {

				// display the students
				System.out.println("\n\nStudents who have the last name ");

				displayStudents(theStudents);
				session.getTransaction().commit();

				System.out.println("Done!");
			} else {
				System.out.println("There is no student by the last name ");
			}

		} finally {
			sessionFactory.close();
		}
		return signature;
	}

	@Override
	public String findAndReturnMail(String email) {
		String signature;
		SessionFactory sessionFactory = sessionFactory();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			List<Object> theStudents = session.createQuery("from Customer").getResultList();

			String name1 = "'" + email + "'";
			theStudents = session.createQuery("select s.email from Customer s where s.email=" + name1).getResultList();

			signature = theStudents.toString();
			if (!theStudents.isEmpty()) {

				// display the students
				System.out.println("\n\nStudents EMAIL ");

				displayStudents(theStudents);
				session.getTransaction().commit();

				System.out.println("Done!");
			} else {
				System.out.println("There is no EMAIL ");
				displayStudents(theStudents);
				System.out.println(theStudents);
			}

		} finally {
			sessionFactory.close();
		}
		return signature;
	}

	@Override
	public boolean updateStudentEmail(String email) {
		boolean bool = false;

		if (findByEmail(email) == true) {

			SessionFactory factory = sessionFactory();
			Session session = factory.getCurrentSession();

			try {

				session.beginTransaction();

				System.out.println("Update email for student ");

				session.createQuery(
						"update Customer set email=" + "'" + email + "'" + "where email=" + "'" + email + "'")
						.executeUpdate();

				session.getTransaction().commit();

				System.out.println("Done!");

			} finally {
				factory.close();
			}
		} else {
			System.out.println("there is no email " + email);
		}
		return bool;
	}

	@Override
	public void viewAllStudents() {
		SessionFactory factory = sessionFactory();
		Session session = factory.getCurrentSession();
		try {

			session.beginTransaction();
			List<Object> theStudents = session.createQuery("from Customer").getResultList();

			displayStudents(theStudents);
			session.getTransaction().commit();
		} finally {
			factory.close();
		}

	}

	@Override
	public void displayStudents(List<Object> theStudents) {
		for (Object tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	@Override
	public SessionFactory sessionFactory() {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class)
				.buildSessionFactory();
		return factory;
	}

	@Override
	public void findMeAStudent(String something) {
		SessionFactory factory = sessionFactory();
		Session session = factory.getCurrentSession();
		try {

			session.beginTransaction();
			List<Object> theStudents = session.createQuery("from Customer").getResultList();
			theStudents = session
					.createQuery("from Customer s where" + " s.firstName LIKE '%" + something
							+ "%' OR s.lastName LIKE '%" + something + "%' OR s.email LIKE '%" + something + "%'")
					.getResultList();

			displayStudents(theStudents);
		} finally {
			factory.close();
		}

	}

}
