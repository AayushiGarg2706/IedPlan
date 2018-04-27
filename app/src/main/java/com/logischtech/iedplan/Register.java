package com.logischtech.iedplan;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.logischtech.iedplan.ApiControllers.HttpLoginRequest;
import com.logischtech.iedplan.Helpers.InternalStorage;
import com.logischtech.iedplan.Models.LicenseType;
import com.logischtech.iedplan.Models.Login_Token;
import com.logischtech.iedplan.Models.Organization;
import com.logischtech.iedplan.Models.RegistrationDetails;
import com.logischtech.iedplan.Models.RegistrationType;
import com.logischtech.iedplan.Models.User;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.R.layout.simple_spinner_dropdown_item;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    icon_manager icon_manager;
    public static final String TAG = "NetworkHelper";
    private Context ApplicationContext = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Spinner spinner = (Spinner) findViewById(R.id.org_spinner);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.organization_type, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setPrompt("--Select Organisation Type--");
        ImageView btnregister = (ImageView) findViewById(R.id.btnregister);
        TextView signin=(TextView)findViewById(R.id.signin) ;
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Register.this,Login.class);
                startActivity(i);
            }
        });
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FirstName = ((EditText) findViewById(R.id.etname)).getText().toString();
                String Email = ((EditText) findViewById(R.id.etemailreg)).getText().toString();
                String Password = ((EditText) findViewById(R.id.etpasswordreg)).getText().toString();
                String Name = ((EditText) findViewById(R.id.etorg)).getText().toString();
                 String selectorg=((Spinner) findViewById(R.id.org_spinner)).getSelectedItem().toString();
                RegistrationType RegistrationType = com.logischtech.iedplan.Models.RegistrationType.CustomLogin;
                String LastName = "";
                Integer Type = 5;
              //  Date LicenseEndDate = new Date();

              if (FirstName == null || FirstName.isEmpty() || Email == null || Email.isEmpty() || Password == null || Password.isEmpty() || Name == null || Name.isEmpty() || selectorg == null || selectorg.isEmpty())
              {
                  new AlertDialog.Builder(Register.this)

                          .setTitle("Error")
                          .setMessage("All fields are mandatory")
                          .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                              public void onClick(DialogInterface dialog, int which) {
                                  // continue with delete
                              }
                          })
                          .setIcon(android.R.drawable.ic_dialog_alert)
                          .show();
              }
                else{
                 // new HttpRegisterPost().execute(FirstName, Email, LastName, Password, Name, LicenseEndDate, License, RegistrationType);
                  new HttpRegisterPost().execute(FirstName, Email, LastName, Password, Name, Type);


              }


            }
        });


        icon_manager = new icon_manager();
        ((EditText) findViewById(R.id.etname)).setTypeface(icon_manager.get_icons("ionicons.ttf", this));
        ((EditText) findViewById(R.id.etemailreg)).setTypeface(icon_manager.get_icons("ionicons.ttf", this));
        ((EditText) findViewById(R.id.etpasswordreg)).setTypeface(icon_manager.get_icons("ionicons.ttf", this));
        ((EditText) findViewById(R.id.etorg)).setTypeface(icon_manager.get_icons("ionicons.ttf", this));

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class HttpRegisterPost extends AsyncTask<Object, Object, RegistrationDetails> {
        private ProgressDialog progress = new ProgressDialog(Register.this);

        @Override
        protected RegistrationDetails doInBackground(Object... params) {

            try {
                RegistrationDetails details = new RegistrationDetails();
                details.FirstName = (String) params[0];
                details.Email = (String) params[1];
                details.LastName = (String) params[2];
                details.Password = (String) params[3];


                details.RegistrationType = RegistrationType.CustomLogin;
                Organization org = new Organization();
                org.Name = (String) params[4];
                // org.LicenseEndDate = (Date) params[5];
                org.Type =  5;


//192.168.0.119/

                //String url="http://52.187.19.230/iedplan.apihost/api/account/register";
                //  String url =  R.string.apiIP+"/api/account/register";http://52.187.186.166/iEdPlan.ApiHost
                //  String url= ApplicationContext.getString(R.string.apiIP)+"/api/account/register";
                String url = "http://uat.api.iedplan.com/api/account/register";
                // String url = "http://52.187.186.166/iEdPlan.ApiHost/api/account/register";

                RestTemplate restTemplate = new RestTemplate();
                HttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
                HttpMessageConverter stringHttpMessageConverternew = new StringHttpMessageConverter();
                JSONObject request = new JSONObject();
                request.put("FirstName", details.FirstName);
                request.put("Email", details.Email);
                request.put("Lastname", details.LastName);
                request.put("Password", details.Password);
                //  request.put("RegistrationType", details.RegistrationType);
                //  request.put("OrganizationId",details.OrganizationId);
                JSONObject organ = new JSONObject();
                organ.put("Name", org.Name);
                // organ.put("LicenseEndDate", org.LicenseEndDate);
                organ.put("Type",5);
                request.put("Organization", organ);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
                restTemplate.getMessageConverters().add(formHttpMessageConverter);
                restTemplate.getMessageConverters().add(stringHttpMessageConverternew);
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                try {
                    RegistrationDetails response = restTemplate.postForObject(url, entity, RegistrationDetails.class);

                    InternalStorage.writeObject(Register.this, "RegistrationDetails", response);

                    return response;


                }
                catch (Exception ex) {
                    return null;

                }

            } catch (Exception e) {

                this.progress.setMessage("");
                return null;

            }






    }

        @Override
        protected void onPreExecute() {
            this.progress.setMessage("Please Wait");
            this.progress.show();

        }

        @Override
        protected void onPostExecute(RegistrationDetails user) {
            super.onPostExecute(user);
            //   if (    true    )
            if (progress.isShowing()) {
                progress.dismiss();
            }
            String Username = ((EditText) findViewById(R.id.etemailreg)).getText().toString();
            String Password = ((EditText) findViewById(R.id.etpasswordreg)).getText().toString();
            String grant_type = "password";
            if (user == null){

                new AlertDialog.Builder(Register.this)

                        .setTitle("Error")
                        .setMessage("Email already registered")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
            else{
           new HttpLoginRequest(Register.this,getApplicationContext()).execute(Username, Password, grant_type);

            }


            // Intent i = new Intent(Register.this,Login.class);
            // startActivity(i);

        }


    }
    }
