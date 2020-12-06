package xyz.lob.referenceofcomputerscience.ui.post;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;

import xyz.lob.referenceofcomputerscience.App;
import xyz.lob.referenceofcomputerscience.R;

public class ScrollingFragment extends Fragment {
    private String cat;
    private int id;

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            id = getArguments().getInt("id");
            cat = getArguments().getString("category");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scrolling, container, false);
        Spanned textSpan = Html.fromHtml(App.getInstance().getContent().getPost(cat,id).getText(), HtmlCompat.FROM_HTML_MODE_LEGACY, source -> {
            Drawable drawFromPath;
            int path =
                    getActivity().getResources().getIdentifier(source, "drawable",
                            getActivity().getApplicationContext().getPackageName());
            drawFromPath = getActivity().getResources().getDrawable(path, getContext().getTheme());
            drawFromPath.setBounds(0, 0, drawFromPath.getIntrinsicWidth(),
                    drawFromPath.getIntrinsicHeight());
            return drawFromPath;
        }, null);
        Log.e("test", "go" + cat + " " + id);
        ((TextView) view.findViewById(R.id.scrolingTextView)).setText(textSpan);
        ((ImageView) view.findViewById(R.id.scrolingImageView)).setImageDrawable(App.getInstance().getContent().getPost(cat,id).getImg());

        getActivity().setTitle(App.getInstance().getContent().getPost(id).title);
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