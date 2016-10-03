package iii.org.tw.mysensortest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SensorManager smgr;
    private Sensor sensor;
    private MySensorListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smgr = (SensorManager)getSystemService(SENSOR_SERVICE);
        listener = new MySensorListener();

        //-----得到此手機所有的感應器
        List<Sensor> sensors =  smgr.getSensorList(Sensor.TYPE_ALL);

        for (Sensor s : sensors) {
            String name = s.getName();
            String vendor = s.getVendor();
            Log.d("Abner",name + "\t" + "公司:" + vendor);
        }
        //-----


        //-----三軸加速感應器
        sensor = smgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (sensor == null) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        smgr.registerListener(listener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        smgr.unregisterListener(listener);

    }

    private class MySensorListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event) {

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }
}
