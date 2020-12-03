package xyz.lob.referenceofcomputerscience.ui.systemNumberCalc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import xyz.lob.referenceofcomputerscience.R;

public class SystemNumberFragment extends Fragment {

    //private SystemNumberViewModel mViewModel;

    public static SystemNumberFragment newInstance() {
        return new SystemNumberFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.system_number_fragment, container, false);
        ViewPager2 viewPager2 = view.findViewById(R.id.viewPager);
        Adapter myAdapter = new Adapter(this);
        myAdapter.addFragment(new FragmentCalc());
        myAdapter.addFragment(new FragmentCalc());
        viewPager2.setAdapter(myAdapter);
        viewPager2.setPageTransformer(new ZoomOutPageTransformer());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = new ViewModelProvider(this).get(SystemNumberViewModel.class);
    }

}