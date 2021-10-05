package com.example.sudeepbajracharya.treeleaftask.feature.addUser;


import com.example.sudeepbajracharya.treeleaftask.shared.model.UserDetailModel;
import com.example.sudeepbajracharya.treeleaftask.shared.utils.CheckValidation;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class AddUserPresenter implements MvpPresenter<AddUserView> {
    AddUserInteractor addUserInteractor;
    AddUserView view;

    @Override
    public void attachView(AddUserView view) {
        this.addUserInteractor = new AddUserInteractor();
        this.view = view;

    }

    @Override
    public void detachView(boolean retainInstance) {
        addUserInteractor = null;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void destroy() {

    }

    public void saveData(UserDetailModel userDetailModel) {
        CheckValidation checkValidation = new CheckValidation();
        if (checkValidation.checkIfFieldsValid(userDetailModel)) {
            addUserInteractor.saveToDB(userDetailModel).subscribe(new SingleObserver<String>() {

                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onSuccess(@NonNull String s) {
                    view.onSuccess(s);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    view.onFailure(e.getLocalizedMessage());
                }
            });
        } else {
            view.onFailure("All form fields are mandetory and email should be in proper order.");
        }
    }

}
