package com.example.a2;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    private SensorManager sm;
    private Sensor s1;
    private Sensor s2;
    private List<Sensor> l;
    float rangeA;
    float resolutionA;
    float delayA;
    float rangeL;
    float resolutionL;
    float delayL;

    float senVal;
    boolean inval;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        l = sm.getSensorList(Sensor.TYPE_ALL);

        s1 = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        s2 = sm.getDefaultSensor(Sensor.TYPE_LIGHT);

        rangeA = s1.getMaximumRange();

        resolutionA = s1.getResolution();

        delayA = s1.getMinDelay();

        rangeL = s2.getMaximumRange();

        resolutionL = s2.getResolution();

        delayL = s2.getMinDelay();

        sm.registerListener(this, s1, 1000000);

        sm.registerListener(this, s2, 1000000);

        if(sm.getDefaultSensor(Sensor.TYPE_LIGHT) == null){
            //Display sensor not available message. 
            TextView textView = (TextView) findViewById(R.id.lightText);
            textView.setText("Wrong" );
        }


        if(sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) == null){
            //Display sensor not available message. 
            TextView textView = (TextView) findViewById(R.id.accelerometerText);
            textView.setText("Wrong" );
        }




        TextView textView1 = (TextView) findViewById(R.id.accelerometerText);
        textView1.setText("Status: Accelerometer \nis Present \nInfo:" +
                "\nMax range: " + rangeA + " \nResolution: " + resolutionA + " \nMin delay: "+ delayA);


        TextView textView2 = (TextView) findViewById(R.id.lightText);
        textView2.setText("Status: Light is Present \nInfo:" +
                "\nMax range: " + rangeL + " \nResolution: " + resolutionL + " \nMin delay: "+ delayL);




    }



    public void foo(View view) {


        Intent x = new Intent(this, AccActivity.class);


        startActivity(x);


    }


    public void foo1(View view) {


        Intent x = new Intent(this, LightActivity.class);


        startActivity(x);


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










        }else if (sensor.getType() == Sensor.TYPE_LIGHT) {

            float l = event.values[0];
            //Log.v("MYTAG","Light= " + l);




        }






    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }



    @Override
    protected  void onPause(){
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    protected  void onResume(){
        super.onResume();
        sm.registerListener(this, s1, 1000000);
        sm.registerListener(this, s2, 1000000);
    }





}
