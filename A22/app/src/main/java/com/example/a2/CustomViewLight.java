package com.example.a2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class CustomViewLight extends View {




    int viewWidth;
    int viewHeight;

    ArrayList<Float> l = new ArrayList<Float>(8);
    ArrayList<Float> l1 = new ArrayList<Float>(8);
    ArrayList<Float> l2 = new ArrayList<Float>(8);

    public CustomViewLight(Context context) {
        super(context);
    }

    public CustomViewLight(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewLight(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomViewLight(Context context,  AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
    }










    public void clearList(){
        l.clear();
    }

    public void addPoint(Float n){
        l.add(n);

        if(l.size() > 8){
            l.remove(0);
        }



    }

    public float getMean(Float n){
        int index = l.indexOf(n);
        float n1 ;
        float n2 ;
        float n3 ;
        float mean ;
        n1 = l.get(index-1);
        n2 = l.get(index-2);
        n3 = l.get(index-3);

        mean = (n1+n2+n3)/3;

        return mean;

    }


    public void addPoint1( Float n){


        int index = l.indexOf(n);
        float mean ;

        if(index > 3){
            mean = getMean(n);
        }else{
            mean = n;
        }


        l1.add(mean);

        if(l1.size() > 8){
            l1.remove(0);
        }

    }


    public void addPoint2( Float n){


        int index = l.indexOf(n);
        float mean ;
        float temp=0;
        float r;

        if(index > 3){
            mean = getMean(n);

            for (int i = (index-3); i < index; i++)
            {
                float val = l.get(i);

                // Step 2:
                double squrDiffToMean = Math.pow(val - mean, 2);

                // Step 3:
                temp += squrDiffToMean;
            }

            // Step 4:
            double meanOfDiffs = (double) temp / (double) (3);

            r = (float)Math.sqrt(meanOfDiffs);


        }else{
            r = 0;
        }

        l2.add(r);

        if(l2.size() > 8){
            l2.remove(0);
        }

    }


    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
        super.onSizeChanged(xNew, yNew, xOld, yOld);

        viewWidth = xNew;
        viewHeight = yNew;
    }



    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);






        Paint blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        blackPaint.setStrokeWidth(5); // set stroke so you can actually see the lines
        blackPaint.setStyle(Paint.Style.FILL);
        blackPaint.setTextSize(50);
        canvas.drawText("200", 90, 380, blackPaint);
        canvas.drawText("150", 90, 580, blackPaint);
        canvas.drawText("100", 90, 780, blackPaint);
        canvas.drawText("50", 90, 980, blackPaint);
        canvas.drawText("00", 90, 1180, blackPaint);



        canvas.drawText("1", ((viewWidth/8)*1)+120, 1250, blackPaint);
        canvas.drawText("2", ((viewWidth/8)*2)+120, 1250, blackPaint);
        canvas.drawText("3", ((viewWidth/8)*3)+120, 1250, blackPaint);
        canvas.drawText("4", ((viewWidth/8)*4)+120, 1250, blackPaint);
        canvas.drawText("5", ((viewWidth/8)*5)+120, 1250, blackPaint);
        canvas.drawText("6", ((viewWidth/8)*6)+120, 1250, blackPaint);
        canvas.drawText("7", ((viewWidth/8)*7)+90, 1250, blackPaint);



        canvas.drawLine(150, 350, getMeasuredWidth(), 350, blackPaint);
        canvas.drawLine(150, 550, getMeasuredWidth(), 550, blackPaint);
        canvas.drawLine(150, 750, getMeasuredWidth(), 750, blackPaint);
        canvas.drawLine(150, 950, getMeasuredWidth(), 950, blackPaint);
        canvas.drawLine(150, 1150, getMeasuredWidth(), 1150, blackPaint);



        // light values point
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        p.setStrokeWidth(8);
        p.setStyle(Paint.Style.FILL);
        p.setTextSize(50);


        for (int i = 0;i < l.size(); i++){

            float cx = ((viewWidth/8)*i)+150;
            float cy = (1150-(200/50)*l.get(i));

            canvas.drawCircle(cx,cy,30,p);

            if(i>0){
                float ox = ((viewWidth/8)*(i-1))+150;
                float oy = (1150-(200/50)*l.get(i-1));
                canvas.drawLine(cx, cy, ox, oy, p);
            }


        }



        // running mean point
        Paint p1 = new Paint();
        p1.setColor(Color.GREEN);
        p1.setStrokeWidth(8);
        p1.setStyle(Paint.Style.FILL);
        p1.setTextSize(50);


        for (int i = 0;i < l1.size(); i++){

            float cx = ((viewWidth/8)*i)+150;
            float cy = (1150-(200/50)*l1.get(i));

            canvas.drawCircle(cx,cy,30,p1);

            if(i>0){
                float ox = ((viewWidth/8)*(i-1))+150;
                float oy = (1150-(200/50)*l1.get(i-1));
                canvas.drawLine(cx, cy, ox, oy, p1);
            }


        }


        // standard deviation point
        Paint p2 = new Paint();
        p2.setColor(Color.YELLOW);
        p2.setStrokeWidth(8);
        p2.setStyle(Paint.Style.FILL);
        p2.setTextSize(50);


        for (int i = 0;i < l2.size(); i++){

            float cx = ((viewWidth/8)*i)+150;
            float cy = (1150-(200/50)*l2.get(i));

            canvas.drawCircle(cx,cy,30,p2);

            if(i>0){
                float ox = ((viewWidth/8)*(i-1))+150;
                float oy = (1150-(200/50)*l2.get(i-1));
                canvas.drawLine(cx, cy, ox, oy, p2);
            }


        }





        canvas.drawText("Value", 150, 195, blackPaint);
        canvas.drawCircle(100,180,30,p);
        canvas.drawText("Mean", 450, 195, blackPaint);
        canvas.drawCircle(400,180,30,p1);
        canvas.drawText("Std Dev", 750, 195, blackPaint);
        canvas.drawCircle(700,180,30,p2);




    }


}
