package xyz.lob.referenceofcomputerscience.ui.recycle.forever;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import xyz.lob.referenceofcomputerscience.App;
import xyz.lob.referenceofcomputerscience.R;
import xyz.lob.referenceofcomputerscience.content.model.Post;
import xyz.lob.referenceofcomputerscience.fragment.posts.PostsFragment;


public class ForeverRecyclerAdapter extends RecyclerView.Adapter<ForeverRecyclerAdapter.ViewHolder>{

    private List<Post> mValues;
    private NavController navController;

    @NotNull
    @Override
    public ForeverRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ForeverRecyclerAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forever, parent, false));
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(final ForeverRecyclerAdapter.ViewHolder holder, int position) {
        holder.setData(mValues.get(position));
    }

    public ForeverRecyclerAdapter(NavController navController) {
        this.navController = navController;
        mValues = App.getInstance().getContent().getForevers();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void updateList(List<Post> posts){
        mValues.clear();
        mValues.addAll(posts);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final View view;
        public final TextView titleView;
        public final ImageView imageView;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.view = view;
            titleView = view.findViewById(R.id.textView);
            imageView = view.findViewById(R.id.imageView);
            view.setOnClickListener(this);
        }

        public void setData(Post post){
            titleView.setText(post.getTitle());
            imageView.setImageDrawable(post.getImg());
        }


        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putInt(PostsFragment.ARG_ID, getAdapterPosition());
            navController.navigate(R.id.nav_scrollingFragment, bundle);
        }
    }
}
