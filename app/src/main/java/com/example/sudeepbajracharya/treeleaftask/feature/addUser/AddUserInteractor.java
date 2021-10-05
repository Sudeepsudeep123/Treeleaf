package com.example.sudeepbajracharya.treeleaftask.feature.addUser;

import com.example.sudeepbajracharya.treeleaftask.shared.model.UserDetailModel;
import com.example.sudeepbajracharya.treeleaftask.shared.repository.AddUserRepository;

import io.reactivex.rxjava3.core.Single;

public class AddUserInteractor {
    AddUserRepository addUserRepository = new AddUserRepository();

    public Single<String> saveToDB(UserDetailModel userDetailModelList){
        return addUserRepository.saveData(userDetailModelList);

    }
}
