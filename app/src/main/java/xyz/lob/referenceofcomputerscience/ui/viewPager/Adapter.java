package xyz.lob.referenceofcomputerscience.ui.viewPager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class Adapter extends FragmentStateAdapter {

    private ArrayList<Fragment> arrayList = new ArrayList<>();

    public Adapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return arrayList.get(position);
    }

    public void addFragment(Fragment fragment) {
        arrayList.add(fragment);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
