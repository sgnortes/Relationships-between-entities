package com.hibernaterelationships;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernaterelationships.entity.Address;
import com.hibernaterelationships.entity.Customer;
import com.hibernaterelationships.entity.Email;
import com.hibernaterelationships.entity.Order;
import com.hibernaterelationships.entity.Product;

public class SaveCustomer {

	public static void main(String[] args) {
		// Creation of session
		SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class).addAnnotatedClass(Address.class).addAnnotatedClass(Email.class).addAnnotatedClass(Order.class).addAnnotatedClass(Product.class).buildSessionFactory();
		Session mySession = myFactory.openSession();
					
		try {
			// Creating customer
			Customer  customer = new Customer("Roberto", "García");
			
			// Setting its address and email using class setters (OneToOne relationship)
			Address address = new Address("Avda.", "La libertad", 3690, "San Vicente", "España");
			Email email = new Email("email@email.com");
			customer.setAddress(address);
			customer.setEmail(email);
			email.setCustomer(customer);
			
			// Declaring multiple orders of the customer (OneToMany relationship)
			Order order1 = new Order(Date.valueOf(LocalDate.now()));
			order1.setCustomer(customer);
			Order order2 = new Order(Date.valueOf(LocalDate.now().plusDays(1)));
			order2.setCustomer(customer);
			List<Order> orders = new ArrayList<Order>();
			
			// Declaring multiple products and assigning them to the orders (ManyToMany relationship)
			Product product1 = new Product("Asics Gel Cumulus", 130.00);
			Product product2 = new Product("Asics Gel Trabuco", 120.00);
			List<Product> products = new ArrayList<Product>();
			products.add(product1);
			products.add(product2);
			
			order1.addProduct(product1);
			order1.addProduct(product2);
			
			order2.addProduct(product2);
			
			orders.add(order1);
			orders.add(order2);
			
			// Setting the orders to the customer (OneToMany relationship)
			customer.setOrders(orders);
			
			// Transaction begins
			mySession.beginTransaction();
			
			// Save customer in BBDD
			mySession.persist(customer);
			
			// Transaction ends
			mySession.getTransaction().commit();
			System.out.println("Customer registered in database");
						
		}
		catch(Exception e) {
			e.printStackTrace();
			// Rollback if exception occurs
			mySession.getTransaction().rollback();
		}
		finally {
			// Closing factory and session
			mySession.close();
			myFactory.close();
		}

	}
	
}

