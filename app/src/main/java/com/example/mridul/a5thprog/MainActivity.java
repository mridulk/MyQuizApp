package com.example.mridul.a5thprog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button obj;
    private TextView obj1;
    private ProgressDialog progessdialog;
    private FirebaseAuth firebaseauth;

    //making firebase object to acess the authentacation process
    private void RegisterUser() {
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        if (TextUtils.isEmpty(Email)) {
            Toast.makeText(this, "please enter the email", Toast.LENGTH_SHORT).show();
            return;
            //it will stop the function from execution
        }
        if (TextUtils.isEmpty(Password)) {
            Toast.makeText(this, "please enter the password", Toast.LENGTH_SHORT).show();
            return;
            //it will stop the function from execution
        }
        progessdialog.setMessage("Registering User...");
        progessdialog.show();
        firebaseauth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //user is sucessfully registered
                    progessdialog.hide();
                    Toast.makeText(MainActivity.this, "user is sucessfully Registered", Toast.LENGTH_SHORT).show();
                    Intent st = new Intent(getApplicationContext(), Main3Activity.class);
                } else {
                    //user not sucessfully registered
                    Toast.makeText(MainActivity.this, "user not sucessfully registered", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseauth = FirebaseAuth.getInstance();
        progessdialog = new ProgressDialog(this);
        email = findViewById(R.id.editText1);
        password = findViewById(R.id.editText2);
        obj1 = findViewById(R.id.textView);
        obj = findViewById(R.id.button1);
        obj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == obj) {
                    RegisterUser();
                }


            }
        });
        obj1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
            }
        });


    }
}
