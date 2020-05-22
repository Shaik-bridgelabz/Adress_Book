package com.bridgelabz.addressbook;

public class AddressBookException extends Exception {

    public enum ExceptionType {
        NO_FILE_FOUND,CANNOT_CREATE_FILE;
    }
    ExceptionType type;

    public AddressBookException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}