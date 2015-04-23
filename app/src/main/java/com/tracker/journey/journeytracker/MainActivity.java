package com.tracker.journey.journeytracker;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private ArrayList<Coordinates> gps;
    private LocationManager lm;
    private TextView  speed, curr_average, overall_average;
    private Button startTracking;
    public Boolean tracking =false;
    private Chronometer chronometer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer = (Chronometer) findViewById(R.id.time);
        speed = (TextView) findViewById(R.id.speed);
        curr_average = (TextView) findViewById(R.id.current_average);
        overall_average = (TextView) findViewById(R.id.overall_average);
        startTracking = (Button) findViewById(R.id.start_tracking);
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        final LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Coordinates coor = new Coordinates();
                Double speed_metres = (double) location.getSpeed();
                if (location != null) {
                    coor.setLongtitude(location.getLongitude());
                    coor.setLatitude(location.getLatitude());
                    coor.setSpeed(speed_metres * 3.6);
                    double average_speed = calculateAverage(gps);
                    coor.setAverages(average_speed);
                    gps.add(coor);
                    double overall_av_speed = calculateOverallAverage(gps);
                    //update the UI
                    updateUI(speed_metres * 3.6, average_speed, overall_av_speed);
                }
            }
            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }
            @Override
            public void onProviderEnabled(String s) {
            }
            @Override
            public void onProviderDisabled(String s) {
                chronometer.setText("Time(s) : " + "N/A");
                speed.setText("Speed(km/h) : " + "N/A");
                curr_average.setText("Current Average(km/h) : " + "N/A");
                overall_average.setText("Overall Average (km/h) :" + "N/A");
                Toast.makeText(getApplicationContext(), "Please enable GPS", Toast.LENGTH_SHORT).show();
            }
           };
           startTracking.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  if(tracking==false){
                      startTracking(locationListener);
                      tracking=true;
                  }
                   else{
                        stopTracking(locationListener);
                        tracking = false;
                  }
                }
            });
    }
   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void startTracking(LocationListener locationListener) {
        //Create new coor
        //set gps back to null
        gps = new ArrayList<>();
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1, locationListener);
        }
    public void stopTracking(LocationListener locationListener){
        lm.removeUpdates(locationListener);
        chronometer.stop();
    }

    public void updateUI(Double curr_speed, Double average_speed_curr, Double overall_average_speed ){
            speed.setText("Speed(km/h) : "+curr_speed.toString());
            curr_average.setText("Current Average(km/h) : " + average_speed_curr.toString());
            overall_average.setText("Overall Average(km/h) : "+overall_average_speed.toString());

    }
    public double calculateAverage(ArrayList<Coordinates> gps_coor){
        double average_speed=0.0;
        if(gps_coor.size()>2) {
            for (int i = 0; i < gps_coor.size(); i++) {
                average_speed = gps_coor.get(i).getSpeed() + average_speed;
            }
            average_speed = average_speed / gps_coor.size();
        }
        return average_speed;
    }
    public Double calculateOverallAverage(ArrayList<Coordinates> gps_coor){
        double overall_averages = 0.0;
        if(gps_coor.size()>2) {
            for (int j = 0; j < gps_coor.size(); j++) {
                overall_averages = overall_averages + gps_coor.get(j).getAverages();
            }
            overall_averages = overall_averages / gps_coor.size();
        }
        return overall_averages;
    }

}
