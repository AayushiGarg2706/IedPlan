package com.logischtech.iedplan.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Aayushi.Garg on 12-06-2017.
 */

public enum  Roles implements Serializable {
    Unknown("Unknown", 0),
    OrganizationAdmin("Admin", 1),
    Student("Student",2),
    Faculty("Faculty",3),
    Parent("Parent",4);


    @SerializedName("stringValue")
    private String stringValue;
    private int intValue;
    private Roles(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }

}
