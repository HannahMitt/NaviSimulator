package com.hannah.navisimulator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;

@SuppressLint("NewApi")
public class NaviLaunchActivity extends Activity {

	private NotificationManager mNotificationManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_navi_launch);

		if (android.os.Build.VERSION.SDK_INT >= 11) {
			getActionBar().setTitle(R.string.title_activity_navi_launch);
			getActionBar().setDisplayShowHomeEnabled(false);
		}

		mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	}

	public void onFlyButtonClick(View view) {
		mNotificationManager.notify(0, NaviNotifications.getHeyListenNotification(this));
	}
}
