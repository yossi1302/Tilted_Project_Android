package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class ShowPostsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, OnClickListener {
    private DatabaseReference database;
    ListView ListView;
    ArrayList<Post> posts;
    ArrayAdapter<Post> arrayAdapter;
    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_posts);
        posts = new ArrayList<Post>();
        ListView = findViewById(R.id.listview);
        database = FirebaseDatabase.getInstance("https://tilted-test-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Posts");
        ListView.setAdapter(arrayAdapter);
        ListView.setOnItemClickListener(this);
        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data:snapshot.getChildren()){
                    Post post = data.getValue(Post.class);
                    posts.add(post);
                    Collections.reverse(posts);
                }
                arrayAdapter = new PostArrayAdapter(ShowPostsActivity.this,R.layout.list_row,posts);
                ListView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.profile){
            Intent intent = new Intent(this,ProfileActivity.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, PostActivity.class);
        Post post = posts.get(i);
        intent.putExtra("selected_post", post);
        startActivity(intent);
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, AddPostActivity.class);
        startActivity(intent);
    }
}