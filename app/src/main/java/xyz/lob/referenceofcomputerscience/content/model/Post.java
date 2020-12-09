package xyz.lob.referenceofcomputerscience.content.model;

import android.graphics.drawable.Drawable;

public class Post {
    private   String title;
    private   String content;
    private   String details;
    private   Drawable img;
    private   Boolean isForever;



    public Post(String title, String content, String details, Drawable img) {
        this.title = title;
        this.content = content;
        this.details = details;
        this.img = img;
    }

    public Post setTitle(String title) {
        this.title = title;
        return this;
    }

    public Post setContent(String content) {
        this.content = content;
        return this;
    }

    public Post setDetails(String details) {
        this.details = details;
        return this;
    }

    public Post setImg(Drawable img) {
        this.img = img;
        return this;
    }

    public Post setForever(Boolean forever) {
        isForever = forever;
        return this;
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

    public Boolean getForever() {
        return isForever;
    }
    public String getContent() {
        return content;
    }
}