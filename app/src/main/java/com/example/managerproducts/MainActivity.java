package com.example.managerproducts;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.managerproducts.fragments.ManageProductFragment;
import com.example.managerproducts.fragments.MultiListCategoriesFragment;
import com.example.managerproducts.fragments.MultiListProductsFragment;

/**
 * Created by dprimenko on 15/12/16.
 */

public class MainActivity extends AppCompatActivity implements MultiListProductsFragment.MultiListProductFragmentListener, ManageProductFragment.ManageProductFragmentListener{

    private ManageProductFragment manageProductFragment;
    private MultiListProductsFragment multiListProductsFragment;
    private MultiListCategoriesFragment multiListCategoriesFragment;
    private NavigationView mNav;
    private DrawerLayout mDrawer;
    private Toolbar toolbar_main;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_navigation);
        multiListProductsFragment = MultiListProductsFragment.newInstance(null);
        mNav = (NavigationView)findViewById(R.id.navigation_view);
        mDrawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        toolbar_main = (Toolbar) findViewById(R.id.toolbar_main);

        setSupportActionBar(toolbar_main);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main, multiListProductsFragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                if (mDrawer != null) {
                    mDrawer.openDrawer(GravityCompat.START);
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMultiListProductFragment(Bundle bundle) {
        multiListProductsFragment = MultiListProductsFragment.newInstance(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_main, multiListProductsFragment);
        ft.commit();
    }

    public void onMultiListCategoryFragment(Bundle bundle) {
        multiListCategoriesFragment = MultiListCategoriesFragment.newInstance(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_main, multiListCategoriesFragment);
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

    public void setupDrawerContent(){
        mNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);

                switch (item.getItemId()){
                    case R.id.action_home:
                        break;
                    case R.id.action_list:
                        onMultiListProductFragment(null);
                        break;
                    case R.id.pharmacy:
                        //showPharmacy();
                        break;
                    case R.id.action_sales:
                        //showSales();
                        break;
                    case R.id.action_categories:
                        onMultiListCategoryFragment(null);
                        break;
                    default:
                        item.setChecked(false);
                        break;
                }
                mDrawer.closeDrawer(GravityCompat.START);
                setTitle(item.getTitle());
                return true;
            }
        });
    }
}
