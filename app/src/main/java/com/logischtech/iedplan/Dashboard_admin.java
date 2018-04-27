package com.logischtech.iedplan;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.logischtech.iedplan.Helpers.InternalStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import me.relex.circleindicator.CircleIndicator;

public class Dashboard_admin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private static ViewPager mPager;
    private DrawerLayout mDrawable;
    private ImageView icon;
    icon_manager icon_manager;
    TextView usertile;

    private static int currentPage = 0;
    private static final Integer[] XMEN= {R.drawable.banner1, R.drawable.charles,R.drawable.magneto,R.drawable.mystique,R.drawable.wolverine};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);
        RelativeLayout front = (RelativeLayout) findViewById(R.id.front);
        front.bringToFront();
        RelativeLayout actionbar=(RelativeLayout) findViewById(R.id.actionbar);
        actionbar.bringToFront();

        init();
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
        TextView coursettile = (TextView)findViewById(R.id.coursetile);
        coursettile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Dashboard_admin.this, Courses.class);
                startActivity(i);

            }
        });
        usertile = (TextView)findViewById(R.id.user_tile);
        usertile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Dashboard_admin.this, Users.class);
                startActivity(i);

            }
        });

    }

    private void setNavigationViewListner() {
        NavigationView navigationView=(NavigationView)findViewById(R.id.nav_drawer) ;
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void init() {
        for(int i=0;i<XMEN.length;i++)
            XMENArray.add(XMEN[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyAdapter(Dashboard_admin.this,XMENArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.signout:


                try{
                    Object fromStorage=null;
                    fromStorage= InternalStorage.removeObject(Dashboard_admin.this,"Login_token");
                    Intent i = new Intent(Dashboard_admin.this,Login.class);
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
                Intent i=new Intent(Dashboard_admin.this, Users.class);
                startActivity(i);



            case R.id.profile:
                Intent in = new Intent(Dashboard_admin.this,Profile.class);
                startActivity(in);
        }










        return false;
    }
}
