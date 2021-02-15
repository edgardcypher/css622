package Accounts;

import java.io.Serializable;

public class Address implements Serializable {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String city;
 private String street;
 private String state;
 private String zipCode;
 private String country;
 private int streetNumber;
public Address(String city, String street, String zipCode, String country, String state, int streetNumber) {
	super();
	this.city = city;
	this.street = street;
	this.zipCode = zipCode;
	this.country = country;
	this.streetNumber = streetNumber;
	this.state = state;
}
	public Address() {
	// TODO Auto-generated constructor stub
}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getStreetNumber()+" "+this.getStreet()+" "+this.getCity()+" "+this.getState() +" "+this.getZipCode()+" "+this.getCountry();
	}
 
 
}
