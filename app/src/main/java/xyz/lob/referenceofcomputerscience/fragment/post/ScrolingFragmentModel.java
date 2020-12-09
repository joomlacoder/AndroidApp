package xyz.lob.referenceofcomputerscience.fragment.post;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.lifecycle.AndroidViewModel;

import xyz.lob.referenceofcomputerscience.R;

public class ScrolingFragmentModel  extends AndroidViewModel {
    private String text;
    private Drawable img;

    ScrolingFragmentModel(Application application, String cat, Drawable img){
        super(application);
        text = getApplication().getString(R.string.large_text);
        img = img;
    }

    public String getText() {
        return text;
    }

    public Drawable getImg() {
        return img;
    }
}
