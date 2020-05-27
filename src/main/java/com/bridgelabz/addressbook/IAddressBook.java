package com.bridgelabz.addressbook;

import java.util.ArrayList;

public interface IAddressBook {

    public boolean createFile(String fileName);

    public ArrayList<PersonDetails> addPersonDetails(PersonDetails personDetails);

    public boolean save(String fileName, ArrayList<PersonDetails> personDetails) ;

    public ArrayList<PersonDetails> readPersonInfo(String fileName);

    public boolean deletePersonDetails(String fileName, String firstName);

    public boolean editPersonDetails(String phoneNumber, String fileName, PersonDetails personDetails);

    public ArrayList getFieldWiseSortedData(SortByField.Parameter parameter,String fileName);

    public void printPersonDetails(String fileName);

    public boolean saveAs(String fileName);
}