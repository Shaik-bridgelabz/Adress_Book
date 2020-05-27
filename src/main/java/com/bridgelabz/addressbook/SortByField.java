package com.bridgelabz.addressbook;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortByField {

    static Map<Parameter,Comparator> sortParameterComparator=new HashMap<>();

    public enum Parameter {
        FIRST_NAME,ZIP;
    }

    SortByField(){}

    public static Comparator getParameter(SortByField.Parameter parameter) {

        Comparator<PersonDetails> firstNameComparator=Comparator.comparing(name->name.getFirstName());
        Comparator<PersonDetails> ZipComparator=Comparator.comparing(name->name.getZip());

        sortParameterComparator.put(Parameter.FIRST_NAME,firstNameComparator);
        sortParameterComparator.put(Parameter.ZIP,ZipComparator);


        Comparator<PersonDetails> comparator=sortParameterComparator.get(parameter);
        return comparator;
    }
}