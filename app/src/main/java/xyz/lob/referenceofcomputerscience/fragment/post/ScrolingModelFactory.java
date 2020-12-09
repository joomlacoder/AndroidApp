package xyz.lob.referenceofcomputerscience.fragment.post;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ScrolingModelFactory extends ViewModelProvider.AndroidViewModelFactory {

    private  String cat;
    private  Drawable img;
    private  Application application;

    public ScrolingModelFactory(Application context, String cat, Drawable img) {
        super(context);
        this.cat = cat;
        this.img = img;
        application = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == ScrolingFragmentModel.class) {
            return (T) new ScrolingFragmentModel(application, cat, img);
        }
        return null;
    }
}