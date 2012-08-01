package com.hannah.navisimulator;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.content.Intent;

public class NotificationReceiver extends IntentService {

	private static final int NAVI_REPEAT_MILLIS = 1000 * 20;

	public static String ACTION_NAVI_LISTEN = "com.hannah.navisimulator.NAVI_LISTEN";
	public static String ACTION_NAVI_IGNORE = "com.hannah.navisimulator.NAVI_IGNORE";

	public static String SWITCH_NOTIFICATION_ARG_NOTIFICATION = "NOTIF_NOTIFICATION";

	public NotificationReceiver() {

		super("NotificationReplaceService");

	}

	@Override
	protected void onHandleIntent(Intent intent) {

		final NotificationManager mgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		if (ACTION_NAVI_LISTEN.equals(intent.getAction())) {
			Intent ignoreIntent = new Intent();
			ignoreIntent.setClass(this, NotificationReceiver.class);
			ignoreIntent.setAction(NotificationReceiver.ACTION_NAVI_IGNORE);

			PendingIntent ignorePending = PendingIntent.getService(this, 0, ignoreIntent, 0);

			Builder build = new Notification.Builder(this).setContentTitle("Hey, listen!").setTicker("Hey, listen!").setSmallIcon(R.drawable.navi_icon)
					.setDeleteIntent(ignorePending);
			Notification notification = new Notification.BigTextStyle(build).bigText("Princess Zelda is in trouble!").build();
			mgr.notify(0, notification);

		} else if (ACTION_NAVI_IGNORE.equals(intent.getAction())) {
			TimerTask timerTask = new TimerTask() {

				@Override
				public void run() {
					mgr.notify(0, NaviNotifications.getHeyListenNotification(NotificationReceiver.this));
				}
			};

			Timer repeatTimer = new Timer();
			repeatTimer.schedule(timerTask, new Date(System.currentTimeMillis() + NAVI_REPEAT_MILLIS));
		}

	}

}
