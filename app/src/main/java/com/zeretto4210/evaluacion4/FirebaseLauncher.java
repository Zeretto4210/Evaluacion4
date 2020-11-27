package com.zeretto4210.evaluacion4;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class FirebaseLauncher extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
