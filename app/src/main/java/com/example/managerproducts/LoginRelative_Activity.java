package com.example.managerproducts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.design.widget.Snackbar;

/**
 * Created by dprimenko on 19/10/16.
 */
public class LoginRelative_Activity  extends AppCompatActivity implements ILoginMvp.View{

    private LoginPresenter loginMvp;
    private EditText edtPassword;
    private EditText edtUser;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_relative);

        loginMvp = new LoginPresenter(this);
        edtUser = (EditText) findViewById(R.id.edtUser);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginMvp.validateCredentials(edtUser.getText().toString(), edtPassword.getText().toString());
            }
        });
    }

    @Override
    public void setMessageError(int messageError, int idView) {
        Snackbar.make(findViewById(R.id.relativeLayout), messageError, Snackbar.LENGTH_LONG).show();
        resetValues();
    }

    @Override
    public void startListProductsActivity() {
        Intent intent = new Intent(this, ListProducts_Activity.class);
        startActivity(intent);
    }

    private void resetValues() {
        edtUser.setText("");
        edtPassword.setText("");
    }
}
