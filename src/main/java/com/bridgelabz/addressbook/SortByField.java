package com.bridgelabz.addressbook;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortByField {

    static Map<Parameter,Comparator> sortParameterComparator=new HashMap<>();

    public enum Parameter {
        FIRST_NAME;
    }

    SortByField(){}

    public static Comparator getParameter(SortByField.Parameter parameter) {

        Comparator<PersonDetails> firstNameComparator=Comparator.comparing(name->name.FirstName);

        sortParameterComparator.put(Parameter.FIRST_NAME,firstNameComparator);

        Comparator<PersonDetails> comparator=sortParameterComparator.get(parameter);
        return comparator;
    }
}