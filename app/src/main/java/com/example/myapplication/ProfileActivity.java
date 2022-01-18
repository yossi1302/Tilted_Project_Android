package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private Button settings;
    private FirebaseAuth mAuth;
    private DatabaseReference ref;
    private TextView name;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        settings = findViewById(R.id.settings);
        settings.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.username);
        image = findViewById(R.id.back4);
        image.setOnClickListener(this);
        ref = FirebaseDatabase.getInstance("https://tilted-test-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users/"+mAuth.getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                name.setText(user.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view == settings){
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);
        }
        if(view == image){
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
        if (id == R.id.add){
            Intent intent = new Intent(this, AddPostActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.home){
            Intent intent = new Intent(this,ShowPostsActivity.class);
            startActivity(intent);
        }
        return true;
    }


}