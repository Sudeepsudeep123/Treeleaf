package com.example.sudeepbajracharya.treeleaftask.feature.userList;

import com.example.sudeepbajracharya.treeleaftask.shared.model.UserDetailModel;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

public interface UserListView extends MvpView {
    void onSuccess(List<UserDetailModel> userDetailModelList);
    void onFailure(String message);
}
