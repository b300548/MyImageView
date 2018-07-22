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
    private ShapeImageView imageView3;
    private ShapeImageView imageView4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //矩形imageview
        imageView1 = (ShapeImageView)findViewById(R.id.image1);

        //添加圆角矩形imageview
        imageView2 = (ShapeImageView)findViewById(R.id.image2);
        imageView2.setRoundAngleRadium(100); //设置圆角半径为100

        //平行四边行imageview
        imageView3 = (ShapeImageView)findViewById(R.id.image3);
        imageView3.setAngle(60);//设置平行四边形底角角度, 范围：0 - 90度

        //圆角平行四边行imageview
        imageView4 = (ShapeImageView)findViewById(R.id.image4);
        imageView4.setAngle(70);
        imageView4.setRoundAngleRadium(30);

    }


}
