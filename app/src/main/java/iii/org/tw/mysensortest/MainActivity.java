package iii.org.tw.mysensortest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SensorManager smgr;
    private Sensor sensor;
    private MySensorListener listener;
    private TextView TextX,TextY,TextZ;
    private MyView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smgr = (SensorManager)getSystemService(SENSOR_SERVICE);
        listener = new MySensorListener();
        myView = (MyView)findViewById(R.id.myView);
//        TextX = (TextView)findViewById(R.id.vX);
//        TextY = (TextView)findViewById(R.id.vY);
//        TextZ = (TextView)findViewById(R.id.vZ);

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

    //-----注意 要取得View的寬高需在這裡取得
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //Log.d("Abner","X =" + TextX.getWidth() + "Y = " + TextX.getHeight());
    }

    private class MySensorListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] value = event.values;
            float vx,vy,vz;
            vx = value[0];
            vy = value[1];
            vz = value[2];

            float screenW = myView.getScreenW();
            float screenH = myView.getScreenH();

            if (screenH > 0 && screenW > 0 ) {
                float rateX = screenW/(9.8f*2);
                float rateY = screenH/(9.8f*2) *-1;
                myView.setXY(vx*rateX+screenW/2,vy*rateY+screenH/2);
            }

//            TextX.setText("X = " + vx);
//            TextY.setText("Y = " + vy);
//            TextZ.setText("Z = " + vz);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }
}
