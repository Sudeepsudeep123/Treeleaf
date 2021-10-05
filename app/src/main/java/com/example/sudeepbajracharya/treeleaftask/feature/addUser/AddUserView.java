package com.example.sudeepbajracharya.treeleaftask.feature.addUser;

import com.example.sudeepbajracharya.treeleaftask.shared.model.UserDetailModel;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

public interface AddUserView extends MvpView {
    void onSuccess(String message);
    void onFailure(String message);
}
