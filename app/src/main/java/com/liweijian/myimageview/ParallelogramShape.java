package com.liweijian.myimageview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.shapes.Shape;
import android.util.Log;


/**
 * Created by Administrator on 2018/7/21.
 */

class ParallelogramShape extends Shape {

    private Path path;
    private Rect rect;
    private int offset;
    private float scale = -1f;
    private float roundAngleRadium = 0f;
    private float angle = 90f;
    private float move;



    ParallelogramShape(){
        path = new Path();
    }


    public void setRect(Rect rect){
        this.rect = rect;
    }

    public void setOffset(int offset){
        this.offset = offset;
    }

    public  void setScale(float scale){
        this.scale = scale;
    }

    public void setAngle(float angle){
        if(angle > 0f && angle < 90f)
            this.scale = 1.0f - angle/90f;
    }

    public void setRoundAngleRadium(float radium){
            this.roundAngleRadium = radium;
            double radians = Math.toRadians(angle);
            float tan = (float) Math.tan(radians);
            this.move = roundAngleRadium / tan;
    }

    //此方法设置path，path为平行四边形
    @Override
    public void draw(Canvas canvas, Paint paint) {
        if (rect == null || rect.width()==0||rect.height()==0){
            return;
        }

        if (scale > 0.0f && scale < 1.0f){
            offset = (int)(scale * rect.width());
        }

        path.reset();
        path.moveTo(offset+roundAngleRadium-move,rect.top);
        Log.i("move",Float.toString(move));
        path.arcTo(rect.left+offset-move,rect.top,rect.left+offset+roundAngleRadium*2-move,rect.top+roundAngleRadium*2,270f,-angle,false);
        path.lineTo(rect.left+move,rect.bottom-roundAngleRadium);
        path.arcTo(rect.left+move,rect.bottom-roundAngleRadium*2,rect.left+roundAngleRadium*2+move,rect.bottom,180f+angle,-angle*2,false);
        //path.lineTo(rect.right-offset-move,rect.bottom);
        path.lineTo(rect.right-offset-roundAngleRadium,rect.bottom);
        path.arcTo(rect.right-roundAngleRadium*2-offset+move,rect.bottom-roundAngleRadium*2,rect.right-offset+move,rect.bottom,90f,-angle,false);
       // path.lineTo(rect.right-move,rect.top+move);
        path.lineTo(rect.right-move,rect.top+roundAngleRadium);
        path.arcTo(rect.right-roundAngleRadium*2-move,rect.top,rect.right-move,rect.top+roundAngleRadium*2,0f+angle,-angle*2,false);

        canvas.drawPath(path,paint);
    }

}
