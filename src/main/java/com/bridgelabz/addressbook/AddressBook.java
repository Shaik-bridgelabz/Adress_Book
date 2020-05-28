package com.bridgelabz.addressbook;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddressBook {

    ArrayList<PersonDetails> personDetailsList = new ArrayList<PersonDetails>();

    public boolean createNewFile(String fileName) {
        try {
            if (fileName.length()==0)
                throw new AddressBookException("File Name Cannot be empty", AddressBookException.ExceptionType.ENTERED_EMPTY);
            File files = new File("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
            boolean isFileExist = files.exists();
            if (isFileExist) {
                return false;
            }
            files.createNewFile();
            return true;
        } catch (IOException e) {
            throw new AddressBookException("Cannot Create File in path", AddressBookException.ExceptionType.CANNOT_CREATE_FILE);
        } catch (NullPointerException e) {
            throw new AddressBookException("File Name Cannot be Null", AddressBookException.ExceptionType.ENTERED_NULL);
        }
    }

    public ArrayList<PersonDetails> addPersonDetails(PersonDetails personDetails) {
        personDetailsList.add(personDetails);
        return personDetailsList;
    }

    public boolean deletePersonDetails(String fileName, String phoneNumber) {
        try {
            if (fileName.length()==0)
                throw new AddressBookException("File Name Cannot be empty", AddressBookException.ExceptionType.ENTERED_EMPTY);
            File file = new File("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
            List<PersonDetails> personDetailsList = new AddressBookManager().readPersonDetails(fileName);
            for (PersonDetails personDetails1: personDetailsList) {
                if (personDetails1.getPhoneNumber().equals(phoneNumber)) {
                    personDetailsList.remove(personDetails1);
                    Gson gson = new Gson();
                    String json = gson.toJson(personDetailsList);
                    FileWriter writer = new FileWriter(file);
                    writer.write(json);
                    writer.close();
                    return true;
                }
            }
            return false;
        } catch (NullPointerException e) {
            throw new AddressBookException("File Name Cannot be Null", AddressBookException.ExceptionType.ENTERED_NULL);
        } catch (FileNotFoundException e) {
            throw new AddressBookException("No Such File Found in Path", AddressBookException.ExceptionType.NO_FILE_FOUND);
        } catch (IOException e) {
            throw new AddressBookException("File I/O Error", AddressBookException.ExceptionType.NO_FILE_FOUND);
        }
    }

    public boolean checksize(List<PersonDetails> list) {
        if(list.size()!=0)
            return true;
        return false;
    }
}