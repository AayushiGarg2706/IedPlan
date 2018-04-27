package com.logischtech.iedplan;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.logischtech.iedplan.Models.Course;

/**
 * Created by Aayushi.Garg on 31-08-2017.
 */

public class CourseAdapter extends BaseAdapter {
    private Activity activity;
    private Course[] data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader;

//    public CourseAdapter(Activity a,Add_course[] courses) {
//        activity = a;
//        data=courses;
//        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        imageLoader=new ImageLoader(activity.getApplicationContext());
//    }

    public CourseAdapter(Activity a, Course[] courses ) {
        activity = a ;
        data = courses ;
    inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    @Override
    public int getCount() {
        return data.length;

    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.course_item_list, null);

        TextView title = (TextView)vi.findViewById(R.id.ctitle); // title
        TextView coursecodelw = (TextView)vi.findViewById(R.id.course); // artist name
        TextView numfaculty = (TextView)vi.findViewById(R.id.numfaculty); // duration
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.clist_image); // thumb image

        Course course = new Course();
        course = data[position];
        title.setText(course.getCourseCode());
        coursecodelw.setText(course.getCourseCodeLower());
      //  numfaculty.setText(course.getFaculties().toString());
        if (course.getFaculties() == null) {
            numfaculty.setText("0 faculties");

        }
        else {
            numfaculty.setText(course.getFaculties().toString());
        }


        return vi;


    }
}
