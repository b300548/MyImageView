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
        path.lineTo(rect.left,rect.bottom);
        path.lineTo(rect.right-offset,rect.bottom);
        path.lineTo(rect.right,0);
        canvas.drawPath(path,paint);
    }

}
