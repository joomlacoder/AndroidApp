package xyz.lob.referenceofcomputerscience.ui.post;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import xyz.lob.referenceofcomputerscience.R;

public class ScrolingFragmentModel  extends AndroidViewModel {
    private String text;
    private int imgId;

    ScrolingFragmentModel(Application application, String cat, int idPost){
        super(application);
        text = getApplication().getString(R.string.large_text);
        imgId = idPost;
    }

    public String getText() {
        return text;
    }

    public int getImgId() {
        return imgId;
    }
}
