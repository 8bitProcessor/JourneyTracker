package com.tracker.journey.journeytracker;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class TraceView extends View {
     private Paint black, red, white, green;
    private Rect square;
    private int height_width;
    private ArrayList<Coordinates> gps;
    public TraceView(Context context, AttributeSet as){
        super(context, as);
        init();
    }
    private void init(){
        black = new Paint(Paint.ANTI_ALIAS_FLAG);
        white = new Paint(Paint.ANTI_ALIAS_FLAG);
        red = new Paint(Paint.ANTI_ALIAS_FLAG);
        green = new Paint(Paint.ANTI_ALIAS_FLAG);
        black.setColor(0xff000000);
        white.setColor(0xffffffff);
        green.setColor(0xff00ff00);
        red.setColor(0xFFFF0000);
    }
    public void setTrace(ArrayList<Coordinates> gps_coor){
        gps = gps_coor;
    }
    public void updateCurrentAverage(){

    }
    public void updateOverallAverage(){

    }
    public void update(){






    }
    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawRect(new Rect(0, 0, height_width, height_width),black);
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int size = width > height ? height : width;
        setMeasuredDimension(size, size);
        height_width=height;
    }



}
