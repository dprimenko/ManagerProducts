package com.example.managerproducts.interfaces;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by usuario on 9/02/17.
 */

public interface IListPresenter {

    interface View{
        Context getContext();
        Cursor getCursor();
        void setCursor(Cursor cursor);
    }

    void getAllItems(int resource);
}
