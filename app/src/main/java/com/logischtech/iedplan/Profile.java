package com.logischtech.iedplan;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.logischtech.iedplan.Helpers.InternalStorage;
import com.logischtech.iedplan.Models.User;
import java.io.IOException;



public class Profile extends AppCompatActivity {
    private static final String TAG = "";
    EditText etfname,etemail,etlname,etmobile;
    icon_manager icon_manager;
    Button btnedit,btnsave;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        etfname = (EditText) findViewById(R.id.etfname);
        etlname = (EditText) findViewById(R.id.etlname);
        etmobile =(EditText) findViewById(R.id.etmobile);
        etemail = (EditText) findViewById(R.id.etemail) ;
        btnedit = (Button)findViewById(R.id.btnedit);

        etfname.setClickable(false);
        etfname.setFocusable(false);
       etlname.setClickable(false);
        etlname.setFocusable(false);
        etmobile.setClickable(false);
        etmobile.setFocusable(false);
        etemail.setClickable(false);
        etemail.setFocusable(false);

     //   icon_manager =new icon_manager();
     //   ((TextView)findViewById(R.id.icon_puser)).setTypeface(icon_manager.get_icons("ionicons.ttf",this));
     //   ((TextView)findViewById(R.id.icon_pemail)).setTypeface(icon_manager.get_icons("ionicons.ttf",this));

        Object fromstorage = null;
        try {
            fromstorage = InternalStorage.readObject(getApplicationContext(),"User");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        User user = (User) fromstorage ;
        etfname.setText(user.getFirstName());
        etemail.setText(user.getEmail());
        etlname.setText(user.getLastName());


        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this,EditProfile.class);
                startActivity(i);
               // overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);




            }
        });
    }

}
