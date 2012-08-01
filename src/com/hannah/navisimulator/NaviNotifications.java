package com.hannah.navisimulator;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;

public class NaviNotifications {

	public static Notification getHeyListenNotification(Context context) {
		Intent listenIntent = new Intent();
		listenIntent.setClass(context, NotificationReceiver.class);
		listenIntent.setAction(NotificationReceiver.ACTION_NAVI_LISTEN);

		PendingIntent listenPending = PendingIntent.getService(context, 0, listenIntent, 0);

		Intent ignoreIntent = new Intent();
		ignoreIntent.setClass(context, NotificationReceiver.class);
		ignoreIntent.setAction(NotificationReceiver.ACTION_NAVI_IGNORE);

		PendingIntent ignorePending = PendingIntent.getService(context, 0, ignoreIntent, 0);

		Builder build = new Notification.Builder(context).setContentTitle("Hey, listen!").setTicker("Hey, listen!").setSmallIcon(R.drawable.navi_icon)
				.addAction(R.drawable.ic_stat_yes_icon, "Yes?", listenPending).setDeleteIntent(ignorePending);

		Notification notification = new Notification.BigPictureStyle(build).bigPicture(
				BitmapFactory.decodeResource(context.getResources(), R.drawable.navi_glow)).build();

		return notification;
	}
}
