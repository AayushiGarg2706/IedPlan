package com.logischtech.iedplan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.logischtech.iedplan.Helpers.InternalStorage;
import com.logischtech.iedplan.Models.User;

import java.io.IOException;

public class Dashboard extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener
{
   private DrawerLayout mDrawable;
    private ImageView icon;


icon_manager icon_manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        RelativeLayout front = (RelativeLayout) findViewById(R.id.front);
        front.bringToFront();
        RelativeLayout actionbar=(RelativeLayout) findViewById(R.id.actionbar);
        actionbar.bringToFront();

        icon_manager =new icon_manager();
        icon=(ImageView)findViewById(R.id.icon_make);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawable=(DrawerLayout) findViewById(R.id.dashboard);
                mDrawable.openDrawer(GravityCompat.START);

            }
        });
        mDrawable=(DrawerLayout) findViewById(R.id.dashboard);

        setNavigationViewListner();





    }

    private void setNavigationViewListner() {
        NavigationView navigationView=(NavigationView)findViewById(R.id.nav_drawer) ;
         navigationView.setNavigationItemSelectedListener(this);

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.signout:


                try{
                    Object fromStorage=null;
                    fromStorage=InternalStorage.removeObject(Dashboard.this,"Login_token");
                     Intent i = new Intent(Dashboard.this,Login.class);
                     startActivity(i);
                    finish();
                    break;





                }
                catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            case R.id.user:
                 Intent i=new Intent(Dashboard.this, Users.class);
                startActivity(i);
            }









            return false;
    }
}