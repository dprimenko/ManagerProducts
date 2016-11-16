package com.example.managerproducts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.managerproducts.interfaces.IValidateUser;
import com.example.managerproducts.model.User;
import com.example.managerproducts.presenter.SignupPresenter;

/**
 * Created by dprimenko on 9/11/16.
 */

public class Signup_Activity extends AppCompatActivity implements IValidateUser.View {

    private Spinner spProvinces;
    private Spinner spCities;
    private Button btnSignUp;
    private RadioGroup typeClient;
    private TextInputLayout tilNameCompany;
    private AdapterView.OnItemSelectedListener spinnerListener;
    private ArrayAdapter<CharSequence> spProvincesAdapter;
    private SignupPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        spProvinces = (Spinner) findViewById(R.id.spn_ProvinceRegister);
        spCities = (Spinner) findViewById(R.id.spn_CityRegister);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        typeClient = (RadioGroup) findViewById(R.id.rg_EmpresaParticular);
        tilNameCompany = (TextInputLayout) findViewById(R.id.tilNameCompany);

        typeClient.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_Particular:
                        showCompany(false);
                        break;
                    case R.id.rb_Empresa:
                        showCompany(true);
                        break;
                }
            }
        });

        // Inicializar el Spinner Provincias
        spProvincesAdapter = ArrayAdapter.createFromResource(
                getApplicationContext(),
                R.array.array_provincia_a_localidades,
                android.R.layout.simple_spinner_dropdown_item);

        loadSpinnerProvinces();
    }

    public void signUp (View view) {
        // Recoger los datos de la vista y llama al método del presentador
        User user = new User();

        presenter.validateCredentials(user);
    }

    private void showCompany(boolean show) {
        tilNameCompany.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void loadSpinnerProvinces() {
        spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (view.getId()) {
                    case R.id.spn_ProvinceRegister:
                        spProvinces.setAdapter(spProvincesAdapter);
                        break;
                    case R.id.spn_CityRegister:
                        //spCities.setAdapter();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    public void showCitySelected() {

        Toast.makeText(getApplicationContext(), getString(R.string.message_province_city,
                spProvinces.getSelectedItem().toString(),
                spCities.getSelectedItem().toString()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setMessageError(String error, int errCode) {
        //String messageErro = getResources().getString(getResources().getIdentifier());
    }
}
