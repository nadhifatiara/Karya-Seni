package com.example.tugasmandiri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPassword extends AppCompatActivity {

    EditText etReset;
    Button btnReset;
    TextView tvBack;

    private FirebaseAuth mAuth;

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etReset = findViewById(R.id.newPass);
        btnReset = findViewById(R.id.btnNewPass);
        tvBack = findViewById(R.id.back);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etReset.getText().toString();

                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ForgotPassword.this, "Check email to reset your password!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ForgotPassword.this, "Fail to send reset password email!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
