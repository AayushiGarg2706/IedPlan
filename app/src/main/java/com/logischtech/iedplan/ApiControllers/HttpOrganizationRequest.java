package com.logischtech.iedplan.ApiControllers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;

import com.logischtech.iedplan.Dashboard;
import com.logischtech.iedplan.Dashboard_admin;
import com.logischtech.iedplan.Helpers.InternalStorage;
import com.logischtech.iedplan.Models.Login_Token;
import com.logischtech.iedplan.Models.Organization;
import com.logischtech.iedplan.Models.User;
import com.logischtech.iedplan.R;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aayushi.Garg on 20-06-2017.
 */

public class HttpOrganizationRequest extends AsyncTask<String,String,Organization> {
    private ProgressDialog progress = null;
    private Context ApplicationContext = null;
    private Activity currentScreen = null;
    private Intent intent=null;

    public HttpOrganizationRequest(Activity screen, Context appContext)
    {
        progress = new ProgressDialog(screen);
        ApplicationContext = appContext;
        currentScreen = screen;
    }

    @Override
    protected Organization doInBackground(String... params) {
     // String url= ((R.string.apiIP)+"/api/Organization/GetCurrentOrganization").toString();
          String url= ApplicationContext.getString(R.string.apiIP)+"/api/Organization/GetCurrentOrganization";
        Login_Token login_token=new Login_Token();

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

            ResponseEntity<Organization> response = restTemplate.exchange( url, HttpMethod.GET, entity, Organization.class);

            Organization repsoneEntity = response.getBody();
            InternalStorage.writeObject(currentScreen, "Organization", repsoneEntity);

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
    protected void onPostExecute(Organization  organization) {
        super.onPostExecute(organization);
        //   if (    true    )
        if (progress.isShowing()) {
            progress.dismiss();

        }
      // new HttpUserRequest(currentScreen,ApplicationContext).execute();

        Intent i=new Intent(currentScreen,Dashboard_admin.class);
        currentScreen.startActivity(i);





    }

}
