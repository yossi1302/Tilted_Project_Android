package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.Row;
import com.example.myapplication.RowArrayAdapter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private ArrayList<Row> rows;
    private ArrayAdapter<Row> arrayAdapter;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mAuth = FirebaseAuth.getInstance();
        rows = new ArrayList<Row>();
        rows.add(new Row("Account","Privacy",R.drawable.profile));
        rows.add(new Row("Change Language","English, Arabic, Hebrew",R.drawable.language));
        rows.add(new Row("Help & Support","Policies, Report A Problem, Inbox",R.drawable.help));
        rows.add(new Row("Log Out","",R.drawable.signout));
        arrayAdapter = new RowArrayAdapter(this,R.layout.custom_row,rows);
        listView=findViewById(R.id.listview);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);
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
        else if(id == R.id.add){
            Intent intent = new Intent(this,AddPostActivity.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Row  row = rows.get(i);
        if (row.getTopic().equals("Log Out")){
            mAuth.signOut();
            Intent intent = new Intent(this, LogInActivity.class);
            startActivity(intent);
        }
    }
}