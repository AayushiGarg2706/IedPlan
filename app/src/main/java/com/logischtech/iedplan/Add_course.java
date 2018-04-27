package com.logischtech.iedplan;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.logischtech.iedplan.Helpers.InternalStorage;
import com.logischtech.iedplan.Models.Course;
import com.logischtech.iedplan.Models.Login_Token;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class Add_course extends AppCompatActivity {
    Button btncreate;
    private Context ApplicationContext = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcourse);
        btncreate = (Button) findViewById(R.id.btncreate);
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CourseCode = ((EditText) findViewById(R.id.coursename)).getText().toString();
                String Title = ((EditText) findViewById(R.id.coursetitle)).getText().toString();


                new HttpAddcourseRequest().execute(CourseCode, Title);
            }


        });

    }

    private class HttpAddcourseRequest extends AsyncTask<String, String, Course> {
        private static final String TAG = "NetworkHelper";
        private ProgressDialog progress = new ProgressDialog(Add_course.this);

        @Override
        protected Course doInBackground(String... params) {
            try {

                Course course = new Course();
                course.CourseCode = (String) params[0];
                course.Title = (String) params[1];
                String url = "http://uat.api.iedplan.com/api/Course/AddCourse";
              //  String url = "http://52.187.186.166/iEdPlan.ApiHost/api/Course/AddCourse";
                RestTemplate restTemplate = new RestTemplate();
                HttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
                HttpMessageConverter stringHttpMessageConverternew = new StringHttpMessageConverter();
                JSONObject request = new JSONObject();
                request.put("CourseCode", course.CourseCode);
                request.put("Title", course.Title);
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
                    Course response = restTemplate.postForObject(url, entity, Course.class);

                    InternalStorage.writeObject(Add_course.this, "Course", response);

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
        protected void onPostExecute(Course courses) {
            super.onPostExecute(courses);
            if (progress.isShowing()) {
                progress.dismiss();

            }
            Intent i = new Intent(Add_course.this, Courses.class);
            startActivity(i);


        }
    }
}