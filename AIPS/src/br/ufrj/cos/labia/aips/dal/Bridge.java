package br.ufrj.cos.labia.aips.dal;

import java.util.List;

import android.app.IntentService;
import android.content.Intent;
import android.net.wifi.ScanResult;

public class Bridge extends IntentService implements Scans {

	// WiFi Variables
	LoopScanner receiver;


	@Override
	public void processScans(List<ScanResult> results, float[] gyro,
			float millibars, float temp_celsius, float[] magn_uT,
			float proximity_cm, float[] gravity, float humidity,
			float[] acceleration, double lat, double lon) {
		Intent intent = new Intent("br.ufrj.cos.labia.aips.SENSOR_DATA");
		intent.putExtra("wifi", results.toArray());
		intent.putExtra("gyroscope", gyro);	
		intent.putExtra("pressure", millibars);	
		intent.putExtra("temperature", temp_celsius);
		intent.putExtra("magnetometer", magn_uT);	
		intent.putExtra("proximity", proximity_cm);	
		intent.putExtra("gravity", gravity);	
		intent.putExtra("humidity", humidity);	
		intent.putExtra("acceleration", acceleration);	
		intent.putExtra("latitude", lat);	
		intent.putExtra("longitude", lon);
		sendBroadcast(intent);
		
	}
	
	public Bridge(String name) {
		super(name);
		receiver = new LoopScanner(this, this);		
		receiver.acquire();
	}


	@Override
	public void calibrateSensor() {
		Intent intent = new Intent("br.ufrj.cos.labia.aips.NEEDS_CALIBRATION");
		sendBroadcast(intent);
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub
		
	}

}
