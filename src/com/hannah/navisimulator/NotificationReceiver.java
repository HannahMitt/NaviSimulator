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
import android.graphics.BitmapFactory;

public class NotificationReceiver extends IntentService {

	private static final int NAVI_REPEAT_MILLIS = 1000 * 60;

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

			Builder build = new Notification.Builder(this).setContentTitle("Hey, listen!").setTicker("Hey, listen!").setSmallIcon(R.drawable.navi_icon);
			Notification notification = new Notification.BigTextStyle(build).bigText("Princess Zelda is in trouble!").build();
			mgr.notify(0, notification);

		} else if (ACTION_NAVI_IGNORE.equals(intent.getAction())) {
			TimerTask timerTask = new TimerTask() {

				@Override
				public void run() {
					Intent listenIntent = new Intent();
					listenIntent.setClass(NotificationReceiver.this, NotificationReceiver.class);
					listenIntent.setAction(NotificationReceiver.ACTION_NAVI_LISTEN);

					PendingIntent listenPending = PendingIntent.getService(NotificationReceiver.this, 0, listenIntent, 0);

					Intent ignoreIntent = new Intent();
					ignoreIntent.setClass(NotificationReceiver.this, NotificationReceiver.class);
					ignoreIntent.setAction(NotificationReceiver.ACTION_NAVI_IGNORE);

					PendingIntent ignorePending = PendingIntent.getService(NotificationReceiver.this, 0, ignoreIntent, 0);

					Builder build = new Notification.Builder(NotificationReceiver.this).setContentTitle("Hey, listen!").setTicker("Hey, listen!")
							.setSmallIcon(R.drawable.navi_icon).addAction(R.drawable.ic_stat_yes_icon, "Yes?", listenPending)
							.addAction(R.drawable.ic_stat_ignore_icon, "Ignore", ignorePending);

					Notification notification = new Notification.BigPictureStyle(build).bigPicture(
							BitmapFactory.decodeResource(getResources(), R.drawable.navi_glow)).build();
					mgr.notify(0, notification);
				}
			};

			Timer repeatTimer = new Timer();
			repeatTimer.schedule(timerTask, new Date(System.currentTimeMillis() + NAVI_REPEAT_MILLIS));
		}

	}

}
