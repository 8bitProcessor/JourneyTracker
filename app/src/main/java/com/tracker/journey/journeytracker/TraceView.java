package com.tracker.journey.journeytracker;


import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class TraceView extends View {



    public TraceView(Context context, AttributeSet as){
        super(context, as);
        init();
    }
    private void init(){

    }
    public void setTrace(){

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

    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int size = width > height ? height : width;
        setMeasuredDimension(size, size);
    }



}
