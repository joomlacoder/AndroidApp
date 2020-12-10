package xyz.lob.referenceofcomputerscience.ui.recycle.search;

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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    private List<Post> mValues;
    private NavController navController;


    @NotNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(mValues.get(position));
    }

    public SearchAdapter(NavController navController) {
        this.navController = navController;
        mValues = App.getInstance().getContent().getAllPost();
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
        public View view;
        public TextView titleView;
        public ImageView imageView;
        public TextView detaleView;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.view = view;
            titleView = view.findViewById(R.id.itemCardTitle);
            imageView = view.findViewById(R.id.itemCardLogo);
            detaleView = view.findViewById(R.id.itemCardText);
            view.setOnClickListener(this);
        }

        public void setData(Post post){
            titleView.setText(post.getTitle());
            imageView.setImageDrawable(post.getImg());
            detaleView.setText(post.getDetails());
        }


        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putInt(PostsFragment.ARG_ID, getAdapterPosition());
            navController.navigate(R.id.nav_scrollingFragment, bundle);
        }
    }
}
