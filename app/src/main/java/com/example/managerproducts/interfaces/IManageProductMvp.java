package com.example.managerproducts.interfaces;

import com.example.managerproducts.model.Product;

/**
 * Created by dprimenko on 20/10/16.
 */
public interface IManageProductMvp {
    interface View {
        void setMessageError(int messageError);
        void backToListProductFragment(Product product, int request);
    }

    interface Presenter {
        void validateFields(String name, String description, String brand, String price, String concentration, int request);
    }
}
