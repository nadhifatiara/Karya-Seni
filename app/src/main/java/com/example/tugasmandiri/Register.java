package com.example.tugasmandiri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    Button btnSignUp1;
    EditText edtEmail, edtPass;

    private FirebaseAuth mAuth;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtEmail = findViewById(R.id.EdtEmailSignUp);
        edtPass = findViewById(R.id.EdtPassSignUp);
        btnSignUp1 = findViewById(R.id.SignUp);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        btnSignUp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = edtEmail.getText().toString();
                String pwd = edtPass.getText().toString();

                Bundle bun = new Bundle();
                bun.putString("email", mail);
                bun.putString("password", pwd);

                //ini firebase
                mAuth.createUserWithEmailAndPassword(mail, pwd)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("Sukses", "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(Register.this, "Authentication success.", Toast.LENGTH_LONG).show();

                                    Intent i = new Intent(Register.this, MainActivity.class);
                                    startActivity(i);

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("Sukses", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Register.this, "Authentication failed.", Toast.LENGTH_LONG).show();
                                }

                                // ...
                            }
                        });
            }
        });

    }
}
