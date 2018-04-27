package com.logischtech.iedplan.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Aayushi.Garg on 31-08-2017.
 */

public class Course extends Entity implements Serializable {
    @SerializedName("CourseCode ")

    public String CourseCode ;
    @SerializedName("CourseCodeLower  ")

    public String CourseCodeLower  ;
    @SerializedName("Title   ")

    public String Title   ;

    @SerializedName("OrganizationId    ")

    public long OrganizationId    ;
    @SerializedName("Organization     ")

    public Organization  Organization     ;
    @SerializedName("CreatedById")

    public long  CreatedById  ;
    @SerializedName("CreatedBy ")
    public  User CreatedBy;

    @SerializedName("Faculties")

    public User Faculties;

    @SerializedName("UserCourseMaps ")
    public UserCourseMap UserCourseMaps ;

    public String getCourseCode(){
        return this.CourseCode;
    }
     public String getCourseCodeLower(){
         return this.CourseCodeLower;
     }
    public User getFaculties(){
        return this.Faculties;
    }
public String getTitle(){
    return this.Title ;
}
    public UserCourseMap getUserCourseMaps(){
        return this.UserCourseMaps;
    }

    public long getOrganizationId(){
        return this.OrganizationId ;
    }

 public long getCreatedById(){
     return this.CreatedById;
 }
    public Organization getOrganization(){
        return this.Organization ;
    }
    public User getCreatedBy(){
        return this.CreatedBy;
    }
}
