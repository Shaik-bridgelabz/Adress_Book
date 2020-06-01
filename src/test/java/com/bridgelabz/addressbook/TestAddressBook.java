package com.bridgelabz.addressbook;

import org.junit.Assert;
import org.junit.Test;

public class TestAddressBook {

    AddressBook addressBook=new AddressBook();
    @Test
    public void givenFileNametoCreate_whenNotExist_shouldCreateFileandReturnTrue() {
        try {
            String fileName="MyNewAddressBook.json";
            boolean createFile=addressBook.createNewFile(fileName);
            Assert.assertEquals(true,createFile);
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenFileNametoCreate_whenExists_shouldNotCreateFileandReturnFalse() {
        try {
            Assert.assertEquals(false, addressBook.createNewFile("MyNewAddressBook.json"));
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenFile_whenAddPersonDetails_shouldReturnPersonDetails() {
        AddressBookManager addressBookManager = new AddressBookManager();
        PersonDetails personDetails=new PersonDetails("Shaik", "Mohammed", "VijayNagar", "Bangalore", "Karnataka", "124536", "8660424568");
        Assert.assertEquals("8660424568", addressBookManager.addPersonDetails(personDetails).iterator().next().getPhoneNumber());
    }

    @Test
    public void givenFileName_whenDeletedPersonDetails_shouldDeletePersonandReturnTrue() {
        try {
            AddressBookManager addressBookManager = new AddressBookManager();
            Assert.assertEquals(true,addressBookManager.deletePersonDetails("MyNewAddressBook.json","8800223344"));
        } catch (AddressBookException e) {
            e.printStackTrace();
        }
    }
}