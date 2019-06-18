package com.e.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintStream;

public class Gyroscope extends AppCompatActivity {
    private SensorManager sensorManager;
    private TextView tvGyro;
    private EditText etFirst, etSecond;
    private Button btnAdd, btnSubtract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);
        setTitle("Gyroscope sensor");
        tvGyro = findViewById(R.id.tvGyro);
        etFirst = findViewById(R.id.etFirst);
        etSecond = findViewById(R.id.etSecond);
        btnAdd = findViewById(R.id.btnAdd);
        btnSubtract = findViewById(R.id.btnSubtract);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    add();
                }

        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtract();
            }
        });
        gyroscope();
    }

    private void add() {
        int add = Integer.parseInt(etFirst.getText().toString()) + Integer.parseInt(etSecond.getText().toString());
        Toast.makeText(this, "Sum is " + add, Toast.LENGTH_SHORT).show();
    }
    private void subtract() {
        int subtract = Integer.parseInt(etFirst.getText().toString()) - Integer.parseInt(etSecond.getText().toString());
        Toast.makeText(this, "Difference is " + subtract, Toast.LENGTH_SHORT).show();
    }


    private void gyroscope() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        SensorEventListener gyrolistener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                    if (event.values[1] < 0) {
                        add();
                    } else if (event.values[1] > 0) {
                        subtract();
                    }
                }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(gyrolistener, sensor, SensorManager.SENSOR_DELAY_NORMAL);


        };


}
