package com.bridgelabz.addressbook;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AddressBookManager extends AddressBook implements IAddressBookManager {

    AddressBook addressBook =new AddressBook();

    @Override
    public boolean createFile(String fileName) {
       return addressBook.createNewFile(fileName);
    }

    @Override
    public ArrayList<PersonDetails> addPersonDetails(PersonDetails personDetails) {
        return addressBook.addPersonDetails(personDetails);
    }

    @Override
    public boolean save(String fileName, ArrayList<PersonDetails> personDetails) {
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
    public ArrayList<PersonDetails> readPersonDetails(String fileName) {
        try {
        if (fileName.length()==0)
            throw new AddressBookException("File Name Cannot be empty", AddressBookException.ExceptionType.ENTERED_EMPTY);
        File file = new File("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
        if (file.exists()) {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader(file));
            PersonDetails[] personDetails = gson.fromJson(br, PersonDetails[].class);
            for (int i = 0; i < personDetails.length; i++) {
                personDetailsList.add(personDetails[i]);
            }
            return personDetailsList;
        }
            throw new AddressBookException("No Such File Found in Path", AddressBookException.ExceptionType.NO_FILE_FOUND);
        } catch (NullPointerException | FileNotFoundException e) {
            throw new AddressBookException("File Name Cannot be Null", AddressBookException.ExceptionType.ENTERED_NULL);
        }
    }

    @Override
    public boolean deletePersonDetails(String fileName, String phoneNumber) {
       return addressBook.deletePersonDetails(fileName,phoneNumber);
    }

    @Override
    public boolean editPersonDetails(String phoneNumber, String fileName, PersonDetails personDetails) {
        List<PersonDetails> personList = readPersonDetails(fileName);
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
        return addressBook.checksize(list);
    }

    @Override
    public ArrayList getFieldWiseSortedData(SortByField.Parameter parameter,String fileName) {
        List<PersonDetails> personList = readPersonDetails(fileName);
        Comparator<PersonDetails> personDetailsComparator;
        if(personList ==null || personList.size()==0){
            throw new AddressBookException("No Data Found",AddressBookException.ExceptionType.NO_FILE_FOUND);
        }
        personDetailsComparator = SortByField.getParameter(parameter);
        ArrayList sortedData= personDetailsList.stream().
                sorted(personDetailsComparator).collect(Collectors.toCollection(ArrayList::new));
        this.save(fileName,sortedData);
        return sortedData;
    }

    @Override
    public void printPersonDetails(String fileName) {
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
                    personDetailsList.add(personDetails[i]);
                }
            }
        } catch (NullPointerException e) {
            throw new AddressBookException("File Name Cannot be Null", AddressBookException.ExceptionType.ENTERED_NULL);
        } catch (FileNotFoundException e) {
            throw new AddressBookException("No Such File Found in Path", AddressBookException.ExceptionType.NO_FILE_FOUND);
        }
    }

    @Override
    public boolean saveAs(String fileName) {
        try {
            if (fileName.length() == 0)
                throw new AddressBookException("File Name Cannot be empty", AddressBookException.ExceptionType.ENTERED_EMPTY);
            File file = new File("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
            if (file.exists()) {
                System.out.println("Please give other file Name");
                return false;
            }
            this.createFile(fileName);
            ArrayList<PersonDetails> personDetails = this.readPersonDetails("MyAddressBook.json");
            FileWriter writer = new FileWriter("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
            writer.write(String.valueOf(personDetails));
            writer.close();
            return true;
        } catch (IOException e) {
            throw new AddressBookException("Cannot Save in the File", AddressBookException.ExceptionType.NO_FILE_FOUND);
        } catch (NullPointerException e) {
            throw new AddressBookException("File Name Cannot be Null", AddressBookException.ExceptionType.ENTERED_NULL);
        }
    }

    public boolean saveAsCSVFile(String fileName) {
        try {
            if (fileName.length() == 0)
                throw new AddressBookException("File Name Cannot be empty", AddressBookException.ExceptionType.ENTERED_EMPTY);
            File file = new File("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
            if (file.exists()) {
                System.out.println("Please give other file Name");
                return false;
            }
            this.createFile(fileName);
            List<PersonDetails> personDetails = this.readPersonDetails("MyAddressBook.json");
            String FILE_HEADER="firstName,lastName,address,city,state,zip,phoneNumber";
            FileWriter writer=new FileWriter("./src/main/java/com/bridgelabz/addressbook/json/" + fileName);
            writer.append(FILE_HEADER.toString());
            writer.append("\n");
            for (PersonDetails person : personDetails) {
                writer.append(String.valueOf(person.getFirstName()));
                writer.append(",");
                writer.append(String.valueOf(person.getLastName()));
                writer.append(",");
                writer.append(String.valueOf(person.getAddress()));
                writer.append(",");
                writer.append(String.valueOf(person.getCity()));
                writer.append(",");
                writer.append(String.valueOf(person.getState()));
                writer.append(",");
                writer.append(String.valueOf(person.getZip()));
                writer.append(",");
                writer.append(String.valueOf(person.getPhoneNumber()));
                writer.append(",");
                writer.append("\n");
            }
            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            throw new AddressBookException("Cannot Save in the File", AddressBookException.ExceptionType.NO_FILE_FOUND);
        } catch (NullPointerException e) {
            throw new AddressBookException("File Name Cannot be Null", AddressBookException.ExceptionType.ENTERED_NULL);
        }
    }
}