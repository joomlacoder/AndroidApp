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

import java.util.List;

import xyz.lob.referenceofcomputerscience.R;
import xyz.lob.referenceofcomputerscience.content.Content;

public class MyPostRecyclerViewAdapter extends RecyclerView.Adapter<MyPostRecyclerViewAdapter.ViewHolder> {

    private final List<Content.Post> mValues;
    private OnSelectedRecyclerItemListener recOnClicklistener;
    private Context context;

    public MyPostRecyclerViewAdapter(List<Content.Post> mValues, OnSelectedRecyclerItemListener recOnClicklistener, Context context) {
        this.mValues = mValues;
        this.recOnClicklistener = recOnClicklistener;
        this.context = context;
    }

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

    public void updateList(List<Content.Post> posts){
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
            titleView = (TextView) view.findViewById(R.id.itemCardTitle);
            detaleView = (TextView) view.findViewById(R.id.itemCardText);
            imageView =(ImageView)view.findViewById(R.id.itemCardLogo);
            view.setOnClickListener(this);
        }

        public void setData(Content.Post post){
            titleView.setText(post.getTitle());
            imageView.setImageResource(post.getImg());
            detaleView.setText(post.getDetails());
        }

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