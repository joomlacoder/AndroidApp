package xyz.lob.referenceofcomputerscience.content.model;

import android.graphics.drawable.Drawable;

public class Post {
    public  String title;
    public  String content;
    public  String details;
    public  Drawable img;

    public Post(String title, String content, String details, Drawable img) {
        this.title = title;
        this.content = content;
        this.details = details;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return content;
    }

    public String getDetails() {
        return details + "";
    }

    public Drawable getImg() {
        return img;
    }

}