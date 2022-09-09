package com.hibernaterelationships.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
	
	public Address(String typeOfRoad, String street, int postalCode, String city, String country) {
		this.typeOfRoad = typeOfRoad;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.country = country;
	}

	public Address() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "type_road")
	private String typeOfRoad;
	
	@Column(name = "name_street")
	private String street;
	
	@Column(name = "postal_code")
	private int postalCode;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "country")
	private String country;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeOfRoad() {
		return typeOfRoad;
	}

	public void setTypeOfRoad(String typeOfRoad) {
		this.typeOfRoad = typeOfRoad;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
