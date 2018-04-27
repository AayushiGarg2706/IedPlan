package com.logischtech.iedplan.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Aayushi.Garg on 12-06-2017.
 */

public class User extends Entity implements Serializable {
    @SerializedName("FirstName")

    public String FirstName;
    @SerializedName("LastName")
    public String LastName;
    @SerializedName("Email")
    public String Email;
    @SerializedName("LoginDetailsId")

    public long LoginDetailsId;
    @SerializedName("IsActive")
    public boolean IsActive;
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
    @SerializedName("OrganizationId")
    public long OrganizationId;
    @SerializedName("Language")
    public String Language;
    @SerializedName("Currency")
    public String Currency;
    @SerializedName("Identity")



    public String Identity;
    @SerializedName("IsDefaultRole")
    public Boolean IsDefaultRole;
    public Boolean getIsDefaultRole(){
        return this.IsDefaultRole;
    }

    @SerializedName("IsLicenseExpired")
    public Boolean IsLicenseExpired;
    public Boolean getIsLicenseExpired(){
        return this.IsLicenseExpired;
    }

    @SerializedName("ImageUrl")
    public String ImageUrl;
    public String getImageUrl(){
        return this.ImageUrl;
    }
    @SerializedName("SelectedRoles")
    public List<DisplayTypes> SelectedRoles;
    public List<DisplayTypes> getSelectedRoles(){
        return this.SelectedRoles;
    }

    @SerializedName("Name")
    public  String Name;
    @SerializedName("Organization")
    public Organization Organization;
    @SerializedName("LastLogin")
    public Date LastLogin;
    public Date getLastLogin(){
        return this.LastLogin;
    }
    @SerializedName("EmailLower")
    public String EmailLower;
    public String getEmailLower(){
        return this.EmailLower;
    }
    public Organization getOrganization(){
        return this.Organization;
    }
    public Object getExtendedProperties(){
        return this.ExtendedProperties;
    }
    public String getFirstName(){
        return this.FirstName;
    }
    public String getLastName(){
        return this.LastName;
    }
    public String getEmail() {
        return this.Email;
    }
public long getLoginDetailsId(){
        return this.LoginDetailsId;
    }
    public Object getAssociations(){
        return this.Associations;
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
    public long getOrganizationId(){
        return this.OrganizationId;
    }
    public String getLanguage(){
        return this.Language;
    }
    public String getCurrency(){
        return this.Currency;
    }
    public boolean  getisActive(){
        return  this.IsActive;
    }
    public String getName(){
        return this.Name;
    }

}
