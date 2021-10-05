package com.example.sudeepbajracharya.treeleaftask.shared.repository;

import android.util.Log;

import com.example.sudeepbajracharya.treeleaftask.shared.model.UserDetailModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.realm.Realm;
import io.realm.RealmResults;

public class UserListRepository {

    public Single<List<UserDetailModel>> retrive() {
        return Single.create(emitter -> {
            Realm realm = Realm.getDefaultInstance();
            RealmResults<UserDetailModel> results = realm.where(UserDetailModel.class).findAll();

            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<UserDetailModel> results = realm.where(UserDetailModel.class).findAll();
                    results.load();
                    Log.e("result", results.toString());
                }
            }, new Realm.Transaction.OnSuccess() {

                @Override
                public void onSuccess() {
                    emitter.onSuccess(results);
                }
            }, new Realm.Transaction.OnError() {

                @Override
                public void onError(Throwable error) {
                    emitter.onError(new Throwable(error.getLocalizedMessage()));
                }

            });
        });
    }
}
