package com.example.myapplication;

public class Row {
    private String topic;
    private String text;
    private int img;

    public Row(String topic, String text,int img) {
        this.topic = topic;
        this.text = text;
        this.img = img;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Row{" +
                "topic='" + topic + '\'' +
                ", text='" + text + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}

