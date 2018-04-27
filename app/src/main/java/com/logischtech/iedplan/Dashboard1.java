package com.logischtech.iedplan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class Dashboard1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard1);
        RelativeLayout front = (RelativeLayout) findViewById(R.id.front);
        front.bringToFront();
        RelativeLayout actionbar=(RelativeLayout) findViewById(R.id.actionbar);
        actionbar.bringToFront();

    }
}
