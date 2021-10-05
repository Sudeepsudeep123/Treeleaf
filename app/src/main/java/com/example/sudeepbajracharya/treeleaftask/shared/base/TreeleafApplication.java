package com.example.sudeepbajracharya.treeleaftask.shared.base;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TreeleafApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initRealm(getApplicationContext());
    }

    public void initRealm(Context context) {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .allowWritesOnUiThread(true)
                .build();
        Realm.setDefaultConfiguration(config);
//        Realm.getInstance(config);
    }
}
