package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.teamproject.databinding.ActivityLoginBinding;
import com.example.teamproject.databinding.ActivitySignupBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.txtEmail.getText().toString();
                String password = binding.txtPassword.getText().toString();

                if(email.equals("")|| password.equals("")){
                    Toast.makeText(LoginActivity.this,"All fields are mandatory",Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkCredential = databaseHelper.checkEmailPassword(email,password);


                    if(checkCredential == true){

                        Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this,"Invalid Credential",Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        binding.txtAlready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });
    }


}