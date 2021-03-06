package com.bridgelabz.addressbook;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestAddressBookManager {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    public AddressBook addressBook;

    @InjectMocks
    public AddressBookManager addressBookManager;


    @Test
    public void givenFileNametoCreate_whenNotExist_shouldCreateFileandReturnTrue() {
        try {
            String fileName="MyNewAddressBook2.json";
            when(addressBook.createNewFile(fileName)).thenReturn(true);
            Assert.assertEquals(true,addressBookManager.createFile(fileName));
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenFileNametoCreate_whenExists_shouldNotCreateFileandReturnFalse() {
        try {
            String fileName="MyNewAddressBook2.json";
            when(addressBook.createNewFile(fileName)).thenReturn(false);
            Assert.assertEquals(false,addressBookManager.createFile(fileName));
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenFile_whenAddPersonDetails_shouldReturnPersonDetails() {
        PersonDetails personDetails=new PersonDetails("Shaik", "Mohammed", "VijayNagar", "Bangalore", "Karnataka", "124536", "8660424568");
        ArrayList<PersonDetails> personList=new ArrayList<>();
        personList.add(personDetails);
        when(addressBook.addPersonDetails(personDetails)).thenReturn(personList);
        Assert.assertEquals(1, addressBookManager.addPersonDetails(personDetails).size());
    }

    @Test
    public void givenFileName_whenSavePersonDetails_shouldWriteIntoJson() {
        try {
        AddressBookManager addressBookManager = new AddressBookManager();
            PersonDetails personDetails = new PersonDetails("Shaik", "Mohammed", "VijayNagar", "Bangalore", "Karnataka", "124536", "8660424568");
            Assert.assertEquals(true, addressBookManager.save("MyAddressBook.json",addressBookManager.addPersonDetails(personDetails)));
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenFileName_whenReadPersonDetails_shouldReadPersonDetailsFromJson(){
        try {
        AddressBookManager addressBookManager = new AddressBookManager();
        List<PersonDetails> list =addressBookManager.readPersonDetails("MyAddressBook.json");
            Assert.assertEquals(true,addressBookManager.checksize(list));
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenWrongFileName_whenReadPersonDetails_shouldReturnCustomException(){
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.readPersonDetails("WrongAddress.json");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.NO_FILE_FOUND,e.type);
        }
    }

    @Test
    public void givenEmptyFileName_whenReadPersonDetails_shouldReturnCustomException(){
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.readPersonDetails("");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_EMPTY,e.type);
        }
    }

    @Test
    public void givenNullFileName_whenReadPersonDetails_shouldReturnCustomException(){
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.readPersonDetails(null);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_NULL,e.type);
        }
    }

    @Test
    public void givenFileName_whenDeletedPersonDetails_shouldDeletePersonandReturnTrue() {
        try {
            PersonDetails personDetails1=new PersonDetails("Shaik", "Mohammed", "VijayNagar", "Bangalore", "Karnataka", "124536", "8660424568");
            PersonDetails personDetails2 = new PersonDetails("Sharukh", "Khan", "Mumbai", "Mumbai", "Maharashtra", "124536", "8660424568");
            ArrayList<PersonDetails> personList=new ArrayList<>();
            personList.add(personDetails1);
            personList.add(personDetails2);
            when(addressBook.deletePersonDetails("MyAddressBook.json","8660424568")).thenReturn(true);
            Assert.assertEquals(true,addressBookManager.deletePersonDetails("MyAddressBook.json","8660424568"));
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenFileName_whenDeletedNonExistingPersonDetails_shouldReturnFalse() {
        try {
            PersonDetails personDetails1=new PersonDetails("Shaik", "Mohammed", "VijayNagar", "Bangalore", "Karnataka", "124536", "8660424568");
            PersonDetails personDetails2 = new PersonDetails("Sharukh", "Khan", "Mumbai", "Mumbai", "Maharashtra", "124536", "8660424568");
            ArrayList<PersonDetails> personList=new ArrayList<>();
            personList.add(personDetails1);
            personList.add(personDetails2);
            when(addressBook.deletePersonDetails("MyAddressBook.json","8112345682")).thenReturn(false);
            Assert.assertEquals(false,addressBookManager.deletePersonDetails("MyAddressBook.json","8112345682"));
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenFileName_whenEditPersonDetails_shouldReturnUpdatedDetails() {
        try {
        AddressBookManager addressBookManager = new AddressBookManager();
        PersonDetails personDetails = new PersonDetails("Sharukh", "Khan", "Mumbai", "Mumbai", "Maharashtra", "124536", "8660424568");
            Assert.assertEquals(true, addressBookManager.editPersonDetails("8660424568", "MyAddressBook.json", personDetails));
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenFileName_whenSortPersonDetailsByName_shouldReturnSortedNameAtFirst() {
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            ArrayList<PersonDetails> sortedData=addressBookManager.getFieldWiseSortedData(SortByField.Parameter.FIRST_NAME,"MyAddressBook.json");
            Assert.assertEquals("Ashish",sortedData.get(0).getFirstName());
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenFileName_whenSortPersonDetailsByName_shouldReturnSortedNameAtLast() {
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            ArrayList<PersonDetails> sortedData=addressBookManager.getFieldWiseSortedData(SortByField.Parameter.FIRST_NAME,"MyAddressBook.json");
            Assert.assertEquals("Shaik",sortedData.get(sortedData.size()-1).getFirstName());
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenFileName_whenSortPersonDetailsByZip_shouldReturnSortedNameAtFirst() {
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            ArrayList<PersonDetails> sortedData=addressBookManager.getFieldWiseSortedData(SortByField.Parameter.ZIP,"MyAddressBook.json");
            Assert.assertEquals("Micheal",sortedData.get(0).getFirstName());
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenFileName_whenSortPersonDetailsByZip_shouldReturnSortedNameAtLast() {
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            ArrayList<PersonDetails> sortedData=addressBookManager.getFieldWiseSortedData(SortByField.Parameter.ZIP,"MyAddressBook.json");
            Assert.assertEquals("Katrina",sortedData.get(sortedData.size()-1).getFirstName());
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenFileName_whenPrintedPersonDetails_shouldPrintJsonFile() {
        try {
            AddressBookManager addressBookManager=new AddressBookManager();
            addressBookManager.printPersonDetails("MyAddressBook.json");
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenNewFileName_whenSavedAs_shouldSavePersonDetails() {
        try {
            AddressBookManager addressBookManager=new AddressBookManager();
            Assert.assertEquals(true,addressBookManager.saveAs("ShaikAddressBook.txt"));
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenNewFileName_whenSavedAsCSVFile_shouldSavePersonDetailsinCSVFile() {
        try {
            AddressBookManager addressBookManager=new AddressBookManager();
            boolean result=addressBookManager.saveAsCSVFile("ShaikAddressBook.csv");
            Assert.assertEquals(true,result);
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }
}