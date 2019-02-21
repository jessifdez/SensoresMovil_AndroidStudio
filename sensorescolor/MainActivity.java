package com.example.maanas.sensorescolor;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.service.autofill.FieldClassification;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv_acel_x,tv_acel_y,tv_acel_z;
    ConstraintLayout mi_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarVistas();
        SensorManager sm=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor_aceleracion=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SensorEventListener oyente_sensor=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] valores=event.values;
                tv_acel_x.setText(String.valueOf("X: "+valores[0]));
                tv_acel_y.setText(String.valueOf("Y: "+valores[1]));
                tv_acel_z.setText(String.valueOf("Z: "+valores[2]));
                int c=Math.round(valores[0]*23+valores[1]*10000+valores[2]*1500);//Math.round sirve para redondear
                mi_layout.setBackgroundColor(c);
                /*if(valores[1]>7 && valores[1]<9){
                    mi_layout.setBackgroundColor(Color.rgb(255,51,147));
                }
                else
                    if(valores[1]<7){
                        mi_layout.setBackgroundColor(Color.rgb(51,209,255));
                    }
                    else
                        if(valores[1]>9){
                             mi_layout.setBackgroundColor(Color.rgb(51,255,122));
                        }*/
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sm.registerListener(oyente_sensor,sensor_aceleracion,1000 );

    }

    private void cargarVistas(){
        mi_layout=findViewById(R.id.mi_layout);
        tv_acel_x=findViewById(R.id.tv_acel_x);
        tv_acel_y=findViewById(R.id.tv_acel_y);
        tv_acel_z=findViewById(R.id.tv_acel_z);
    }
}
