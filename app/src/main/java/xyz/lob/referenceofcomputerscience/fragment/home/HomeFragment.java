package xyz.lob.referenceofcomputerscience.fragment.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import xyz.lob.referenceofcomputerscience.R;
import xyz.lob.referenceofcomputerscience.ui.recycle.forever.ForeverRecyclerAdapter;

public class HomeFragment extends Fragment {

    private ForeverRecyclerAdapter adapter;

    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        adapter = new ForeverRecyclerAdapter(navController);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        View view = root.findViewById(R.id.recyclerForever);
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setAdapter(adapter);
        }
        return root;
    }
}