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

import com.logischtech.iedplan.Helpers.InternalStorage;
import com.logischtech.iedplan.Models.Login_Token;
import com.logischtech.iedplan.Models.User;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class EditProfile extends AppCompatActivity {
    private static final String TAG = "";
    EditText etfirstname,etemailid,etlastname;
    icon_manager icon_manager;
    Button btnsave,btncancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        etfirstname = (EditText) findViewById(R.id.etfirstname);
        etlastname = (EditText) findViewById(R.id.etlastname);
        etemailid = (EditText) findViewById(R.id.etemailid) ;
        btnsave = (Button) findViewById(R.id.btnsave) ;
        btncancel = (Button)findViewById(R.id.btncancel);
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(EditProfile.this,Profile.class);
                startActivity(i);
               // overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FirstName = ((EditText) findViewById(R.id.etfirstname)).getText().toString();
                String LastName = ((EditText) findViewById(R.id.etlastname)).getText().toString();
                String Email = ((EditText) findViewById(R.id.etemailid)).getText().toString();



                new HttpProfileUpdateRequest().execute(FirstName,LastName,Email);

            }
        });
//        icon_manager =new icon_manager();
//        ((TextView)findViewById(R.id.icon_puser)).setTypeface(icon_manager.get_icons("ionicons.ttf",this));
//        ((TextView)findViewById(R.id.icon_pemail)).setTypeface(icon_manager.get_icons("ionicons.ttf",this));
        Object fromstorage = null;
        try {
            fromstorage = InternalStorage.readObject(getApplicationContext(),"User");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        User user = (User) fromstorage ;
        etfirstname.setText(user.getFirstName());
        etlastname.setText(user.getLastName());
        etemailid.setText(user.getEmail());




    }

    private class HttpProfileUpdateRequest extends AsyncTask<String,String,User> {
        private ProgressDialog progress = new ProgressDialog(EditProfile.this);

        @Override
        protected User doInBackground(String... params) {
            try{
                User user = new User();
                user.FirstName = (String) params[0];
                user.LastName = (String) params[1];
                user.Email = (String) params[2];
                String url = "http://52.187.186.166/iEdPlan.ApiHost/api/User/UpdateProfile";
                RestTemplate restTemplate = new RestTemplate();
                HttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
                HttpMessageConverter stringHttpMessageConverternew = new StringHttpMessageConverter();
                JSONObject request = new JSONObject();
                request.put("FirstName", user.FirstName);
                request.put("LastName", user.LastName);
                request.put("Email",user.Email);
                Object fromStorage = null;

                fromStorage = InternalStorage.readObject(getApplicationContext(), "Login_token");
                Login_Token token = (Login_Token) fromStorage;
                String access_token = token.getAccess_token().toString();
                HttpHeaders header = new HttpHeaders();
                header.set("Authorization", "Bearer" + " " + access_token);
                header.setContentType(MediaType.APPLICATION_JSON);

                HttpEntity<String> entity = new HttpEntity<String>(request.toString(), header);
                restTemplate.getMessageConverters().add(formHttpMessageConverter);
                restTemplate.getMessageConverters().add(stringHttpMessageConverternew);
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                try {
                    User response = restTemplate.postForObject(url, entity, User.class);

                    InternalStorage.writeObject(EditProfile.this, "User", response);

                    return response;
                } catch (Exception ex) {

                    Log.d(TAG, "doInBackground: ");

                }

            } catch (Exception e) {
                this.progress.setMessage("");

            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            this.progress.setMessage("Please Wait");
            this.progress.show();

        }
        @Override
        protected  void onPostExecute(User user){
            super.onPostExecute(user);
            if (progress.isShowing()) {
                progress.dismiss();

            }

            Intent i = new Intent (EditProfile.this,Profile.class);
            startActivity(i);
        }
    }
}
