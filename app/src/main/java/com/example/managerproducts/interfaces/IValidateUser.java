package com.example.managerproducts.interfaces;

import android.util.Patterns;
import com.example.managerproducts.model.Error;

/**
 * Created by dprimenko on 10/11/16.
 */

public interface IValidateUser extends IValidateAccount {

    interface PresenterUser {
        static int validateEmail(String email){
            int result = 0;

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                result = Error.INVALID_MAIL;
            }
            else {
                result = Error.OK;
            }

            return result;
        }
    }
}