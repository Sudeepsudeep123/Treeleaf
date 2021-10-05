package com.example.sudeepbajracharya.treeleaftask.feature.userList;


import android.util.Log;

import com.example.sudeepbajracharya.treeleaftask.feature.addUser.AddUserView;
import com.example.sudeepbajracharya.treeleaftask.shared.model.UserDetailModel;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class UserListPresenter implements  MvpPresenter<UserListView> {
    UserListInteractor userListInteractor;
    UserListView view;

    @Override
    public void attachView( UserListView view) {
        userListInteractor = new UserListInteractor();
        this.view = view;
    }

    @Override
    public void detachView(boolean retainInstance) {
        userListInteractor = null;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void destroy() {

    }

    public void getData() {

        userListInteractor.retrieveUserList().subscribe(new SingleObserver<List<UserDetailModel>>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull List<UserDetailModel> userDetailModelList) {
                view.onSuccess(userDetailModelList);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("error", e.getLocalizedMessage());
                view.onFailure(e.getLocalizedMessage());
            }

        });
    }


}
