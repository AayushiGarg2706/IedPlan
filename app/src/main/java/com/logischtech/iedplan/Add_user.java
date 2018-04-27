package com.logischtech.iedplan;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.logischtech.iedplan.Helpers.InternalStorage;
import com.logischtech.iedplan.Models.Course;
import com.logischtech.iedplan.Models.Login_Token;
import com.logischtech.iedplan.Models.Organization;
import com.logischtech.iedplan.Models.Roles;
import com.logischtech.iedplan.Models.User;

import org.apache.http.protocol.HTTP;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class Add_user extends AppCompatActivity {

    private static final String TAG ="Network Helper" ;
    private Context ApplicationContext = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        Spinner spinner=(Spinner)findViewById(R.id.role);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.role, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);


        ImageView backarrow=(ImageView)findViewById(R.id.back_arrow);
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Add_user.this, Users.class);
                startActivity(i);
                finish();
            }
        });
        Button create=(Button)findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FirstName=((EditText)findViewById(R.id.firstname)).getText().toString();
                String LastName=((EditText)findViewById(R.id.edlastname)).getText().toString();
                String Email=((EditText)findViewById(R.id.edemail)).getText().toString();
                String Role=((Spinner)findViewById(R.id.role)).getSelectedItem().toString();
                String Name= FirstName +  " " + LastName;

                new HttpAddUserRequest().execute(FirstName,LastName,Email,Role,Name);

            }
        });
    }

    private class HttpAddUserRequest extends AsyncTask<String,String,User> {
        private ProgressDialog progress = new ProgressDialog(Add_user.this);

        @Override
        protected User doInBackground(String... params) {
            try{
                int values=1;
                User user=new User();
                user.FirstName=(String) params[0];
                user.LastName=(String)params[1];
                user.Email=(String)params[2];
                 user.Role=Roles.values()[values];
                 user.Name=(String)params[4];
                String url= "http://uat.api.iedplan.com/api/User/AddUser";
                RestTemplate restTemplate = new RestTemplate();
                HttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
                HttpMessageConverter stringHttpMessageConverternew = new StringHttpMessageConverter();


                JSONObject json=new JSONObject();
                json.put("FirstName",user.FirstName);
                json.put("LastName",user.LastName);
                json.put("Email",user.Email);
                json.put("Role",2);
                json.put("Name",user.Name);

                Object fromStorage = null;

                fromStorage = InternalStorage.readObject(getApplicationContext(), "Login_token");
                Login_Token token = (Login_Token) fromStorage;
                String access_token = token.getAccess_token().toString();
                HttpHeaders header = new HttpHeaders();
                header.set("Authorization", "Bearer" + " " + access_token);
                header.setContentType(MediaType.APPLICATION_JSON);

                HttpEntity<String> entity = new HttpEntity<String>(json.toString(), header);
                restTemplate.getMessageConverters().add(formHttpMessageConverter);
                restTemplate.getMessageConverters().add(stringHttpMessageConverternew);
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                try {
                    User response = restTemplate.postForObject(url, entity, User.class);
                    InternalStorage.writeObject(Add_user.this, "UserList", response);

                    return response;

                } catch (Exception ex) {

                    Log.d(TAG, "doInBackground: ");

                }




            }
            catch (Exception e){

            }

            return null;
        }
        @Override
        protected void onPreExecute() {
            this.progress.setMessage("Please Wait");
            this.progress.show();

        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            if (progress.isShowing()) {
                progress.dismiss();

            }
            Intent i = new Intent(Add_user.this, Users.class);
            startActivity(i);


        }

    }
}
