package com.logischtech.iedplan;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.logischtech.iedplan.Helpers.InternalStorage;
import com.logischtech.iedplan.Models.*;
import com.logischtech.iedplan.Models.Course;

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

public class Courses extends AppCompatActivity {
    private Activity courseactivity;
    private ListView courselist;
    private CourseAdapter adapters;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        RelativeLayout front = (RelativeLayout) findViewById(R.id.infront);
        front.bringToFront();

        RelativeLayout actionbar = (RelativeLayout) findViewById(R.id.actionbar);
        actionbar.bringToFront();
        courseactivity = this;
         ImageView addcourse=(ImageView)findViewById(R.id.addcourse) ;
        addcourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Courses.this,Add_course.class);
                startActivity(i);
            }
        });

        new HttpCourseRequest().execute();
    }

    private class HttpCourseRequest extends AsyncTask<Object,Object,Course[]> {
        private ProgressDialog progress = new ProgressDialog(Courses.this);
        private Context ApplicationContext = null;

        @Override
        protected Course[] doInBackground(Object... params) {
            //'Add_course/GetCourse'
            String url= "http://uat.api.iedplan.com/api/Course/GetCourse";
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
                List<Course> answerList = new ArrayList<Course>();

                ResponseEntity<Course[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Course[].class);

                Course[] repsoneEntity = response.getBody();
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
        protected void onPostExecute(Course[] courses) {
            super.onPostExecute(courses);
            if (progress.isShowing()) {
                progress.dismiss();

            }

            courselist = (ListView)findViewById(R.id.courselist);
            Utility.setListViewHeightBasedOnChildren(courselist);
            adapters=new CourseAdapter(courseactivity, courses);
            courselist.setAdapter(adapters);

          //  adapter=new UsersAdapter(usersActivity, users);
          //  listView.setAdapter(adapter);

            //   }
        }
    }
}
