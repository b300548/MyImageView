package com.liweijian.myimageview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ShapeImageView imageView1;
    private ShapeImageView imageView2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = (ShapeImageView)findViewById(R.id.image1);
        imageView1.setAngle(60f);
        imageView1.setRoundAngleRadium(100);

//        imageView2 = (ShapeImageView)findViewById(R.id.image2);
//        imageView2.setRoundAngleRadium(100);


    }


}
