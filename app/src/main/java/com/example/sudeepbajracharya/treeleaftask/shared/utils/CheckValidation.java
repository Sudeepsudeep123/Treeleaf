package com.example.sudeepbajracharya.treeleaftask.shared.utils;

import android.util.Patterns;

import com.example.sudeepbajracharya.treeleaftask.shared.model.UserDetailModel;

public class CheckValidation {
    public Boolean checkIfFieldsValid(UserDetailModel userDetailModel) {

        if (userDetailModel.getName().equals("") || userDetailModel.getName() == null) {
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(userDetailModel.getMail()).matches()) {
            return false;
        }

        if (userDetailModel.getPhone().equals("") || userDetailModel.getPhone() == null) {
            return false;
        }

        if (userDetailModel.getDevice().equals("") || userDetailModel.getDevice() == null) {
            return false;
        }

        if (userDetailModel.getDate().equals("") || userDetailModel.getDate() == null) {
            return false;
        }

//        userDetailModel.getImageUrl();
        if (userDetailModel.getImageUrl() == null) {
            return false;
        }

        return true;
    }
}
