package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.javafaker.Faker;

public class PostActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView Title;
    private TextView Text;
    private TextView user;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Title = findViewById(R.id.PTitle);
        Text = findViewById(R.id.PText);
        user = findViewById(R.id.username);
        back = findViewById(R.id.back);
        back.setOnClickListener(this);

        if(getIntent().hasExtra("selected_post")){
            Post post = (Post) getIntent().getSerializableExtra("selected_post");
            Title.setText(post.getTitle());
            Text.setText(post.getText());
            user.setText(post.getAuthor());
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ShowPostsActivity.class);
        startActivity(intent);
    }
}