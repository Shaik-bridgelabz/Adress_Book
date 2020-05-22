package com.bridgelabz.addressbook;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AddressBookManager {

    ArrayList<PersonDetails> personInfo = new ArrayList<PersonDetails>();

    public boolean createFile(String fileName) {
        File files = new File("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
        boolean isFileExist = files.exists();
        if (isFileExist) {
            return false;
        }
        try {
            files.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
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

    public boolean save(String fileName) throws IOException {
        File file = new File("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
        if (file.exists()) {
            Gson gson = new Gson();
            String json = gson.toJson(personInfo);
            FileWriter writer = new FileWriter("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
            writer.write(json);
            writer.close();
            return true;
        }
        return false;
    }

}