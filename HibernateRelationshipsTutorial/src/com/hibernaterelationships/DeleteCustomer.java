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

public class DeleteCustomer {

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
			Customer customer = (Customer) query.getSingleResult();
			
			// Delete restrictions generates in many to many mapping between orders and products.
			// If we don't delete it a ConstraintViolationException occurs.
			for(Order order : customer.getOrders()) {
				order.removeAllProductsFromOrder();
			}

			// Delete customer, address, email and order thanks to CascadeType.ALL defined in entity
			mySession.delete(customer);
			
			// Transaction ends
			mySession.getTransaction().commit();
			System.out.println("Customer deleted from database");
						
		}
		catch(Exception e) {
			e.printStackTrace();
			mySession.getTransaction().rollback();
		}
		finally {
			// Close factory and sesión
			mySession.close();
			myFactory.close();
		}

	}
	
}

