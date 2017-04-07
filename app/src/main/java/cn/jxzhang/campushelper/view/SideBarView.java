package cn.jxzhang.campushelper.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import cn.jxzhang.campushelper.R;


/**
 * Created on 2016/1/8 21:52
 * <p>Title:       ${NAME}</p>
 * <p>Description: [Description]</p>
 *
 * @author <a href=zhangjx_dev@163.com>j.x.zhang</a>
 * @version 1.0
 */
public class SideBarView extends View {
    public static String[] b = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};
    private int selectPos = -1;

    private static final int DEFAULT_NORMAL_COLOR = Color.TRANSPARENT;
    private static final int DEFAULT_PRESS_COLOR = Color.parseColor("#1F000000");
    private static final int DEFAULT_TEXT_SIZE = 40;
    private static final int DEFAULT_NOR_TEXT_COLOR = Color.parseColor("#cc181818");
    private static final int DEFAULT_PRESS_TEXT_COLOR = Color.parseColor("#ff000000");

    private int sideBarBgNorColor;
    private int sideBarBgPressColor;
    private int sideBarTextSize;
    private int sideBarNorTextColor;
    private int sideBarPressTextColor;

    private Paint paint;
    private Paint paintSelect;

    private int height;
    private int width;
    private int perHeight;


    public SideBarView(Context context) {
        this(context, null);
    }

    public SideBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public SideBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SideBarView, defStyleAttr, 0);
        sideBarBgNorColor = typedArray.getColor(R.styleable.SideBarView_sidebar_nor_background, DEFAULT_NORMAL_COLOR);
        sideBarBgPressColor = typedArray.getColor(R.styleable.SideBarView_sidebar_press_background, DEFAULT_PRESS_COLOR);
        sideBarTextSize = typedArray.getInt(R.styleable.SideBarView_sidebar_text_size, DEFAULT_TEXT_SIZE);
        sideBarNorTextColor = typedArray.getColor(R.styleable.SideBarView_sidebar_text_color_nor, DEFAULT_NOR_TEXT_COLOR);
        sideBarPressTextColor = typedArray.getColor(R.styleable.SideBarView_sidebar_text_color_press, DEFAULT_PRESS_TEXT_COLOR);
        typedArray.recycle();
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(sideBarNorTextColor);
        paint.setTypeface(Typeface.DEFAULT);
        paint.setTextSize(sideBarTextSize);
        paintSelect = new Paint();
        paintSelect.setAntiAlias(true);
        paintSelect.setTypeface(Typeface.DEFAULT);
        paintSelect.setTextSize(sideBarTextSize);
        paintSelect.setColor(sideBarPressTextColor);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getY();
        int position = (int) (x / perHeight);

        if (position < 0 || position >= b.length) {
            setBackgroundColor(sideBarBgNorColor);
            listener.onLetterReleased(null);
            return false;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setBackgroundColor(sideBarBgPressColor);
                selectPos = position;
                if (listener != null)
                    listener.onLetterSelected(b[selectPos]);
                invalidate();
                break;


            case MotionEvent.ACTION_MOVE:
                if (position != selectPos) {
                    selectPos = position;
                    if (listener != null)
                        listener.onLetterChanged(b[selectPos]);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                setBackgroundColor(sideBarBgNorColor);
                if (listener != null) {
                    listener.onLetterReleased(b[selectPos]);
                }
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        height = getHeight();
        width = getWidth();
        perHeight = height / b.length;
        for (int i = 0; i < b.length; i++) {
            canvas.drawText(b[i], width / 2 - paint.measureText(b[i]) / 2, perHeight * i + perHeight, paint);
            if (selectPos == i) {
                canvas.drawText(b[i], width / 2 - paint.measureText(b[i]) / 2, perHeight * i + perHeight, paintSelect);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = resolveMeasure(widthMeasureSpec, true);
        int height = resolveMeasure(heightMeasureSpec, false);
        setMeasuredDimension(width, height);
    }

    private int resolveMeasure(int measureSpec, boolean isWidth) {
        int result = 0;
        int padding = isWidth ? getPaddingLeft() + getPaddingRight() : getPaddingTop() + getPaddingBottom();
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.EXACTLY:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                float textWidth = paint.measureText(b[0]);
                if (isWidth) {
                    result = getSuggestedMinimumWidth() > textWidth ? getSuggestedMinimumWidth() : (int) textWidth;
                    result += padding;
                    result = Math.min(result, size);
                } else {
                    result = size;
                    result = Math.max(result, size);
                }

                break;
        }
        return result;
    }


    public float dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        return (int) dp2px(25);
    }


    public interface LetterSelectListener {
        void onLetterSelected(String letter);

        void onLetterChanged(String letter);

        void onLetterReleased(String letter);
    }

    private LetterSelectListener listener;

    public void setOnLetterSelectListen(LetterSelectListener listen) {
        this.listener = listen;
    }
}
