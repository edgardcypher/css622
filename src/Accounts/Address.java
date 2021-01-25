package Accounts;

public class Address {
 private String city;
 private String street;
 private String zipCode;
 private String country;
 private int streetNumber;
public Address(String city, String street, String zipCode, String country, int streetNumber) {
	super();
	this.city = city;
	this.street = street;
	this.zipCode = zipCode;
	this.country = country;
	this.streetNumber = streetNumber;
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
 
 
}
