package xyz.lob.referenceofcomputerscience.ui.post;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;

import xyz.lob.referenceofcomputerscience.R;
import xyz.lob.referenceofcomputerscience.content.Content;

public class ScrollingFragment extends Fragment {
    private String cat;
    private int id;
    private Content.Post post;
//    private ScrolingFragmentModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            id = getArguments().getInt("id");
            cat = getArguments().getString("category");
            post = Content.getPost(cat, id);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        mViewModel = new ViewModelProvider(this, new ScrolingModelFactory(getActivity().getApplication(), savedInstanceState.getString("category"), savedInstanceState.getInt("id"))).get(ScrolingFragmentModel.class);
        View view = inflater.inflate(R.layout.fragment_scrolling, container, false);
//        Spanned textSpan = Html.fromHtml(getContext().getString(R.string.large_text), HtmlCompat.FROM_HTML_MODE_LEGACY, new Html.ImageGetter() {
//            @Override public Drawable getDrawable(String source) {
//                Drawable drawFromPath;
//                int path =
//                        getActivity().getResources().getIdentifier(source, "drawable",
//                                getActivity().getApplicationContext().getPackageName());
//                drawFromPath = getActivity().getResources().getDrawable(path);
//                drawFromPath.setBounds(0, 0, drawFromPath.getIntrinsicWidth(),
//                        drawFromPath.getIntrinsicHeight());
//                return drawFromPath;
//            }
//        }, null);
        Spanned textSpan = Html.fromHtml(getResources().getString(post.getContent()), HtmlCompat.FROM_HTML_MODE_LEGACY, new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                Drawable drawFromPath;
                int path =
                        getActivity().getResources().getIdentifier(source, "drawable",
                                getActivity().getApplicationContext().getPackageName());
                drawFromPath = getActivity().getResources().getDrawable(path);
                drawFromPath.setBounds(0, 0, drawFromPath.getIntrinsicWidth(),
                        drawFromPath.getIntrinsicHeight());
                return drawFromPath;
            }
        }, null);

        ((TextView) view.findViewById(R.id.scrolingTextView)).setText(textSpan);
        ((ImageView) view.findViewById(R.id.scrolingImageView)).setImageResource(post.getImg());
        return view;
    }

    public static ScrollingFragment newInstance(String cat, int id) {
        Bundle bundle = new Bundle();
        bundle.putString("category", cat);
        bundle.putInt("id", id);
        ScrollingFragment fragment = new ScrollingFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

}