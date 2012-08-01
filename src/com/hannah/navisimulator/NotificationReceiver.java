package com.hannah.navisimulator;

import android.app.IntentService;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.content.Intent;

public class NotificationReceiver extends IntentService {

	public static String ACTION_NAVI_LISTEN = "com.hannah.navisimulator.NAVI_LISTEN";
	public static String ACTION_NAVI_IGNORE = "com.hannah.navisimulator.NAVI_IGNORE";

	public static String SWITCH_NOTIFICATION_ARG_ID = "NOTIF_ID";

	public static String SWITCH_NOTIFICATION_ARG_NOTIFICATION = "NOTIF_NOTIFICATION";

	public NotificationReceiver() {

		super("NotificationReplaceService");

	}

	@Override
	protected void onHandleIntent(Intent intent) {

		NotificationManager mgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		if (ACTION_NAVI_LISTEN.equals(intent.getAction())) {

			Builder build = new Notification.Builder(this).setContentTitle("Hey, listen!").setTicker("Hey, listen!").setSmallIcon(R.drawable.navi_icon);
			Notification notification = new Notification.BigTextStyle(build).bigText("Princess Zelda is in trouble!").build();
			mgr.notify(0, notification);

		}

	}

}
