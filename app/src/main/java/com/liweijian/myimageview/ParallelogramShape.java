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
        this.angle = angle;
        this.scale = 1.0f - angle/90f;

    }

    public void setRoundAngleRadium(float radium){
        this.roundAngleRadium = radium;
        double radians  = Math.toRadians(angle);
        float tan = (float) Math.tan(radians);
        this.move = roundAngleRadium/tan;
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
        path.moveTo(offset,rect.left);
        path.arcTo(rect.left+offset,rect.top,rect.left+offset+roundAngleRadium,rect.top+roundAngleRadium,270f,-angle,false);
        path.lineTo(rect.left+move,rect.bottom-move);
        path.arcTo(rect.left+move,rect.bottom-roundAngleRadium,rect.left+roundAngleRadium+move,rect.bottom,180f,-angle,false);
        path.lineTo(rect.right-offset-move,rect.bottom);
        path.arcTo(rect.right-roundAngleRadium-offset,rect.bottom-roundAngleRadium,rect.right-offset,rect.bottom,90f,-angle,false);
        path.lineTo(rect.right-move,move);
        path.arcTo(rect.right-roundAngleRadium-move,rect.top,rect.right-move,rect.top+roundAngleRadium,0f,-angle,false);

        canvas.drawPath(path,paint);
    }

}
