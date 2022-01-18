package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Post implements Serializable {
    private String Title;
    private String Text;
    private String author;

    public Post(String title, String text, String author){
        this.Title = title;
        this.Text = text;
        this.author = author;
    }

    public Post(){

    }

    protected Post(Parcel in) {
        Title = in.readString();
        Text = in.readString();
    }

    public String getTitle() {
        return Title;
    }

    public String getText() {
        return Text;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
