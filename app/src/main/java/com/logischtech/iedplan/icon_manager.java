package com.logischtech.iedplan;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

/**
 * Created by Aayushi.Garg on 02-06-2017.
 */

public class icon_manager {
    private static Hashtable<String,Typeface> cashed_icons=new Hashtable<>();
    public static Typeface get_icons (String path,Context context) {
        Typeface icons = cashed_icons.get(path);
        if (icons == null) {
            icons = Typeface.createFromAsset(context.getAssets(), path);
            cashed_icons.put(path, icons);
        }
        return icons;
    }
}

