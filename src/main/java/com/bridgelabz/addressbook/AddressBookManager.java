package com.bridgelabz.addressbook;

import java.io.File;
import java.io.IOException;

public class AddressBookManager {

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

}
