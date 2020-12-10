package xyz.lob.referenceofcomputerscience.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import xyz.lob.referenceofcomputerscience.App;
import xyz.lob.referenceofcomputerscience.R;
import xyz.lob.referenceofcomputerscience.content.Content;
import xyz.lob.referenceofcomputerscience.fragment.posts.PostsFragment;
import xyz.lob.referenceofcomputerscience.ui.recycle.forever.ForeverRecyclerAdapter;
import xyz.lob.referenceofcomputerscience.ui.recycle.search.SearchAdapter;

public class HomeFragment extends Fragment {

    private ForeverRecyclerAdapter adapter;

    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        root.findViewById(R.id.searchRC).setVisibility(View.GONE);
        navController = NavHostFragment.findNavController(this);
        RecyclerView rc = (RecyclerView) root.findViewById(R.id.searchRC);
        rc.setLayoutManager(new LinearLayoutManager(rc.getContext(), LinearLayoutManager.VERTICAL, false));
        SearchAdapter ad = new SearchAdapter(navController);
        rc.setAdapter(ad);

        SearchView searchView = (SearchView) root.findViewById(R.id.search);
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                root.findViewById(R.id.scrollhomeMenu).setVisibility(View.VISIBLE);
                rc.setVisibility(View.GONE);
                return true;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                root.findViewById(R.id.scrollhomeMenu).setVisibility(View.GONE);
                rc.setVisibility(View.VISIBLE);
                ad.updateList(App.getInstance().getContent().getBySearch(query));
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() == 0) {
                    rc.setVisibility(View.GONE);
                    root.findViewById(R.id.scrollhomeMenu).setVisibility(View.VISIBLE);

                }
                return true;

            }
        });
        adapter = new ForeverRecyclerAdapter(navController);
        View view = root.findViewById(R.id.recyclerForever);
        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setAdapter(adapter);
        }
        initHomeMenu(root);
        return root;
    }

    private void initHomeMenu(View root) {
        root.findViewById(R.id.cardViewHomeWin).setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString(PostsFragment.ARG_CATEGORY, Content.Category.WIN.getTitle());
            navController.navigate(R.id.nav_postFragment, bundle);
        });
        root.findViewById(R.id.cardViewHomeLinux).findViewById(R.id.cardViewHomeLinux).setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString(PostsFragment.ARG_CATEGORY, Content.Category.LINUX.getTitle());
            navController.navigate(R.id.nav_postFragment, bundle);
        });
        root.findViewById(R.id.cardViewHomePC).findViewById(R.id.cardViewHomePC).setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString(PostsFragment.ARG_CATEGORY, Content.Category.PC.getTitle());
            navController.navigate(R.id.nav_postFragment, bundle);
        });
        root.findViewById(R.id.cardViewHomeNet).findViewById(R.id.cardViewHomeNet).setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString(PostsFragment.ARG_CATEGORY, Content.Category.NET.getTitle());
            navController.navigate(R.id.nav_postFragment, bundle);
        });
        root.findViewById(R.id.cardViewHomeCalc).findViewById(R.id.cardViewHomeCalc).setOnClickListener(v -> {
            navController.navigate(R.id.nav_calcSystemNumber);
        });
        root.findViewById(R.id.cardViewHomeGlas).findViewById(R.id.cardViewHomeGlas).setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString(PostsFragment.ARG_CATEGORY, Content.Category.GLAS.getTitle());
            navController.navigate(R.id.nav_postFragment, bundle);
        });
    }
}