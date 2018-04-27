package com.logischtech.iedplan.Models;

/**
 * Created by Aayushi.Garg on 09-06-2017.
 */

public   enum  RegistrationType {

    Unknown("Unknown", 0),
    Facebook("Facebook", 1),
    CustomLogin("CustomLogin",2),
    Twitter("Twitter",3);


    private String stringValue;
    private int intValue;
    private RegistrationType(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }

}
