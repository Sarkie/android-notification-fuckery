package com.javacodegeeks.android.notificationstest;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
   private NotificationManager myNotificationManager;
   private int notificationIdOne = 111;
   private int numMessagesOne = 0;

   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      Button timerBtn = (Button) findViewById(R.id.notificationTimer);
       timerBtn.setOnClickListener(new View.OnClickListener() {
           public void onClick(View view) {
               startTimer(view);
           }
       });
   }

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {

            displayNotificationOne();

            timerHandler.postDelayed(this, 500);
        }
    };

    private void startTimer(View view) {

        Button b = (Button) view;
        if (b.getText().equals("stop")) {
            timerHandler.removeCallbacks(timerRunnable);
            b.setText("start");
        } else {

            timerHandler.postDelayed(timerRunnable, 0);
            b.setText("stop");
        }

    }

    protected void displayNotificationOne() {
      // Invoking the default notification service
      NotificationCompat.Builder  mBuilder = new NotificationCompat.Builder(this);

      mBuilder.setContentTitle("Unkillable!");
      mBuilder.setContentText("Unkillable!");
      mBuilder.setTicker("Unkillable!");
      mBuilder.setSmallIcon(R.drawable.ic_launcher);

      // Increase notification number every time a new notification arrives
      mBuilder.setNumber(++numMessagesOne);

      myNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        myNotificationManager.cancelAll();

      // pass the Notification object to the system
      myNotificationManager.notify(notificationIdOne, mBuilder.build());
   }

   
}