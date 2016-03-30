package com.appsrox.smartcalendar;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.appsrox.smartcalendar.model.Alarm;
import com.appsrox.smartcalendar.model.AlarmMsg;

/**
 * @author appsrox.com
 *
 */
public class AlarmReceiver extends BroadcastReceiver {
	
//	private static final String TAG = "AlarmReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		long alarmMsgId = intent.getLongExtra(AlarmMsg.COL_ID, -1);
		long alarmId = intent.getLongExtra(AlarmMsg.COL_ALARMID, -1);
		
		AlarmMsg alarmMsg = new AlarmMsg(alarmMsgId);
		alarmMsg.setStatus(AlarmMsg.EXPIRED);
		alarmMsg.persist(RemindMe.db);
		
		Alarm alarm = new Alarm(alarmId);
		alarm.load(RemindMe.db);
		
		//Notification n = new Notification(R.drawable.ic_launcher, alarm.getName(), System.currentTimeMillis());
		PendingIntent pi = PendingIntent.getActivity(context, 0, new Intent(), 0);

		/*
		Notification notification;
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				context);
		notification = builder.setContentIntent(pi)
				.setSmallIcon(R.drawable.ic_launcher).setTicker(alarm.getName()).setWhen(System.currentTimeMillis())
				.setAutoCancel(true).setContentTitle("Remind Me")
				.setContentText(alarm.getName()).build();
		mNM.notify(NOTIFICATION, notification);*/

		//n.setLatestEventInfo(context, "Remind Me", alarm.getName(), pi);

		Notification n = new Notification.Builder(context)
				.setContentIntent(pi)
				.setAutoCancel(true)
				.setContentTitle("Remind Me")
				//.setContentText(subject)
				.setSmallIcon(R.drawable.ic_launcher)
				.setTicker(alarm.getName())
				.setWhen(System.currentTimeMillis())
				.setContentText(alarm.getName()).build();

		if (RemindMe.isVibrate()) {
			n.defaults |= Notification.DEFAULT_VIBRATE;
		}
		if (alarm.getSound()) {
			n.sound = Uri.parse(RemindMe.getRingtone());
//			n.defaults |= Notification.DEFAULT_SOUND;
		}		
		n.flags |= Notification.FLAG_AUTO_CANCEL;		
		
		NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		nm.notify((int)alarmMsgId, n);
	}

}
