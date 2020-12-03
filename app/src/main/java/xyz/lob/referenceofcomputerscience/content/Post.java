package xyz.lob.referenceofcomputerscience.content;

import android.graphics.drawable.Drawable;

public class Post {
    public final String title;
    public final String content;
    public final String details;
    public final Drawable img;

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
        return details;
    }

    public Drawable getImg() {
        return img;
    }

}