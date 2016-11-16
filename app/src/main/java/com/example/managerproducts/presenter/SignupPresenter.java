package com.example.managerproducts.presenter;

import com.example.managerproducts.interfaces.IValidateUser;
import com.example.managerproducts.model.User;

/**
 * Created by dprimenko on 10/11/16.
 */

public class SignupPresenter implements IValidateUser.PresenterUser, IValidateUser.Presenter{

    IValidateUser.View view;

    public SignupPresenter(IValidateUser.View view){
        this.view = view;
    }


    public boolean validateCredentials (User user) {
        return true;
    }

}