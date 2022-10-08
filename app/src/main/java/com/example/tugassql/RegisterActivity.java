package com.example.tugassql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Handler;
import com.google.android.material.snackbar.Snackbar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText ETUsername, ETEmail, ETPassword;
    Button btnRegister;
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sqliteHelper = new SqliteHelper(this);
        initTextViewLogin();
        initViews();
        btnRegister.setOnClickListener(view -> {
            if (validateRegister()) {
                String UserName = ETUsername.getText().toString();
                String Email = ETEmail.getText().toString();
                String Password = ETPassword.getText().toString();

                if (!sqliteHelper.isEmailExists(Email)) {
                    sqliteHelper.addUser(new User(null, UserName, Email, Password));
                    Snackbar.make(btnRegister, "User created successfully! Please Login ", Snackbar.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, Snackbar.LENGTH_LONG);
                }
                else {
                    Snackbar.make(btnRegister, "User already exists with same email ", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initTextViewLogin() {
            TextView textViewLogin = findViewById(R.id.txtLogin);
            textViewLogin.setOnClickListener(view -> {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            });
    }

    private void initViews() {
        ETEmail = findViewById(R.id.txtEmailReg);
        ETPassword = findViewById(R.id.txtPasswordReg);
        ETUsername = findViewById(R.id.txtUsernameReg);
        btnRegister = findViewById(R.id.btnRegister);
    }

    public boolean validateRegister() {
        boolean validReg = false;
        boolean valUN = false;
        boolean valEM = false;
        boolean valPW = false;


        String UserName = ETUsername.getText().toString();
        String Email = ETEmail.getText().toString();
        String Password = ETPassword.getText().toString();

        //Handling validation for UserName field
        if (UserName.isEmpty()) {
            valUN = false;
            Toast.makeText(getApplication(), "Please enter valid username!", Toast.LENGTH_LONG).show();
        }
        else {
            if (UserName.length() > 5) {
                valUN = true;
            }
            else {
                valUN = false;
                Toast.makeText(getApplication(), "Username is too short!", Toast.LENGTH_LONG).show();
            }
        }

        //Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valEM = false;
            Toast.makeText(getApplication(), "Please enter valid email!", Toast.LENGTH_LONG).show();
        }
        else {
            valEM = true;
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valPW = false;
            Toast.makeText(getApplication(), "Please enter Password!", Toast.LENGTH_LONG).show();
        }
        else {
            if (Password.length() > 5) {
                valPW = true;
            }
            else {
                valPW = false;
                Toast.makeText(getApplication(), "Password is too short!", Toast.LENGTH_LONG).show();
            }
        }
        if (valEM && valUN && valPW){
            validReg = true;
        }
        else{
            validReg = false;
        }
        return validReg;
    }
}