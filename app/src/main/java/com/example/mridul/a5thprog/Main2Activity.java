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

public class Main2Activity extends AppCompatActivity {
    ProgressDialog progress;
    FirebaseAuth fire;
    private EditText email1;
    private EditText password1;
    private Button button;
    private TextView ob1;

    private void userlogin() {
        String Email1 = email1.getText().toString().trim();
        String Password1 = password1.getText().toString().trim();
        if (TextUtils.isEmpty(Email1) || TextUtils.isEmpty(Password1)) {
            Toast.makeText(this, "Please Enter the Email and Password", Toast.LENGTH_SHORT).show();
            return;
        }
        progress.setMessage("Please Wait a Moment...");
        progress.show();
        fire.signInWithEmailAndPassword(Email1, Password1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progress.dismiss();
                Intent startintent = new Intent(getApplicationContext(), Main3Activity.class);
                startActivity(startintent);
            }
        });


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        progress = new ProgressDialog(Main2Activity.this);
        fire = FirebaseAuth.getInstance();
        if (fire.getCurrentUser() != null) {
            //Start the profile activity
            //in this case the user is already logged in
        }
        email1 = findViewById(R.id.editText3);
        password1 = findViewById(R.id.editText4);
        button = findViewById(R.id.button2);
        ob1 = findViewById(R.id.textView3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userlogin();
            }
        });
        ob1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent start = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(start);
            }
        });

    }
}
