package com.logischtech.iedplan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.logischtech.iedplan.Helpers.InternalStorage;
import com.logischtech.iedplan.Models.Login_Token;
import com.logischtech.iedplan.Models.User;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Object fromStorage=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            fromStorage= InternalStorage.readObject(this, "Login_token");
            Login_Token token=(Login_Token) fromStorage;



        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(fromStorage==null){
            Intent mainIntent = new Intent(getApplicationContext(), Choose.class);
           startActivity(mainIntent);

        }
        else{
           Intent mainIntent = new Intent(getApplicationContext(), Dashboard_admin.class);
            startActivity(mainIntent);


        }

    }
}
