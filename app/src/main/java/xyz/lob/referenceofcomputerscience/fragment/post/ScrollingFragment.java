package xyz.lob.referenceofcomputerscience.fragment.post;

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
import xyz.lob.referenceofcomputerscience.content.model.Post;
import xyz.lob.referenceofcomputerscience.fragment.posts.PostsFragment;

public class ScrollingFragment extends Fragment {
    private String cat;
    private int id;
    private Post post;
    private App app;

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            id = getArguments().getInt(PostsFragment.ARG_ID);
            if(getArguments().containsKey(PostsFragment.ARG_CATEGORY));
                cat = getArguments().getString(PostsFragment.ARG_CATEGORY);
        }
        app = App.getInstance();
        post = app.getContent().getPost(id);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scrolling, container, false);

        Spanned textSpan = Html.fromHtml(post.getText(), HtmlCompat.FROM_HTML_MODE_LEGACY, source -> {
            Drawable drawFromPath;
            int path = getResources().getIdentifier(source, "drawable", getActivity().getApplicationContext().getPackageName());
            Log.e("src", path + " " + source);

            drawFromPath = getResources().getDrawable(path, getContext().getTheme());
            drawFromPath.setBounds(0, 0, drawFromPath.getIntrinsicWidth(),
                    drawFromPath.getIntrinsicHeight());

            int widthScreen = getActivity().getWindowManager().getDefaultDisplay().getWidth()-100;
            int width = drawFromPath.getIntrinsicWidth();
            int height = drawFromPath.getIntrinsicHeight();
            if (width > widthScreen){
                float sc = (float)widthScreen/width;
                height = (int) (height * sc);
                drawFromPath.setBounds(
                    0, 0,
                    widthScreen,
                    height);
            }
            return drawFromPath;
        }, null);

        getActivity().setTitle(post.getTitle());
        ((TextView) view.findViewById(R.id.scrolingTextView)).setText(textSpan);
        ((ImageView) view.findViewById(R.id.scrolingImageView)).setImageDrawable(post.getImg());
        ImageView image = (ImageView) view.findViewById(R.id.imageButton);

        if(post.getForever())
            image.setImageResource(R.drawable.ic_forever_on);
        else
            image.setImageResource(R.drawable.ic_forever_off);

        image.setOnClickListener(v -> {
            if (!post.getForever()) {
                app.addForever(post.getTitle());
                image.setImageResource(R.drawable.ic_forever_on);
            } else {
                app.removeForever(post.getTitle());
                image.setImageResource(R.drawable.ic_forever_off);
            }
            post.setForever(!post.getForever());
        });

        return view;
    }

    public static ScrollingFragment newInstance(String cat, int id) {
        Bundle bundle = new Bundle();
        bundle.putString(PostsFragment.ARG_CATEGORY, cat);
        bundle.putInt(PostsFragment.ARG_ID, id);
        ScrollingFragment fragment = new ScrollingFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

}