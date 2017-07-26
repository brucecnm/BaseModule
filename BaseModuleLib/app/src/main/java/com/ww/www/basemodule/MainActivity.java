package com.ww.www.basemodule;

import android.os.Bundle;

import com.example.lkslib.base.LksActivity;

public class MainActivity extends LksActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.activity_main);
    }
}
