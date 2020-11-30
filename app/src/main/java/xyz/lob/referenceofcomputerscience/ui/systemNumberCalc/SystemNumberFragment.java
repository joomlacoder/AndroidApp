package xyz.lob.referenceofcomputerscience.ui.systemNumberCalc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import xyz.lob.referenceofcomputerscience.R;

public class SystemNumberFragment extends Fragment {

    //private SystemNumberViewModel mViewModel;

    public static SystemNumberFragment newInstance() {
        return new SystemNumberFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.system_number_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = new ViewModelProvider(this).get(SystemNumberViewModel.class);
    }

}