package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.javafaker.Faker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddPostActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseDatabase database;
    private EditText title;
    private EditText text;
    private Button button;
    private TextView User;
    private ImageView back;
    private FirebaseAuth mAuth;
    private User user1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        database = FirebaseDatabase.getInstance("https://tilted-test-default-rtdb.europe-west1.firebasedatabase.app/");
        title = findViewById(R.id.title);
        text = findViewById(R.id.text);
        button = findViewById(R.id.button1);
        mAuth = FirebaseAuth.getInstance();
        User = findViewById(R.id.username1);
        back = findViewById(R.id.back1);
        database.getReference("Users/"+mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user1 = snapshot.getValue(User.class);
                User.setText(user1.getName());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        button.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == button) {
            if (!(title.getText().toString() + "").equals("") && !(text.getText().toString() + "").equals("")) {
                database.getReference("Posts").push().setValue(new Post(title.getText().toString(), text.getText().toString(),user1.getName()));
                Intent intent = new Intent(this,ShowPostsActivity.class);
                startActivity(intent);
            }
        }
        if(view == back){
            Intent intent = new Intent(this,ShowPostsActivity.class);
            startActivity(intent);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home){
            Intent intent = new Intent(this, ShowPostsActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.profile){
            Intent intent = new Intent(this,ProfileActivity.class);
            startActivity(intent);
        }
        return true;
    }
}