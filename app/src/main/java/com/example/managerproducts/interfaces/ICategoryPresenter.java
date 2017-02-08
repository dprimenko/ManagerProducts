package com.example.managerproducts.interfaces;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by usuario on 8/02/17.
 */

public interface ICategoryPresenter {

    interface View{
        Context getContext();
        void setCursorCategory(Cursor cursor);
    }

    void getAllCategoies();
}
