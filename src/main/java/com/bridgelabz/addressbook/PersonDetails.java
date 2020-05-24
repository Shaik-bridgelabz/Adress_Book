package com.bridgelabz.addressbook;

public class PersonDetails {

    public String FirstName;
    private String LastName;
    private String Address;
    private String City;
    private String State;
    public String Zip;
    private String PhoneNumber ;

    public PersonDetails(PersonDetails personDetails) {
        this.FirstName=personDetails.FirstName;
        this.Zip=personDetails.Zip;
    }
    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return City;
    }

    public String getState() {
        return State;
    }

    public String  getZip() {
        return Zip;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setState(String state) {
        State = state;
    }

    public void setZip(String zip) {
        Zip = zip;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public PersonDetails() {
    }

    public PersonDetails(String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber) {
        FirstName = firstName;
        LastName = lastName;
        Address = address;
        City = city;
        State = state;
        Zip = zip;
        PhoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "PersonDetails{" +
                "FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Address='" + Address + '\'' +
                ", City='" + City + '\'' +
                ", State='" + State + '\'' +
                ", Zip=" + Zip +
                ", PhoneNumber=" + PhoneNumber +
                '}';
    }
}