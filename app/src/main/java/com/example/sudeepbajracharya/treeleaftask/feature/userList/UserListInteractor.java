package com.example.sudeepbajracharya.treeleaftask.feature.userList;

import com.example.sudeepbajracharya.treeleaftask.shared.model.UserDetailModel;
import com.example.sudeepbajracharya.treeleaftask.shared.repository.UserListRepository;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class UserListInteractor {
    UserListRepository userListRepository = new UserListRepository();

    public Single<List<UserDetailModel>> retrieveUserList() {
        return userListRepository.retrive();
    }
}
