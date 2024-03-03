package com.srsoft.saptagirimess.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.srsoft.saptagirimess.databinding.ActivityRegistrationBinding;
import com.srsoft.saptagirimess.ui.modal.User;

public class RegistrationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ActivityRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialization();
    }

    private void initialization() {

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateUser();
            }

            private void validateUser() {

                String name = binding.etName.getText().toString();
                String age = binding.etAge.getText().toString();
                String email = binding.etEmail.getText().toString();
                String phone = binding.etPhone.getText().toString();
                String password = binding.etPassword.getText().toString().toUpperCase();

                if (name.matches("")) {
                    binding.etName.setError("Enter your name");
                } else if (age.matches("")) {
                    binding.etAge.setError("Enter your age");
                } else if (email.matches("")) {
                    binding.etEmail.setError("Enter your email");
                } else if (phone.matches("")) {
                    binding.etPhone.setError("Enter Phone Number");
                } else if (password.matches("")) {
                    binding.etPassword.setError("Set Password");
                } else {

                    mAuth = FirebaseAuth.getInstance();
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                String userId = task.getResult().getUser().getUid();
                                User user = new User(name, age, email, userId, phone, 0, "notApproved");
                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                Toast.makeText(RegistrationActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                                db.collection("users").document(userId).set(user).
                                        addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(RegistrationActivity.this, "Signing In!", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(RegistrationActivity.this, DashboardActivity.class);
                                            startActivity(intent);
                                            finishAffinity();
                                        } else {
                                            Toast.makeText(RegistrationActivity.this, "Failed to Login", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });


                            } else {

                            }
                        }
                    });
                }
            }
        });
    }


}