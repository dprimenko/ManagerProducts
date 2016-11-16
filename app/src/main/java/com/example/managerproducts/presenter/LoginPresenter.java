package com.example.managerproducts.presenter;

import android.text.TextUtils;

import com.example.managerproducts.R;

/**
 * Created by dprimenko on 19/10/16.
 */
public class LoginPresenter implements IValidateUser.Presenter{

    private IValidateUser.View view;

    public LoginPresenter(IValidateUser.View view) {

        this.view = view;
    }

    @Override
    public void validateCredentials(String user, String password) {
        if (TextUtils.isEmpty(user)|| TextUtils.isEmpty(password)) {
            view.setMessageError(R.string.data_empty, R.id.edtUser);
        }
        else if (!((password.matches(".*[a-z]*.")) && (password.matches(".*[A-Z]*.")))) {
            view.setMessageError(R.string.password_case, R.id.edtPassword);
        }
        else if (!(password.matches(".*[0-9]*."))) {
            view.setMessageError(R.string.password_digit, R.id.edtPassword);
        }
        else if (password.length() < 8) {
            view.setMessageError(R.string.password_length, R.id.edtPassword);
        }
        else {
            view.startListProductsActivity();
        }
    }
}
