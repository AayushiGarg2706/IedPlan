package com.logischtech.iedplan.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Aayushi.Garg on 31-08-2017.
 */
public class UserCourseMap implements Serializable
{
    @SerializedName("FacultyId ")

    public long FacultyId ;

    @SerializedName("Faculty ")

    public User Faculty ;
    @SerializedName("CourseId ")

    public long CourseId ;
    @SerializedName("Add_course ")

    public Course Course ;


    public long getFacultyId(){
        return this.FacultyId;
    }
    public User getFaculty(){
        return this.Faculty;
    }

    public long getCourseId(){
        return this.CourseId;
    }

    public Course getCourse(){
        return this.Course ;
    }
}
