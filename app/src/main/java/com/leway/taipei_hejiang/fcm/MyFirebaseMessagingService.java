package com.leway.taipei_hejiang.fcm;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Li Chia-en on 2017/5/8.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private String TAG = this.getClass().getName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        RemoteMessage.Notification notification = remoteMessage.getNotification();

    }

}
