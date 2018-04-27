package com.logischtech.iedplan.Models;

import java.util.Date;

/**
 * Created by Aayushi.Garg on 12-06-2017.
 */

public class Entity {
 public   Integer Id;
    public boolean IsDeleted;
    public Date CreatedDate;
    public Date UpdatedDate;
    public Integer getId(){
        return this.getId();

    }
   public Date getCreatedDate(){
       return this.getCreatedDate();
   }
    public Date getUpdatedDate(){
        return this.getUpdatedDate();
    }
    public boolean getIsDeleted(){
        return this.getIsDeleted();
    }
}
