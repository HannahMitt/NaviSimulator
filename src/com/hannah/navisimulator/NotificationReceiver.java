package com.hannah.navisimulator;

import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;

public class NotificationReceiver extends IntentService {

	public static String ACTION_NAVI_LISTEN = "com.hannah.navisimulator.NAVI_LISTEN";
	public static String ACTION_NAVI_IGNORE = "com.hannah.navisimulator.NAVI_IGNORE";

	public static String SWITCH_NOTIFICATION_ARG_NOTIFICATION = "NOTIF_NOTIFICATION";

	private static final int NAVI_REPEAT_MILLIS = 1000 * 20;
	private static final String[] NAVI_MESSAGES = { "Princess Zelda is in trouble!", "This area is dangerous.", "Go find the Spiritual Stones!" };
	private static final String[] RESPONSES = { "I know.", "-_-", "Ok?" };

	private Random mRandom;

	public NotificationReceiver() {
		super("NaviNotificationService");
		mRandom = new Random();
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		final NotificationManager mgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		if (ACTION_NAVI_LISTEN.equals(intent.getAction())) {

			int messageId = mRandom.nextInt(NAVI_MESSAGES.length);
			int responseId = mRandom.nextInt(RESPONSES.length);

			mgr.notify(0, NaviNotifications.getListenNotification(this, NAVI_MESSAGES[messageId], RESPONSES[responseId]));

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
