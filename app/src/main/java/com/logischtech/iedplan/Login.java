package com.logischtech.iedplan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.logischtech.iedplan.ApiControllers.HttpLoginRequest;
import com.logischtech.iedplan.Helpers.InternalStorage;
import com.logischtech.iedplan.Models.Login_Token;
import com.logischtech.iedplan.Models.User;


import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
    icon_manager icon_manager;
    ImageView btnlogin;
    public static final String TAG = "NetworkHelper";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        icon_manager =new icon_manager();
        ((EditText)findViewById(R.id.etusername)).setTypeface(icon_manager.get_icons("ionicons.ttf",this));
        ((EditText)findViewById(R.id.etpassword)).setTypeface(icon_manager.get_icons("ionicons.ttf",this));
        TextView signin=(TextView)findViewById(R.id.signup);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this,Register.class);
                startActivity(i);
            }
        });
        btnlogin=(ImageView) findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username=((EditText) findViewById(R.id.etusername)).getText().toString();
                String Password=((EditText)findViewById(R.id.etpassword)).getText().toString();
                String grant_type="password";
                new HttpLoginRequest(Login.this, getApplicationContext()).execute(Username,Password,grant_type);

            }
        });
        ProgressDialog progress = new ProgressDialog(Login.this);
    }
}
