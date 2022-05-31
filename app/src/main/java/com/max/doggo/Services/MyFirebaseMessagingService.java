package com.max.doggo.Services;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import com.max.doggo.Utils.UserUtils;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
   /* @Override
    public void onNewToken(@NonNull String s){
        super.onNewToken(s);
        if(FirebaseAuth.getInstance().getCurrentUser() != null)
            UserUtils.updateWalkerToken(this,s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage){
        super.onMessageReceived(remoteMessage);

    }*/
}
