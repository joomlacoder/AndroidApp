package xyz.lob.referenceofcomputerscience.ui.post;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ScrolingModelFactory extends ViewModelProvider.AndroidViewModelFactory {

    private final String cat;
    private final int id;
    private final Application application;

    public ScrolingModelFactory(Application context, String cat, int id) {
        super(context);
        this.cat = cat;
        this.id = id;
        application = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == ScrolingFragmentModel.class) {
            return (T) new ScrolingFragmentModel(application, cat, id);
        }
        return null;
    }
}