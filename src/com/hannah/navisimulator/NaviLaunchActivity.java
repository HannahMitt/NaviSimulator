package com.hannah.navisimulator;

import android.app.Activity;
import android.app.NotificationManager;
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

	public void onFlyButtonClick(View view) {
		mNotificationManager.notify(0, NaviNotifications.getHeyListenNotification(this));
	}
}
