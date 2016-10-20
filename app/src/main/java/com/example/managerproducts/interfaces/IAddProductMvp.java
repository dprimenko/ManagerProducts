package com.example.managerproducts.interfaces;

/**
 * Created by dprimenko on 20/10/16.
 */
public interface IAddProductMvp {
    interface View {
        void setMessageError(int messageError);
        void startListProductsActivity();
    }

    interface Presenter {
        void validateFields(String name, String description, String brand, String price, String concentration);
    }
}
