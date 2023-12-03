package com.example.PupId;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }
    private String name;
    private int imageResourceId;
    private String info;

    public MainActivity3(String name, int imageResourceId, String info) {
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getInfo() {
        return info;
    }
}