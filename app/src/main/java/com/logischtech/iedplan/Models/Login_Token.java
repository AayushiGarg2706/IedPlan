package com.logischtech.iedplan.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Aayushi.Garg on 19-06-2017.
 */

public class Login_Token implements Serializable {
    @SerializedName("access_token")

    public String access_token;
    @SerializedName("token_type")

    public String token_type;
    @SerializedName("expires_in")

    public String expires_in;
    public String getAccess_token(){
        return this.access_token;
    }
    public String getToken_type(){
        return this.token_type;

    }
    public String getExpires_in(){
        return this.expires_in;
    }

}
