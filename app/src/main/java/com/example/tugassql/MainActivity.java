package com.example.tugassql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;




// TESTTT GIT

public class MainActivity extends AppCompatActivity {

    EditText ETEmail, ETPassword;
    Button loginnn;
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqliteHelper = new SqliteHelper(this);
        initCreateAccountTextView();
        initViews();

        login.setOnClickListener(view -> {
            if(validateLogin()){
                String email = ETEmail.getText().toString();
                String pass = ETPassword.getText().toString();

                User currentUser =sqliteHelper.Authenticate(new User(null,null, email, pass));

                if (currentUser != null) {
                    Snackbar.make(login, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();
                    //User Logged in Successfully Launch You home screen activity
                    String username = currentUser.userName;
                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                    intent.putExtra("Username", username);
                    startActivity(intent);
                    finish();
                }
                else {
                    // User Logged in Failed
                    Snackbar.make(login, "Failed to log in , please try again", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initCreateAccountTextView() {
        TextView textViewCreateAccount = (TextView) findViewById(R.id.txtRegister);
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        ETEmail = findViewById(R.id.txtEmail);
        ETPassword = findViewById(R.id.txtPassword);
        login = findViewById(R.id.btnLogin);
    }

    private boolean validateLogin() {
        boolean validuser = false;
        boolean validEM = false;
        boolean validPW = false;

        // Get values from EditText fields
        String Email = ETEmail.getText().toString();
        String Password = ETPassword.getText().toString();

        // Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            validEM = false;
            Toast.makeText(getApplication(), "Please enter valid email!", Toast.LENGTH_LONG).show();
        }
        else {
            validEM = true;
        }

        // Handling validation for Password field
        if (Password.isEmpty()) {
            validPW = false;
            Toast.makeText(getApplication(), "Please enter Password!", Toast.LENGTH_LONG).show();
        }
        else {
            if (Password.length() > 5) {
                validPW = true;
            } else {
                validPW = false;
                Toast.makeText(getApplication(), "Password is too short!", Toast.LENGTH_LONG).show();
            }
        }
        if (validEM && validPW){
            validuser = true;
        }
        else{
            validuser = false;
        }
        return validuser;
    }
}