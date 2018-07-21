package com.liweijian.myimageview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ShapeImageView imageView1;
    private ShapeImageView imageView2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = (ShapeImageView)findViewById(R.id.image1);
        imageView1.setScale(60f);
    }
}
