package com.bridgelabz.addressbook;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AddressBookManager implements AddressBook  {

    ArrayList<PersonDetails> personInfo = new ArrayList<PersonDetails>();

    @Override
    public boolean createFile(String fileName) throws AddressBookException {
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

    @Override
    public ArrayList<PersonDetails> addPersonDetails(PersonDetails personDetails) {
       personInfo.add(personDetails);
       return personInfo;
    }

    @Override
    public boolean save(String fileName, ArrayList<PersonDetails> personDetails) throws AddressBookException
     {
        try {
            if (fileName.length() == 0)
                throw new AddressBookException("File Name Cannot be empty", AddressBookException.ExceptionType.ENTERED_EMPTY);
            File file = new File("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
            if (file.exists()) {
                Gson gson = new Gson();
                String json = gson.toJson(personDetails);
                FileWriter writer = null;
                writer = new FileWriter("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
                writer.write(json);
                writer.close();
                return true;
            }
            return false;
        } catch (IOException e) {
            throw new AddressBookException("Cannot Save in the File", AddressBookException.ExceptionType.NO_FILE_FOUND);
        } catch (NullPointerException e) {
            throw new AddressBookException("File Name Cannot be Null", AddressBookException.ExceptionType.ENTERED_NULL);
        }
    }

    @Override
    public List<PersonDetails> readPersonInfo(String fileName) throws AddressBookException {
        try {
        if (fileName.length()==0)
            throw new AddressBookException("File Name Cannot be empty", AddressBookException.ExceptionType.ENTERED_EMPTY);
        File file = new File("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
        if (file.exists()) {
            Gson gson = new Gson();
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(file));
            PersonDetails[] personDetails = gson.fromJson(br, PersonDetails[].class);
            for (int i = 0; i < personDetails.length; i++) {
                personInfo.add(personDetails[i]);
            }
        }
            return personInfo;
        } catch (NullPointerException e) {
            throw new AddressBookException("File Name Cannot be Null", AddressBookException.ExceptionType.ENTERED_NULL);
        } catch (FileNotFoundException e) {
            throw new AddressBookException("No Such File Found in Path", AddressBookException.ExceptionType.NO_FILE_FOUND);
        }
    }

    @Override
    public boolean deletePersonDetails(String fileName, String firstName) throws AddressBookException {
        try {
            if (fileName.length()==0)
                throw new AddressBookException("File Name Cannot be empty", AddressBookException.ExceptionType.ENTERED_EMPTY);
            File file = new File("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
            List<PersonDetails> personDetailsList = readPersonInfo(fileName);
            for (PersonDetails personDetails1: personDetailsList) {
                if (personDetails1.getFirstName().equals(firstName)) {
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

    @Override
    public boolean editPersonDetails(String phoneNumber, String fileName, PersonDetails personDetails) throws AddressBookException {
        List<PersonDetails> personList = readPersonInfo(fileName);
        File file = new File("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
        try {
                for (PersonDetails person : personList) {
                    if (person.getPhoneNumber().equals(phoneNumber)) {
                        person.setFirstName(personDetails.getFirstName());
                        person.setLastName(personDetails.getLastName());
                        person.setAddress(personDetails.getAddress());
                        person.setCity(personDetails.getCity());
                        person.setState(personDetails.getState());
                        person.setZip(personDetails.getZip());
                        person.setPhoneNumber(personDetails.getPhoneNumber());
                        Gson gson = new Gson();
                        String json = gson.toJson(personList);
                        FileWriter writer = null;
                        writer = new FileWriter(file);
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

    @Override
    public ArrayList getFieldWiseSortedData(SortByField.Parameter parameter,String fileName) throws AddressBookException {
        List<PersonDetails> personList = readPersonInfo(fileName);
        Comparator<PersonDetails> personDetailsComparator;
        if(personList ==null || personList.size()==0){
            throw new AddressBookException("No Data Found",AddressBookException.ExceptionType.NO_FILE_FOUND);
        }
        personDetailsComparator = SortByField.getParameter(parameter);
        ArrayList sortedData= personList.stream().
                sorted(personDetailsComparator).collect(Collectors.toCollection(ArrayList::new));
        this.save(fileName,sortedData);
        return sortedData;
    }

    @Override
    public void printPersonDetails(String fileName) throws AddressBookException {
        try {
            if (fileName.length()==0)
                throw new AddressBookException("File Name Cannot be empty", AddressBookException.ExceptionType.ENTERED_EMPTY);
            File file = new File("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
            if (file.exists()) {
                Gson gson = new Gson();
                BufferedReader br = null;
                br = new BufferedReader(new FileReader(file));
                PersonDetails[] personDetails = gson.fromJson(br, PersonDetails[].class);
                System.out.println(Arrays.toString(personDetails));
                for (int i = 0; i < personDetails.length; i++) {
                    personInfo.add(personDetails[i]);
                }
            }
        } catch (NullPointerException e) {
            throw new AddressBookException("File Name Cannot be Null", AddressBookException.ExceptionType.ENTERED_NULL);
        } catch (FileNotFoundException e) {
            throw new AddressBookException("No Such File Found in Path", AddressBookException.ExceptionType.NO_FILE_FOUND);
        }
    }
}