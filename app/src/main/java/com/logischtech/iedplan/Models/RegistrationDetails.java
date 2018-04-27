package com.logischtech.iedplan.Models;

import android.provider.ContactsContract;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Aayushi.Garg on 09-06-2017.
 */

public class RegistrationDetails implements Serializable {
 @SerializedName("Email")
    public String Email;
    @SerializedName("Password")
    public String Password;
    @SerializedName("FirstName")
    public String FirstName;
    @SerializedName("LastName")
    public String LastName;
    @SerializedName("OrganizationId")
    public long OrganizationId;
    @SerializedName("RegistrationType")
    public RegistrationType RegistrationType;
    @SerializedName("Organization")
    public Organization Organization;
    @SerializedName("LastLogin")
    public Date LastLogin;
    @SerializedName("TimeZone")
    public String TimeZone;
    @SerializedName("Role")
    public Roles Role;
    @SerializedName("LoginDetails")
    public LoginDetails LoginDetails;
    @SerializedName("CreatedBy")
    public  long CreatedBy;
    @SerializedName("Associations")
    public Object Associations;
    @SerializedName("ExtendedProperties")
    public Object ExtendedProperties;

    public Object getAssociations(){
        return this.Associations;
    }
    public Object getExtendedProperties(){
        return this.ExtendedProperties;
    }

    public String getTimeZone(){
        return this.TimeZone;
    }
    public  Roles  getRole(){
        return this.Role;
    }
    public LoginDetails getLoginDetails(){
        return this.LoginDetails;
    }
    public long getCreatedBy(){
        return this.CreatedBy;
    }




    public Date getLastLogin(){
        return this.LastLogin;
    }
    @SerializedName("EmailLower")
    public String EmailLower;
    public String getEmailLower(){
        return this.EmailLower;
    }




    public String getEmail(){
        return this.getEmail();
    }
    public String getPassword(){
        return this.getPassword();
    }
    public String getFirstName(){
        return this.getFirstName();
    }
    public String getLastName(){
        return this.getLastName();
    }
     public RegistrationType getRegistrationType(){
        return this.getRegistrationType();
    }
   public Organization getOrganization(){
       return this.getOrganization();
   }



}
