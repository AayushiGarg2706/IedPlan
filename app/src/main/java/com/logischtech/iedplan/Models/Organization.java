package com.logischtech.iedplan.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Aayushi.Garg on 09-06-2017.
 */

public class Organization extends Entity  implements Serializable{
    @SerializedName("Name")

    public String Name;

    public String getName(){
        return this.Name;
    }
    @SerializedName("Type")
    public Integer Type;
    public Integer getType(){
        return this.Type;
    }
    @SerializedName("LicenseEndDate")
    public Date LicenseEndDate;
    public Date getLicenseEndDate(){
        return this.LicenseEndDate;
    }

    @SerializedName("ModulesAccess")
    public Integer ModulesAccess;
    public Integer getModulesAccess(){
        return this.ModulesAccess;
    }
    @SerializedName("ContactEmail")
    public String ContactEmail;
    public String getContactEmail(){
        return this.ContactEmail;
    }
    @SerializedName("CreatedDate")
    public Date CreatedDate;
    public Date getCreatedDate(){
        return this.CreatedDate;
    }

    @SerializedName("UpdatedDate")
    public Date UpdatedDate;
    public Date getUpdatedDate(){
        return this.UpdatedDate;
    }

    @SerializedName("ParentOrganizationId")
    public Long ParentOrganizationId;
    public Long getParentOrganizationId(){
        return this.ParentOrganizationId;
    }

    @SerializedName("IsDeleted")
    public boolean IsDeleted;
    public boolean getIsDeleted(){
        return this.IsDeleted;
    }




    @SerializedName("License")
    public Integer License;
    public Integer getLicense(){
        return this.License;
    }




}
