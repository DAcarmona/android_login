package com.example.hasdeloriente;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;

public class login extends AppCompatActivity {

    Button btnIngresar;
    EditText inputPassword, inputEmail;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        btnIngresar = findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (email.isEmpty() && password.isEmpty()){
                    Toast.makeText(login.this,"Ingresar los datos", Toast.LENGTH_SHORT).show();
                }else {
                    loginUser(email, password);
                }
            }
        });
    }

    private void loginUser(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(login.this,MainActivity.class));
                            Toast.makeText(login.this,"BIENVENIDO",Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(login.this, "ERROR AL INICIAR SESIÓN: "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(login.this,"ERROR AL INICIAR SESIÓN", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}