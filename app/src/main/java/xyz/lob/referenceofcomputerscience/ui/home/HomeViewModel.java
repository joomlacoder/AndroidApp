package xyz.lob.referenceofcomputerscience.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Приветсвуем в справочнике");
    }

    public LiveData<String> getText() {
        return mText;
    }
}