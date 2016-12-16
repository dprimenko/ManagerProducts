package com.example.managerproducts;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.managerproducts.fragments.ListProductsFragment;
import com.example.managerproducts.fragments.ManageProductFragment;
import com.example.managerproducts.fragments.MultiListProductsFragment;

/**
 * Created by dprimenko on 15/12/16.
 */

public class MainActivity extends AppCompatActivity implements MultiListProductsFragment.ListProductFragmentListener, ListProductsFragment.ListProductFragmentListener, ManageProductFragment.ManageProductFragmentListener{

    private ListProductsFragment listProductsFragment;
    private ManageProductFragment manageProductFragment;
    private MultiListProductsFragment multiListProductsFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        multiListProductsFragment = MultiListProductsFragment.newInstance(null);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main, multiListProductsFragment).commit();
    }

    @Override
    public void onListProductFragment(Bundle bundle) {
        multiListProductsFragment = MultiListProductsFragment.newInstance(null);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_main, multiListProductsFragment);
        ft.commit();
    }

    @Override
    public void onManageProductFragmentListener(Bundle bundle) {
        manageProductFragment = ManageProductFragment.newInstance(null);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_main, manageProductFragment);
        ft.addToBackStack("MANAGE");
        ft.commit();
    }
}
