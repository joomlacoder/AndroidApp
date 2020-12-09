package xyz.lob.referenceofcomputerscience.fragment.post;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;

import java.util.LinkedHashSet;
import java.util.Set;

import xyz.lob.referenceofcomputerscience.App;
import xyz.lob.referenceofcomputerscience.R;
import xyz.lob.referenceofcomputerscience.content.model.Post;

public class ScrollingFragment extends Fragment {
    private String cat;
    private int id;
    private boolean isForever;
    private Post post;
    private SharedPreferences preferences;

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            id = getArguments().getInt("id");
            cat = getArguments().getString("category");
        }
        post = App.getInstance().getContent().getPost(cat, id);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        preferences = App.getPreferences();
        View view = inflater.inflate(R.layout.fragment_scrolling, container, false);

        Spanned textSpan = Html.fromHtml(post.getText(), HtmlCompat.FROM_HTML_MODE_LEGACY, source -> {
            Drawable drawFromPath;
            int path =
                    getActivity().getResources().getIdentifier(source, "drawable",
                            getActivity().getApplicationContext().getPackageName());
            drawFromPath = getActivity().getResources().getDrawable(path, getContext().getTheme());
            drawFromPath.setBounds(0, 0, drawFromPath.getIntrinsicWidth(),
                    drawFromPath.getIntrinsicHeight());
            return drawFromPath;
        }, null);

        getActivity().setTitle(post.getTitle());
        ((TextView) view.findViewById(R.id.scrolingTextView)).setText(textSpan);
        ((ImageView) view.findViewById(R.id.scrolingImageView)).setImageDrawable(post.getImg());
        ImageView image = (ImageView) view.findViewById(R.id.imageButton);

        if(preferences.getStringSet(App.APP_PREFERENCES_FOREVER, new ArraySet<>()).contains(post.getTitle())) {
            isForever = true;
            image.setImageResource(R.drawable.ic_forever_on);
        } else {
            isForever = false;
            image.setImageResource(R.drawable.ic_forever_off);
        }

        image.setOnClickListener(v -> {
            SharedPreferences.Editor editor = preferences.edit();
            Set<String> stringForever = new LinkedHashSet<>(preferences.getStringSet(App.APP_PREFERENCES_FOREVER, new ArraySet<>()));
            if (!isForever) {
                stringForever.add(getActivity().getTitle().toString());
                image.setImageResource(R.drawable.ic_forever_on);
            } else {
                stringForever.remove(getActivity().getTitle().toString());
                image.setImageResource(R.drawable.ic_forever_off);
            }
            isForever = !isForever;
            editor.putStringSet(App.APP_PREFERENCES_FOREVER, stringForever);
            editor.apply();
        });

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