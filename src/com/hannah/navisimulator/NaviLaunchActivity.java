package com.hannah.navisimulator;

import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
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
		Builder build = new Notification.Builder(this).setContentTitle("Hey, listen!").setContentText("You enter a dark dungeon room, what do you do?")
				.setTicker("Hey, listen!").setSmallIcon(R.drawable.ic_action_search)
				.addAction(android.R.drawable.ic_btn_speak_now, "Yes?", PendingIntent.getActivity(getApplicationContext(), 0, getIntent(), 0, null))
				.addAction(android.R.drawable.ic_dialog_info, "Ignore", PendingIntent.getActivity(getApplicationContext(), 0, getIntent(), 0, null));

		Notification notification = new Notification.BigPictureStyle(build).bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.fly))
				.build();

		
		mNotificationManager.notify(0, notification);
	}

}
