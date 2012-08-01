package com.hannah.navisimulator;

import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;


public class NaviLaunchActivity extends Activity {

	private NotificationManager mNotificationManager;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_navi_launch);

		mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	}
	
	public void onFlyButtonClick(View view){
		Intent listenIntent = new Intent();
		listenIntent.setClass(this, NotificationReceiver.class);
		listenIntent.setAction(NotificationReceiver.ACTION_NAVI_LISTEN);
		
		PendingIntent listenPending = PendingIntent.getService(this, 0, listenIntent, 0);
		
		Builder build = new Notification.Builder(this).setContentTitle("Hey, listen!")
				.setTicker("Hey, listen!").setSmallIcon(R.drawable.navi_icon)
				.addAction(R.drawable.ic_stat_yes_icon, "Yes?", listenPending)
				.addAction(R.drawable.ic_stat_ignore_icon, "Ignore", PendingIntent.getActivity(getApplicationContext(), 0, getIntent(), 0, null));

		Notification notification = new Notification.BigPictureStyle(build).bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.navi_glow))
				.build();

		
		mNotificationManager.notify(0, notification);
	}

}
