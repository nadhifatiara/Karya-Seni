package com.example.tugasmandiri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    Button btnSigIn, btnSignUp, btnGoogle, btnFb;
    EditText etEmail, etPass;
    TextView forgotPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSigIn = findViewById(R.id.SignIn);
        btnGoogle = findViewById(R.id.google);
        btnFb = findViewById(R.id.facebook);
        btnSignUp = findViewById(R.id.btnSinUp);
        etEmail = findViewById(R.id.EdtEmail);
        etPass = findViewById(R.id.EdtPass);
        forgotPass = findViewById(R.id.tvForgotPass);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });


    }
}
