package com.hibernaterelationships.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	
	public Order(Date fecha) {
		this.date = fecha;
		this.products = new ArrayList<Product>();
	}

	public Order() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "date_order")
	private Date date;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToMany(mappedBy = "orders", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	List<Product> products;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date fecha) {
		this.date = fecha;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void addProduct(Product product) {
			this.products.add(product);
	        product.getOrders().add(this);
    }
	
	// We manually delete the association between one unique product and the order
    public void removeProduct(Product product) {
    		this.products.remove(product);
            product.getOrders().remove(this);  
    }
    
    // We manually delete the association between an order and all the products that are associated 
 	// in order to avoid the usage of CascadeType.ALL when defining the relationship in the entity
    public void removeAllProductsFromOrder() {
    	
        for(int i = 0; i < this.products.size(); i++) {
            this.products.get(i).getOrders().remove(this);
        }
        
        this.products = null;
    }

}
