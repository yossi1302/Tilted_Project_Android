package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText SuName,SuEmail, SuPass;
    private Button register;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        SuName = findViewById(R.id.SuName);
        SuEmail = findViewById(R.id.SuEmail);
        SuPass = findViewById(R.id.SuPass);
        register = findViewById(R.id.register);
        mAuth = FirebaseAuth.getInstance();
        register.setOnClickListener(this);
        database = FirebaseDatabase.getInstance("https://tilted-test-default-rtdb.europe-west1.firebasedatabase.app/");
        back = findViewById(R.id.back3);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (register == v){
            createUser(SuEmail.getText().toString()+"",SuPass.getText().toString()+"");
        }
        if(v == back){
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
        }
    }

    public void createUser(String email, String password) {
        if(!email.equals("") && !password.equals("")) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String uid = mAuth.getCurrentUser().getUid().toString();
                                database.getReference("Users").child(uid).setValue(new User(SuName.getText().toString()+"",email,password));
                                Intent intent = new Intent(SignUpActivity.this, ShowPostsActivity.class);
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}


