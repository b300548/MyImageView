package com.liweijian.myimageview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2018/7/21.
 */

@SuppressLint("AppCompatCustomView")
public class ShapeImageView extends ImageView {

    public static final String TAG = "ShapeImageView";


    private ParallelogramShape parallelogramShape = new ParallelogramShape();
    private Shape mShape = parallelogramShape;
    private ShapeDrawable mShapeDrawable;
    private boolean isShape = true;
    private boolean RebuildShape = true;

    private Paint paint;

    public ShapeImageView(Context context) {
        super(context,null);
    }

    public ShapeImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs , 0);
    }

    public ShapeImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ShapeImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

//    //设置shape
//    public void setShape(Shape shape){
//        mShape = shape;
//        isShape = true;
//        RebuildShape = true;
//    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {

        if (isShape){
            //获取ImageView的drawable，当调用过setShape方法时使用下面代码
            Drawable drawable = getDrawable();
            if (drawable == null || mShape == null){
                return;
            }

            Rect bounds = drawable.getBounds();

            if (bounds == null || bounds.width() == 0 || bounds.height() == 0){
                return;
            }

            if (mShapeDrawable == null){
                mShapeDrawable = new ShapeDrawable(parallelogramShape);
            }

            //设置shapeDrawable的bounds
            mShapeDrawable.setBounds(bounds);

            if (RebuildShape){
                RebuildShape = false;
                //获取bitmap
                Bitmap bitmap = drawableToBitmap(drawable);
                if (bitmap == null){
                    return;
                }

                //创建一个bitmapshader，shapedrawable设置这个位图渲染
                BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                mShapeDrawable.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
                mShapeDrawable.getPaint().setStyle(Paint.Style.FILL);
                mShapeDrawable.getPaint().setShader(bitmapShader);
                mShapeDrawable.setShape(mShape);

            }

            //当是平行四边形时设置Shape的所在区域
            if (mShape instanceof ParallelogramShape){
                ((ParallelogramShape)mShape).setRect(bounds);
            }

            int paddingTop = getPaddingTop();
            int paddingLeft = getPaddingLeft();

            //将ShapeDrawable画在canvas
            if (paddingTop == 0 && paddingLeft==0){
                mShapeDrawable.draw(canvas);
            }else {
                int saveCount = canvas.getSaveCount();
                canvas.save();

                canvas.translate(paddingLeft,paddingTop);
                mShapeDrawable.draw(canvas);
                canvas.restoreToCount(saveCount);
            }
        }else {
            super.onDraw(canvas);
        }
    }

   //用此方法创建一个bitmap
    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null || drawable.getBounds() == null){
            return null;
        }

        Bitmap bitmap;
        int width = drawable.getBounds().width();
        int height = drawable.getBounds().height();
        try {
            bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.draw(canvas);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            bitmap = null;
        }
        return bitmap;
    }

    public void setScale(float scale){

        float scalenew = scale/90.0f;
        parallelogramShape.setScale((float)(1.0f - scalenew));
    }

    public void setAngle(float angle){
        parallelogramShape.setAngle(angle);
    }

    public void setRoundAngleRadium(float radium){
        parallelogramShape.setRoundAngleRadium(radium);
    }

    private Bitmap getRoundBitmap(Bitmap bitmap, int roundPx) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;

        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        int x = bitmap.getWidth();

        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;


    }

}
