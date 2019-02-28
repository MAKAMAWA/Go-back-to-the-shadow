package com.example.a2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;


public class AccActivity extends AppCompatActivity  implements SensorEventListener{


    private SensorManager sm;
    private Sensor s1;
    private List<Sensor> l;
    float senVal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc);


        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        l = sm.getSensorList(Sensor.TYPE_ALL);

        s1 = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);





        sm.registerListener(this, s1, 1000000);





        if(sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) == null){
            //Display sensor not available message. 
            TextView textView = (TextView) findViewById(R.id.accelerometerText);
            textView.setText("Wrong" );
        }




        CustomView m = findViewById(R.id.customView);



        m.invalidate();







    }


    public void back(View view){
        this.finish();
    }



    @Override
    public void onSensorChanged(SensorEvent event) {


        Sensor sensor = event.sensor;

        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            double tem = x * x + y * y + z * z;
            senVal = (float)Math.sqrt(tem);



            CustomView m = findViewById(R.id.customView);

            m.addPoint(senVal);
            m.addPoint1(senVal);
            m.addPoint2(senVal);

            m.invalidate();


            ImageView img = (ImageView) findViewById(R.id.imageViewAcc);

            if (senVal<7){

                img.setBackgroundResource(R.drawable.low);

            }
            else if (senVal < 14){
                //ImageView img = (ImageView) findViewById(R.id.imageView2);
                img.setBackgroundResource(R.drawable.low_medium);
                ((AnimationDrawable)img.getBackground()).start();
            }
            else{
                //ImageView img = (ImageView) findViewById(R.id.imageView2);
                img.setBackgroundResource(R.drawable.medium_high);
                ((AnimationDrawable)img.getBackground()).start();
            }



        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
