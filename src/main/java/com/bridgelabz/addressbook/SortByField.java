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

    private static final Comparator<PersonDetails> firstNameComparator=Comparator.comparing(name->name.getFirstName());
    private static final Comparator<PersonDetails> ZipComparator=Comparator.comparing(name->name.getZip());

    public static Comparator getParameter(SortByField.Parameter parameter) {

        sortParameterComparator.put(Parameter.FIRST_NAME,firstNameComparator);
        sortParameterComparator.put(Parameter.ZIP,ZipComparator);

        Comparator<PersonDetails> comparator=sortParameterComparator.get(parameter);
        return comparator;
    }
}