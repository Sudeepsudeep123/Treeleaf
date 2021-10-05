package com.example.sudeepbajracharya.treeleaftask.shared.repository;

import com.example.sudeepbajracharya.treeleaftask.shared.model.UserDetailModel;

import io.reactivex.rxjava3.core.Single;
import io.realm.Realm;

public class AddUserRepository {
    public Single<String> saveData(UserDetailModel userDetailModel) {
        return Single.create(emitter -> {
            Realm realm = Realm.getDefaultInstance();

            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(userDetailModel);
                }
            }, new Realm.Transaction.OnSuccess() {

                @Override
                public void onSuccess() {
                    emitter.onSuccess("Success");
                }
            }, new Realm.Transaction.OnError() {

                @Override
                public void onError(Throwable error) {
                    emitter.onError(new Throwable("Error"));
                }

            });
        });
    }
}
