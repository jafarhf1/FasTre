package com.example.fastre.ui.authentication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.fastre.R;
import com.example.fastre.ui.main.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;



public class RegisterFragment extends Fragment implements View.OnClickListener {
    private EditText editTextFullName, editTextEmail, editTextPassword, editTextAge, editTextBloodType;
    private Button mRegister;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        mAuth = FirebaseAuth.getInstance();
        mRegister = (Button) view.findViewById(R.id.btn_register);
        mRegister.setOnClickListener(this);
        editTextFullName = (EditText) view.findViewById(R.id.et_name);
        editTextEmail = (EditText) view.findViewById(R.id.et_email);
        editTextPassword = (EditText) view.findViewById(R.id.et_password);
        editTextAge = (EditText) view.findViewById(R.id.et_age);
        editTextBloodType = (EditText) view.findViewById(R.id.et_bloodType);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        return view;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_register_title:
                startActivity(new Intent(getContext(), MainActivity.class));
                break;
            case R.id.btn_register:
                registerUser();
                break;

        }
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String fullName = editTextFullName.getText().toString().trim();
        String bloodType = editTextBloodType.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();


        if (fullName.isEmpty())
        {
            editTextFullName.setError("Full name is required");
            editTextFullName.requestFocus();
            return;
        }

        if(age.isEmpty()){
            editTextAge.setError("Age is required");
            editTextAge.requestFocus();
            return;
        }

        if(bloodType.isEmpty()){
            editTextBloodType.setError("Blood Type is required");
            editTextBloodType.requestFocus();
            return;
        }
        if (email.isEmpty())
        {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please provide valid email");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editTextPassword.setError("Minimum Password length is 6");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        if(task.isSuccessful()){
                            User user = new User(fullName, age, bloodType, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>(){
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (task.isSuccessful()) {
                                        user.sendEmailVerification();
                                        Toast.makeText(getContext(), "User Has been Registered Succesfully, Verification Email Will be Sent Within 5 minutes", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);

                                    }
                                    else{
                                        Toast.makeText(getContext(), "User failed to register", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });


                        }else {
                            Toast.makeText(getContext(), "User failed to register", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });


    }
}