package com.bridgelabz.addressbook;

import org.junit.Assert;
import org.junit.Test;

public class TestAddressBook {

    @Test
    public void givenFileNametoCreate_whenNotExist_shouldCreateFileandReturnTrue() {
        AddressBookManager addressBookManager=new AddressBookManager();
        Assert.assertEquals(true,addressBookManager.createFile("MyAddress.json"));
    }

    @Test
    public void givenFileNametoCreate_whenExists_shouldNotCreateFileandReturnFalse() {
        AddressBookManager addressBookManager=new AddressBookManager();
        Assert.assertEquals(false,addressBookManager.createFile("MyAddress.json"));
    }

    @Test
    public void givenFile_whenAddPersonDetails_shouldReturnPersonDetails() {
        AddressBookManager addressBookManager = new AddressBookManager();
        Assert.assertEquals("8660424568",addressBookManager.addPersonDetails("Shaik","Mohammed","VijayNagar","Bangalore","Karnataka","124536","8660424568").getPhoneNumber());
    }
}
