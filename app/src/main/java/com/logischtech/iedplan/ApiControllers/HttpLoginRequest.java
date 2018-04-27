package com.logischtech.iedplan.ApiControllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.logischtech.iedplan.Dashboard;
import com.logischtech.iedplan.Helpers.InternalStorage;
import com.logischtech.iedplan.Login;
import com.logischtech.iedplan.Models.Login_Token;
import com.logischtech.iedplan.Models.User;
import com.logischtech.iedplan.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Aayushi.Garg on 19-06-2017.
 */

public class HttpLoginRequest extends AsyncTask<String,String,Login_Token> {

    private ProgressDialog progress = null;
    private Context ApplicationContext = null;
    private Activity currentScreen = null;

    public HttpLoginRequest(Activity screen, Context appContext)
    {
        progress = new ProgressDialog(screen);
        ApplicationContext = appContext;
        currentScreen = screen;
    }

    //private ProgressDialog

    @Override
    protected Login_Token doInBackground(String... params) {
        try {
            String Username=params[0];
            String Password=params[1];
            String grant_type=params[2];
//http://52.187.186.166/iEdPlan.ApiHost/token
            HttpClient httpClient=new DefaultHttpClient();
         //   String url = ApplicationContext.getString(R.string.apiIP ) +"/token";
            String url = "http://uat.api.iedplan.com/token";

            HttpPost httpPost=new HttpPost( url  );

            HttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
            HttpMessageConverter stringHttpMessageConverternew = new StringHttpMessageConverter();
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("Username",Username));
            nameValuePairs.add(new BasicNameValuePair("Password",Password));
            nameValuePairs.add(new BasicNameValuePair("grant_type",grant_type));
            httpPost.setHeader(HTTP.CONTENT_TYPE,"application/x-www-form-urlencoded;charset=UTF-8");
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            try {
                HttpResponse response=httpClient.execute(httpPost);
                String user=  EntityUtils.toString(response.getEntity()) ;

                JSONObject obj = new JSONObject(user);


               Login_Token token = new Login_Token();
                token.access_token = obj.get("access_token").toString();

                token.token_type = obj.get("token_type").toString();
                token.expires_in = obj.get("expires_in").toString();
                if (token.access_token == null){
                    return null;
                }
                else{
                    InternalStorage.writeObject(ApplicationContext,"Login_token",token);


                    return token;

                }





            }
            catch (Exception e){
              //  Log.d(TAG, "doInBackground: ");
                return null;
            }


        }
        catch (Exception e){
           // Log.d(TAG, "doInBackground: ");
            return null;

        }
      //  return null;

    }
    @Override
    protected void onPreExecute(){
        this.progress.setMessage("Please Wait");
        this.progress.show();

    }
    @Override
    protected void onPostExecute(Login_Token  login_token) {
        super.onPostExecute(login_token);
        //   if (    true    )
        if (progress.isShowing()) {
            progress.dismiss();

        }
        if(login_token==null){
            new AlertDialog.Builder(currentScreen)
                    .setTitle("Error")
                    .setMessage("Invalid Username or Password")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        else{
            new HttpProfileRequest(currentScreen,ApplicationContext).execute();

        }



       // Intent i = new Intent(currentScreen,Dashboard.class);
        //currentScreen.startActivity(i);


    }


}