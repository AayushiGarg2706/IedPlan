package com.logischtech.iedplan.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Aayushi.Garg on 17-11-2017.
 */

public class DisplayTypes  implements Serializable{

    @SerializedName("Display")
    public String Display;
    public String getDisplay(){
        return this.Display;
    }

    @SerializedName("Value")
    public Integer Value ;
    public Integer getValue(){
        return this.Value;
    }




}
