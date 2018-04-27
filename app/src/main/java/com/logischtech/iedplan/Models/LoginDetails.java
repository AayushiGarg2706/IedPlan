package com.logischtech.iedplan.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Aayushi.Garg on 12-06-2017.
 */

public class   LoginDetails extends Entity implements Serializable {
    @SerializedName("Email")
    public String Email;
    @SerializedName("Password")
    public String Password;
    @SerializedName("REgistrationType")
    public RegistrationType RegistrationType;

    public String getEmail(){
        return this.getEmail();
    }
    public String getPassword(){
        return this.getPassword();
    }
    public RegistrationType getRegistrationType(){
        return this.getRegistrationType();
    }



}
