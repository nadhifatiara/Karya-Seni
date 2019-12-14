package com.example.tugasmandiri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button btnSigIn, btnSignUp, btnGoogle, btnFb;
    EditText etEmail, etPass;
    TextView forgotPass;

    private FirebaseAuth mAuth;
    private static final String TAG = "AndroidClarified";
    private GoogleSignInClient googleSignInClient;
    private SignInButton googleSignInButton;

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSigIn = findViewById(R.id.SignIn);
        btnFb = findViewById(R.id.facebook);
        btnSignUp = findViewById(R.id.btnSinUp);
        etEmail = findViewById(R.id.EdtEmail);
        etPass = findViewById(R.id.EdtPass);
        forgotPass = findViewById(R.id.tvForgotPass);

        btnGoogle = findViewById(R.id.google);
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        btnSigIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String pass = etPass.getText().toString();

                Bundle bun = new Bundle();
                bun.putString("email", email);
                bun.putString("password", pass);

                mAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(MainActivity.this, "Authentication success.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(MainActivity.this, Main2Activity.class);
                                    startActivity(i);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
            }
        });

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(MainActivity.this, ForgotPassword.class);
                startActivity(i1);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(MainActivity.this, Register.class);
                startActivity(i2);
            }
        });
    }
}
