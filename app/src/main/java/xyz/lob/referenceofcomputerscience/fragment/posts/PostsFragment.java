package xyz.lob.referenceofcomputerscience.fragment.posts;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import xyz.lob.referenceofcomputerscience.App;
import xyz.lob.referenceofcomputerscience.R;
import xyz.lob.referenceofcomputerscience.content.model.Post;
import xyz.lob.referenceofcomputerscience.ui.recycle.OnSelectedRecyclerItemListener;
import xyz.lob.referenceofcomputerscience.ui.recycle.post.PostRecyclerViewAdapter;


public class PostsFragment extends Fragment implements OnSelectedRecyclerItemListener {

    public static final String ARG_COLUMN_COUNT = "column-count";
    public static final String ARG_CATEGORY = "category";
    public static final String ARG_ID = "id";

    private String category;
    private int mColumnCount = 1;

    private PostRecyclerViewAdapter adapter;
    private List<Post> posts = new ArrayList<>();

    @Override
    public void onSelectedRecyclerItem(int id) {
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, category);
        args.putInt(ARG_ID, id);
        Navigation.findNavController(this.getView()).navigate(R.id.action_nav_postFragment_to_nav_scrollingFragment, args);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            category = getArguments().getString(ARG_CATEGORY);
        }
        posts = App.getInstance().getContent().makeCategory(category);
        adapter = new PostRecyclerViewAdapter(posts, this, getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(adapter);
        }
        getActivity().setTitle(category);
        return view;
    }

    @SuppressWarnings("unused")
    public static PostsFragment newInstance(int columnCount, String category) {
        PostsFragment fragment = new PostsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, category);
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

}