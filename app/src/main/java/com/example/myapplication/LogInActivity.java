package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText SiEmail,SiPass;
    private Button login;
    private TextView create;
    private FirebaseAuth mAuth;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        SiEmail = findViewById(R.id.SiEmail);
        SiPass = findViewById(R.id.SiPass);
        login = findViewById(R.id.login);
        login.setOnClickListener(this);
        create = findViewById(R.id.text);
        create.setOnClickListener(this);
        create.setPaintFlags(create.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        mAuth = FirebaseAuth.getInstance();

        back = findViewById(R.id.back2);
        back.setOnClickListener(this);
        //mAuth.signOut(); that's for signing out, we still have to do lines 26&38 again.
    }

    @Override
    public void onClick(View v) {
        if(v == login){
            signIn(SiEmail.getText().toString()+"",SiPass.getText().toString()+"");
        }
        else if(v == create){
            Intent intent = new Intent(this,SignUpActivity.class);
            startActivity(intent);
        }
        else if(v == back){
            Intent intent = new Intent(this,StartActivity.class);
            startActivity(intent);
        }
    }

    public void signIn(String email, String password) {
        if (!email.equals("") && !password.equals("")) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(LogInActivity.this, ShowPostsActivity.class);
                                startActivity(intent);
                            } else {

                                Toast.makeText(LogInActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}