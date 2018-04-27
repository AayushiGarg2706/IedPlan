package com.logischtech.iedplan;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.logischtech.iedplan.Models.User;

/**
 * Created by Aayushi.Garg on 29-06-2017.
 */

public class UsersAdapter extends BaseAdapter {

    private Activity activity;
    private User[] data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader;
    private int imageResources[] = {
            R.drawable.active,
            R.drawable.inactive,

    };


    public UsersAdapter(Activity a, User[] users) {
        activity = a;
        data=users;
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
            vi = inflater.inflate(R.layout.list_row, null);

        TextView title = (TextView)vi.findViewById(R.id.title); // title
        TextView artist = (TextView)vi.findViewById(R.id.artist); // artist name
        TextView duration = (TextView)vi.findViewById(R.id.duration); // duration
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
        ImageView status=(ImageView)vi.findViewById(R.id.status) ;



        User user = new User();
        user = data[position];

        // Setting all values in listview
        title.setText(user.getName());
        artist.setText(user.getEmail());
        duration.setText(user.getRole().toString());
         if(user.getisActive()==false){
             status.setImageResource(imageResources[1]);

         }
        else{
             status.setImageResource(imageResources[0]);

         }
        return vi;
    }
}
