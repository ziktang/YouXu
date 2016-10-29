package com.fl.schedule.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ImageView;

import com.fl.schedule.R;

/**
 * Created by Administrator on 2016/8/3.
 */
public class CircleImageView extends ImageView {
    private int circleColor = Color.WHITE;
    private int circleWidth = 0;
    private Paint circlePaint;
    private int mWidth;
    private int mRadius;

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
        circleWidth = (int) typedArray.getDimension(R.styleable.CircleImageView_StrokeWidth, 0);
        circleColor = typedArray.getColor(R.styleable.CircleImageView_StrokeColor, Color.WHITE);
        typedArray.recycle();
    }

    public void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
        postInvalidate();
    }

    public void setCircleWidth(int circleWidth) {
        this.circleWidth = circleWidth;
        postInvalidate();
    }
    private void init() {
        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(circleWidth);
        circlePaint.setColor(circleColor);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = Math.min(getMeasuredHeight(),getMeasuredWidth());
        setMeasuredDimension(mWidth,mWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }
        if (!(drawable instanceof BitmapDrawable)) {
            return;
        }
        Bitmap b = ((BitmapDrawable) drawable).getBitmap();
        if (null == b) {
            return;
        }

        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);
        int w = mWidth - 2*circleWidth;
        Bitmap roundBitmap = getCroppedBitmap(bitmap, w);
        canvas.drawBitmap(roundBitmap, circleWidth, circleWidth, null);
        init();
        canvas.drawCircle(mWidth / 2, mWidth / 2, w / 2, circlePaint);
    }

    /**
     * 初始Bitmap对象的缩放裁剪过程
     *
     * @param bmp    初始Bitmap对象
     * @param radius 圆形图片直径大小
     * @return 返回一个圆形的缩放裁剪过后的Bitmap对象
     */
    private Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
        Bitmap sbmp;
        //比较初始Bitmap宽高和给定的圆形直径，判断是否需要缩放裁剪Bitmap对象
        if (bmp.getWidth() != radius || bmp.getHeight() != radius)
            sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
        else
            sbmp = bmp;
        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(), sbmp.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getWidth());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(sbmp.getWidth() / 2,
                sbmp.getWidth() / 2, sbmp.getWidth() / 2, paint);
        //核心部分，设置两张图片的相交模式，在这里就是上面绘制的Circle和下面绘制的Bitmap
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);
        return output;
    }

    static public float dipToPx(Context context, float dip) {
        return dip * getScreenDensity(context);
    }

    static public float getScreenDensity(Context context) {
        try {
            DisplayMetrics dm = new DisplayMetrics();
            ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                    .getMetrics(dm);
            return dm.density;
        } catch (Exception e) {
            return DisplayMetrics.DENSITY_DEFAULT;
        }
    }
}
