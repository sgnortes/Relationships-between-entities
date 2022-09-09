package com.hibernaterelationships;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernaterelationships.entity.Address;
import com.hibernaterelationships.entity.Customer;
import com.hibernaterelationships.entity.Email;
import com.hibernaterelationships.entity.Order;
import com.hibernaterelationships.entity.Product;

public class ReadCustomer {

	public static void main(String[] args) {
		// Creation of session
		SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class).addAnnotatedClass(Address.class).addAnnotatedClass(Email.class).addAnnotatedClass(Order.class).addAnnotatedClass(Product.class).buildSessionFactory();
		Session mySession = myFactory.openSession();
					
		try {
			
			// Transaction begins
			mySession.beginTransaction();
			
			// Retrieving customer from database
			String hql = "FROM Customer WHERE name = 'Roberto'";
			Query query = mySession.createQuery(hql);
			query.setMaxResults(1);
			Customer result1 = (Customer) query.getSingleResult();
			
			// Retrieving email from database - we use this query to show bidirectional relationship between customer and email entities
			hql = "FROM Email WHERE email = 'email@email.com'";
			query = mySession.createQuery(hql);
			query.setMaxResults(1);
			Email result2 = (Email) query.getSingleResult();
						
			// Show address data from customer - OneToOne unidirectional
			System.out.println("Customer read from database: " + result1.getName() + ", lives in: " + result1.getAddress().getCity());
			
			// Show customer data from email - OneToOne bidirectional
			System.out.println("This email: " + result2.getEmail() + " belongs to: " + result2.getCustomer().getName());
			
			// Show order data from from customer - OneToMany bidirectional
			System.out.println(result1.getName() + " has an order with this date: " + result1.getOrders().get(0).getDate());			
			
			// Transaction ends
			mySession.getTransaction().commit();
			System.out.println("Customer read from database");
						
		}
		catch(Exception e) {
			e.printStackTrace();
			// Rollback if exception occurs
			mySession.getTransaction().rollback();
		}
		finally {
			// Close factory and session
			mySession.close();
			myFactory.close();
		}

	}
	
}

