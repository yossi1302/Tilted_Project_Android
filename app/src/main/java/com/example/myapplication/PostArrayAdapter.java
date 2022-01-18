
package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.javafaker.Faker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PostArrayAdapter extends ArrayAdapter<Post> {
    private Context context;
    private int resource;


    public PostArrayAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Post> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(this.context).inflate(this.resource, parent, false);
            Post post = getItem(position);
        if (post!=null){
            TextView title = convertView.findViewById(R.id.RTitle);
            title.setText(post.getTitle());
            TextView text = convertView.findViewById(R.id.RText);
            text.setText(post.getText());
            TextView user = convertView.findViewById(R.id.Ruser);
            user.setText(post.getAuthor());
        }
        
        return convertView;
    }




}

