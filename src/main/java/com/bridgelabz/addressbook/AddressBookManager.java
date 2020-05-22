package com.bridgelabz.addressbook;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class AddressBookManager {

    ArrayList<PersonDetails> personInfo = new ArrayList<PersonDetails>();

    public boolean createFile(String fileName) throws AddressBookException {
        File files = new File("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
        boolean isFileExist = files.exists();
        if (isFileExist) {
            return false;
        }
        try {
            files.createNewFile();
        } catch (IOException e) {
            throw new AddressBookException("Cannot Create File in path", AddressBookException.ExceptionType.CANNOT_CREATE_FILE);
        }
        return true;
    }

    public PersonDetails addPersonDetails(String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber) {
        PersonDetails personDetails = new PersonDetails();
        personDetails.setFirstName(firstName);
        personDetails.setLastName(lastName);
        personDetails.setAddress(address);
        personDetails.setCity(city);
        personDetails.setState(state);
        personDetails.setZip(zip);
        personDetails.setPhoneNumber(phoneNumber);
        personInfo.add(personDetails);
        return personDetails;
    }

    public boolean save(String fileName) throws AddressBookException {
        File file = new File("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
        if (file.exists()) {
            Gson gson = new Gson();
            String json = gson.toJson(personInfo);
            FileWriter writer = null;
            try {
                writer = new FileWriter("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
                writer.write(json);
                writer.close();
                return true;
            } catch (IOException e) {
                throw new AddressBookException("Cannot Save in the File", AddressBookException.ExceptionType.NO_FILE_FOUND);
            }
        }
        return false;
    }

    public boolean readPersonInfo(String fileName) throws AddressBookException {
        File file = new File("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
        if (file.exists()) {
            Gson gson = new Gson();
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                throw new AddressBookException("No Such File Found in Path", AddressBookException.ExceptionType.NO_FILE_FOUND);
            }
            PersonDetails[] personDetails = gson.fromJson(br,PersonDetails[].class);
            System.out.println(Arrays.toString(personDetails));
            return true;
        }
        return false;
    }
}