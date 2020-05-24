package com.bridgelabz.addressbook;

import java.util.List;

public interface AddressBook {

    public boolean createFile(String fileName) throws AddressBookException;

    public PersonDetails addPersonDetails(String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber);

    public boolean save(String fileName) throws AddressBookException;

    public List<PersonDetails> readPersonInfo(String fileName) throws AddressBookException;

    public boolean deletePersonDetails(String fileName, String firstName) throws AddressBookException;
}
