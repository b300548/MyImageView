package com.liweijian.myimageview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.shapes.Shape;

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
        path.arcTo(rect.left+offset,rect.top,rect.left+offset+100,rect.top+100,270f,-60f,false);
        path.lineTo(rect.left+50,rect.bottom-50);
        path.arcTo(rect.left+50,rect.bottom-100,rect.left+150,rect.bottom,180f,-60f,false);
        path.lineTo(rect.right-offset-50,rect.bottom);
        path.arcTo(rect.right-100-offset,rect.bottom-100,rect.right-offset,rect.bottom,90f,-60f,false);
        path.lineTo(rect.right-50,50);
        path.arcTo(rect.right-150,rect.top,rect.right-50,rect.top+100,0f,-60f,false);

        canvas.drawPath(path,paint);
    }

}
