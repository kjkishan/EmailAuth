package com.example.kishan.emailauth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserRegisterActivity extends AppCompatActivity {
    FirebaseAuth myAuth;
    EditText name,email,pass;
    Button reg;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        name=findViewById(R.id.Name);
        email=findViewById(R.id.Email1);
        pass=findViewById(R.id.Password1);
        reg=findViewById(R.id.Register1);
        progressBar=findViewById(R.id.Progress1);
        myAuth=FirebaseAuth.getInstance();
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eml,pas;
                eml=email.getText().toString().trim();
                pas=pass.getText().toString().trim();
                progressBar.setVisibility(View.VISIBLE);
                myAuth.createUserWithEmailAndPassword(eml,pas).addOnCompleteListener(UserRegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            progressBar.setVisibility(View.GONE);
                            startActivity(new Intent(UserRegisterActivity.this,LoginActivity.class));
                            Toast.makeText(UserRegisterActivity.this, "Registration Success", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            startActivity(new Intent(UserRegisterActivity.this,LoginActivity.class));
                            finish();
                        }
                    }
                });
            }
        });
    }
}
