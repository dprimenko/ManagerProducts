package com.example.managerproducts.interfaces;

import com.example.managerproducts.model.Category;
import com.example.managerproducts.model.Product;

/**
 * Created by usuario on 2/02/17.
 */

public interface IManageCategoryMvp {

    interface View {
        void setMessageError(int messageError);
        void backToListCategoryFragment(Category category, int request);
    }

    interface Presenter {
        void validateFields(String category);
    }
}
