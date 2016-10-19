package com.example.managerproducts;

/**
 * Created by dprimenko on 19/10/16.
 */
public interface ILoginMvp {

    int PASSWORD_DIGIT = 1;
    int PASSWORD_CASE = 2;
    int PASSWORD_LENGTH = 3;
    int DATA_EMPTY = 4;

    interface View {
        void setMessageError(int messageError, int idView);
        void startListProductsActivity();
    }

    interface Presenter {
        void validateCredentials(String user, String password);
    }
}
