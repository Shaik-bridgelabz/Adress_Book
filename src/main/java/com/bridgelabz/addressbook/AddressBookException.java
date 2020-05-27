package com.bridgelabz.addressbook;

public class AddressBookException extends RuntimeException {

    public enum ExceptionType {
        NO_FILE_FOUND,CANNOT_CREATE_FILE, ENTERED_NULL,ENTERED_EMPTY;
    }
    ExceptionType type;

    public AddressBookException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
