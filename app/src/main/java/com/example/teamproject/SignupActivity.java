package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.teamproject.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.txtEmail.getText().toString();
                String password = binding.txtPassword.getText().toString();
                String confirmpass = binding.txtRetypepassword.getText().toString();

                if (email.equals("")|| password.equals("")||confirmpass.equals("")){
                    Toast.makeText(SignupActivity.this,"Please Fill All The Fields", Toast.LENGTH_SHORT).show();

                }else {
                    if (password.equals(confirmpass)){
                        Boolean checkUserEmail = databaseHelper.checkEmail(email);

                        if (checkUserEmail == false){
                            Boolean insert = databaseHelper.insertData(email,password);

                            if (insert==true){
                                Toast.makeText(SignupActivity.this,"Signup Successfull",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignupActivity.this,"Signup Failed",Toast.LENGTH_SHORT).show();

                            }



                        }
                        else {
                            Toast.makeText(SignupActivity.this,"User Already Exists,Please login",Toast.LENGTH_SHORT).show();

                        }

                    }else {
                        Toast.makeText(SignupActivity.this,"Invalid Password",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        binding.txtAlreadylogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);

            }
        });
    }
}