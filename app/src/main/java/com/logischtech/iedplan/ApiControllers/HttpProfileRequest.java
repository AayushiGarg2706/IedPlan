package com.logischtech.iedplan.ApiControllers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.content.Intent;

import com.logischtech.iedplan.Helpers.InternalStorage;
import com.logischtech.iedplan.Login;
import com.logischtech.iedplan.Models.Login_Token;
import com.logischtech.iedplan.Models.User;
import com.logischtech.iedplan.R;
import com.logischtech.iedplan.Register;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.apache.http.ParseException;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.io.IOException;

/**
 * Created by Aayushi.Garg on 20-06-2017.
 */

public class HttpProfileRequest extends AsyncTask<String,String ,User> {
    private ProgressDialog progress = null;
    private Context ApplicationContext = null;
    private Activity currentScreen = null;
    private Intent intent=null;

    public HttpProfileRequest(Activity screen, Context appContext)
    {
        progress = new ProgressDialog(screen);
        ApplicationContext = appContext;
        currentScreen = screen;
    }


    @Override
    protected User doInBackground(String... params) {
//http://uat.api.iedplan.com/api/User/Profile
       // String url= (R.string.apiIP+"/api/User/Profile").toString();
        String url= ApplicationContext.getString(R.string.apiIP)+"/api/User/Profile";
        RestTemplate restTemplate=new RestTemplate();
        HttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        HttpMessageConverter stringHttpMessageConverternew = new StringHttpMessageConverter();

        try {
            Object fromStorage = null;

           fromStorage= InternalStorage.readObject(ApplicationContext,"Login_token");
            Login_Token token=(Login_Token) fromStorage;
            String access_token=token.getAccess_token().toString();
            HttpHeaders header = new HttpHeaders();
            header.set("Authorization","Bearer" +" " + access_token );
            HttpEntity entity = new HttpEntity(header);
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(  formHttpMessageConverter);
            restTemplate.getMessageConverters().add(stringHttpMessageConverternew);

            ResponseEntity<User> response = restTemplate.exchange( url, HttpMethod.GET, entity, User.class);
            User repsoneEntity = response.getBody();
            InternalStorage.writeObject(currentScreen, "User", repsoneEntity);

            return repsoneEntity;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    protected void onPreExecute(){
        this.progress.setMessage("Please Wait");
        this.progress.show();

    }
    @Override
    protected void onPostExecute(User  user) {
        super.onPostExecute(user);
        //   if (    true    )
        if (progress.isShowing()) {
            progress.dismiss();

        }
        new HttpOrganizationRequest(currentScreen,ApplicationContext).execute();


        // Intent i = new Intent(currentScreen,Dashboard.class);
        //currentScreen.startActivity(i);


    }

}
