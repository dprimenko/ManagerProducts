package com.example.managerproducts;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;

/**
 * Created by dprimenko on 19/10/16.
 */
public class LoginPresenter implements ILoginMvp.Presenter{

    private ILoginMvp.View view;

    public LoginPresenter(ILoginMvp.View view) {

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
            // Guardar el usuario en la Clase Application
            ((LoginApplication)((Context)view).getApplicationContext()).setUser(new User(user, password));
            view.startListProductsActivity();
        }
    }
}
