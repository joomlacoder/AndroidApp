package xyz.lob.referenceofcomputerscience.ui.recycle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import xyz.lob.referenceofcomputerscience.R;
import xyz.lob.referenceofcomputerscience.content.Post;

public class MyPostRecyclerViewAdapter extends RecyclerView.Adapter<MyPostRecyclerViewAdapter.ViewHolder> {

    private final List<Post> mValues;
    private final OnSelectedRecyclerItemListener recOnClicklistener;

    public MyPostRecyclerViewAdapter(List<Post> mValues, OnSelectedRecyclerItemListener recOnClicklistener, Context context) {
        this.mValues = mValues;
        this.recOnClicklistener = recOnClicklistener;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false));
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.setData(mValues.get(position));
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
        public final TextView detaleView;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.view = view;
            titleView = view.findViewById(R.id.itemCardTitle);
            detaleView = view.findViewById(R.id.itemCardText);
            imageView = view.findViewById(R.id.itemCardLogo);
            view.setOnClickListener(this);
        }

        public void setData(Post post){
            titleView.setText(post.getTitle());
            imageView.setImageDrawable(post.getImg());
            detaleView.setText(post.getDetails());
        }

        @NotNull
        @Override
        public String toString() {
            return super.toString() + " '" + detaleView.getText() + "'";
        }

        @Override
        public void onClick(View v) {
             recOnClicklistener.onSelectedRecyclerItem(getAdapterPosition());
        }
    }
}