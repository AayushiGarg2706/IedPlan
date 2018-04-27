package com.logischtech.iedplan;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.logischtech.iedplan.Helpers.InternalStorage;
import com.logischtech.iedplan.Models.Login_Token;
import com.logischtech.iedplan.Models.Organization;
import com.logischtech.iedplan.Models.Roles;
import com.logischtech.iedplan.Models.User;

import org.json.JSONException;
import org.json.JSONObject;
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

import static com.logischtech.iedplan.R.id.user;

public class Users extends AppCompatActivity {
    icon_manager icon_manager;
    private int imageResources[] = {
            R.drawable.active,
            R.drawable.inactive,

    };
    private ListView listView;
    private UsersAdapter adapter;
    private List<User> users;
private Activity usersActivity;
    private Context ApplicationContext = null;



    private TextView Username1, Username2, Username3, Username4, Username5, Email1, Email2, Email3, Email4, Role1, Role2, Role3;
    ImageView adduser, Active1, Active2, Active3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_users);
        RelativeLayout front = (RelativeLayout) findViewById(R.id.infront);
        front.bringToFront();

        RelativeLayout actionbar = (RelativeLayout) findViewById(R.id.actionbar);
        actionbar.bringToFront();
        // adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new ArrayList<String>());
        usersActivity = this;
        // Username1=(TextView)findViewById(R.id.username1);
        icon_manager = new icon_manager();
        ((EditText) findViewById(R.id.icon_search)).setTypeface(icon_manager.get_icons("ionicons.ttf", this));

      adduser=(ImageView)findViewById(R.id.adduser) ;
        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Users.this,Add_user.class);
                startActivity(i);
            }
        });

        new HttpUserRequest().execute();


    }
//
    private class HttpUserRequest extends AsyncTask<Object, Object, User[]> {
        private ProgressDialog progress = new ProgressDialog(Users.this);
        private Context ApplicationContext = null;


        @Override
        protected User[] doInBackground(Object... params) {
           // String url = R.string.apiIP+"/api/User/GetAllUsers";
            String url= "http://uat.api.iedplan.com/api/User/GetAllUsers";


            RestTemplate restTemplate = new RestTemplate();
            HttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
            HttpMessageConverter stringHttpMessageConverternew = new StringHttpMessageConverter();
            try {

                Object fromStorage = null;

                fromStorage = InternalStorage.readObject(getApplicationContext(), "Login_token");
                Login_Token token = (Login_Token) fromStorage;
                String access_token = token.getAccess_token().toString();
                HttpHeaders header = new HttpHeaders();
                header.set("Authorization", "Bearer" + " " + access_token);
                HttpEntity entity = new HttpEntity(header);
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                restTemplate.getMessageConverters().add(formHttpMessageConverter);
                restTemplate.getMessageConverters().add(stringHttpMessageConverternew);
                List<User> answerList = new ArrayList<User>();

                ResponseEntity<User[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, User[].class);

                User [] repsoneEntity = response.getBody();
                // InternalStorage.writeObject(Users.this, "User[]", repsoneEntity);


                return repsoneEntity;


            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            catch(Exception ex)
            {
                throw ex;
            }


            return null;
        }

        @Override
        protected void onPreExecute() {
            this.progress.setMessage("Please Wait");
            this.progress.show();

        }

        @Override
        protected void onPostExecute(User[] users) {
            super.onPostExecute(users);
            //   if (    true    )
            if (progress.isShowing()) {
                progress.dismiss();

            }

            listView =(ListView)findViewById(R.id.list);
            Utility.setListViewHeightBasedOnChildren(listView);

            adapter=new UsersAdapter(usersActivity, users);
            listView.setAdapter(adapter);


        }
    }
}