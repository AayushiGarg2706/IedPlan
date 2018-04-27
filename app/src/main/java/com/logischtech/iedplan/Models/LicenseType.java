package com.logischtech.iedplan.Models;

/**
 * Created by Aayushi.Garg on 09-06-2017.
 */

public enum LicenseType {

//    Unknown("Unknown", 0),
//    SinglePerson("SinglePerson", 1),
//    Group("Group",2),
//    Organization("Organization",3);


       Select("Select",0),
       Individual("Individual",1),
    Group("Group",2),
    School("School",3),
    College("College",4),
    Corporate("Corporate",5);



    private String stringValue;
    private int intValue;
    private LicenseType(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }

}
