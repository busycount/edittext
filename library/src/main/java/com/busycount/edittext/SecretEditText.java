package com.busycount.edittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;


/**
 * SecretEditText
 * <p>
 * 2018/11/30 | Count.C | Created
 */
public class SecretEditText extends View {


    public SecretEditText(Context context) {
        this(context, null);
    }

    public SecretEditText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SecretEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SecretEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }


    private StringBuilder strBuilder;
    private int pwdSize;
    private Paint paintText;
    private Paint paintLine;
    private Paint paintLineSelect;
    private int lineWidth;
    private int lineLength;
    private int lineMargin;

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SecretEditText);
        int colorText = ta.getColor(R.styleable.SecretEditText_textColor, Color.BLACK);
        int colorLine = ta.getColor(R.styleable.SecretEditText_lineColor, Color.BLACK);
        int colorLineSelect = ta.getColor(R.styleable.SecretEditText_lineColorSelect, Color.GREEN);
        lineWidth = ta.getDimensionPixelOffset(R.styleable.SecretEditText_lineWidth, px(1));
        pwdSize = ta.getInteger(R.styleable.SecretEditText_pwdSize, 4);
        ta.recycle();

        paintText = new Paint();
        paintText.setColor(colorText);
        paintText.setAntiAlias(true);
        paintText.setDither(true);
        paintLine = new Paint();
        paintLine.setColor(colorLine);
        paintLine.setStrokeWidth(lineWidth);
        paintLine.setAntiAlias(true);
        paintLine.setDither(true);
        paintLineSelect = new Paint();
        paintLineSelect.setColor(colorLineSelect);
        paintLineSelect.setStrokeWidth(lineWidth);
        paintLineSelect.setAntiAlias(true);
        paintLineSelect.setDither(true);

        strBuilder = new StringBuilder();
    }

    private int px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mWidth = getResources().getDisplayMetrics().widthPixels;
        int mHeight = px(48);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getTargetSize(widthMeasureSpec, mWidth);
        int height = getTargetSize(heightMeasureSpec, mHeight);
        setMeasuredDimension(width, height);
        lineLength = (width - getPaddingLeft() - getPaddingRight()) / (pwdSize + 1);
        lineMargin = lineLength / (pwdSize + 1);
    }

    private int getTargetSize(int measureSpec, int defSize) {
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);
        if (mode == MeasureSpec.AT_MOST) {
            return Math.min(size, defSize);
        } else if (mode == MeasureSpec.EXACTLY) {
            return size;
        } else {
            return defSize;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLine(canvas);
        drawPwd(canvas);
    }

    private void drawLine(Canvas canvas) {
        int startX = getPaddingLeft() + lineMargin;
        int startY = getHeight() - getPaddingBottom() - lineWidth;
        int x;
        for (int i = 0; i < pwdSize; i++) {
            x = startX + i * (lineLength + lineMargin);
            canvas.drawLine(x, startY, x + lineLength, startY, paintLine);
            if (strBuilder.length() == i) {
                canvas.drawLine(x, startY, x + lineLength, startY, paintLineSelect);
            }
        }
    }

    private void drawPwd(Canvas canvas) {
        int cHeight = getHeight() - getPaddingTop() - getPaddingBottom();
        int size = (int) (cHeight * 0.4);
        int startX = getPaddingLeft() + lineMargin + lineLength / 2;
        int startY = getPaddingTop() + cHeight / 2;
        int x;
        for (int i = 0; i < strBuilder.length(); i++) {
            x = startX + i * (lineLength + lineMargin);
            canvas.drawCircle(x, startY, size / 2, paintText);
        }
    }

    public void add(Character c) {
        if (isDone()) {
            return;
        }
        strBuilder.append(c);
        invalidate();
    }

    public void delete() {
        strBuilder.setLength(strBuilder.length() - 1);
        invalidate();
    }

    public void clear() {
        strBuilder.setLength(0);
        invalidate();
    }

    public boolean isDone() {
        return strBuilder.length() >= pwdSize;
    }

    public String getString() {
        return strBuilder.toString();
    }
}
