package com.example.a2;

import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class LightActivity extends AppCompatActivity implements SensorEventListener {


    private SensorManager sm;
    private Sensor s2;
    private List<Sensor> l;
    float senVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);




        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        l = sm.getSensorList(Sensor.TYPE_ALL);

        s2 = sm.getDefaultSensor(Sensor.TYPE_LIGHT);

        sm.registerListener(this, s2, 1000000);


        if(sm.getDefaultSensor(Sensor.TYPE_LIGHT) == null){
            //Display sensor not available message. 
            TextView textView = (TextView) findViewById(R.id.lightText);
            textView.setText("Wrong" );
        }



        CustomViewLight m = findViewById(R.id.customViewLight);



        m.invalidate();

    }



    public void back2 (View view){
        this.finish();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        Sensor sensor = event.sensor;

        if (sensor.getType() == Sensor.TYPE_LIGHT) {
            float x = event.values[0];


            CustomViewLight m = findViewById(R.id.customViewLight);

            m.addPoint(x);
            m.addPoint1(x);
            m.addPoint2(x);

            m.invalidate();


            ImageView img = (ImageView) findViewById(R.id.imageView2);

            if (x<100){

                img.setBackgroundResource(R.drawable.light);

            }

            else{
                //ImageView img = (ImageView) findViewById(R.id.imageView2);
                img.setBackgroundResource(R.drawable.light_bright);
                ((AnimationDrawable)img.getBackground()).start();
            }




        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
