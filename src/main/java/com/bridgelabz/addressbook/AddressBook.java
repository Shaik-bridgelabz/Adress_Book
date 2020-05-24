package com.bridgelabz.addressbook;

import java.util.ArrayList;
import java.util.List;

public interface AddressBook {

    public boolean createFile(String fileName) throws AddressBookException;

    public ArrayList<PersonDetails> addPersonDetails(PersonDetails personDetails);

    public boolean save(String fileName, ArrayList<PersonDetails> personDetails) throws AddressBookException;

    public List<PersonDetails> readPersonInfo(String fileName) throws AddressBookException;

    public boolean deletePersonDetails(String fileName, String firstName) throws AddressBookException;

    public boolean editPersonDetails(String phoneNumber, String fileName, PersonDetails personDetails) throws AddressBookException;

    public ArrayList getFieldWiseSortedData(SortByField.Parameter parameter,String fileName) throws AddressBookException;

}