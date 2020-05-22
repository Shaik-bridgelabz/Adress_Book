package com.bridgelabz.addressbook;

import org.junit.Assert;
import org.junit.Test;

public class TestAddressBook {

    @Test
    public void givenFileNametoCreate_whenNotExist_shouldCreateFileandReturnTrue() {
        try {
        AddressBookManager addressBookManager = new AddressBookManager();
            Assert.assertEquals(true, addressBookManager.createFile("MyAddress.json"));
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenFileNametoCreate_whenExists_shouldNotCreateFileandReturnFalse() {
        try {
        AddressBookManager addressBookManager = new AddressBookManager();
            Assert.assertEquals(false, addressBookManager.createFile("MyAddress.json"));
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenFile_whenAddPersonDetails_shouldReturnPersonDetails() {
        AddressBookManager addressBookManager = new AddressBookManager();
        Assert.assertEquals("8660424568", addressBookManager.addPersonDetails("Shaik", "Mohammed", "VijayNagar", "Bangalore", "Karnataka", "124536", "8660424568").getPhoneNumber());
    }

    @Test
    public void givenFileName_whenSavePersonDetails_shouldWriteIntoJson() {
        try {
        AddressBookManager addressBookManager = new AddressBookManager();
        addressBookManager.addPersonDetails("Shaik", "Mohammed", "VijayNagar", "Bangalore", "Karnataka", "124536", "8660424568");
        addressBookManager.addPersonDetails("Salman","Khan","Bandra","Mumbai","Maharashtra","666999","8855225588");
            Assert.assertEquals(true, addressBookManager.save("MyAddress.json"));
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenFileName_whenReadPersonDetails_shouldReadPersonDetailsFromJson(){
        try {
        AddressBookManager addressBookManager = new AddressBookManager();
            Assert.assertEquals(true,addressBookManager.readPersonInfo("MyAddress.json"));
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenWrongFileName_whenReadPersonDetails_shouldReturnCustomException(){
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.readPersonInfo("WrongAddress.json");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.NO_FILE_FOUND,e.type);
        }
    }

    @Test
    public void givenEmptyFileName_whenReadPersonDetails_shouldReturnCustomException(){
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.readPersonInfo("");
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_EMPTY,e.type);
        }
    }

    @Test
    public void givenNullFileName_whenReadPersonDetails_shouldReturnCustomException(){
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            addressBookManager.readPersonInfo(null);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.ExceptionType.ENTERED_NULL,e.type);
        }
    }

    @Test
    public void givenFileName_whenDeletedPersonDetails_shouldDeletePersonandReturnTrue() {
        AddressBookManager addressBookManager = new AddressBookManager();
        try {
            Assert.assertEquals(true,addressBookManager.deletePersonDetails("MyAddress.json","Salman"));
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }
}