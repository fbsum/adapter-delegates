package com.fbsum.android.adapterdelegates;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class SimpleAdapterDelegate<T> extends AdapterDelegate<T> {

    @LayoutRes
    protected int layoutRes;
    protected OnViewClickListener onViewClickListener;

    public SimpleAdapterDelegate(Activity activity, @LayoutRes int layoutRes) {
        super(activity);
        this.layoutRes = layoutRes;
    }

    @Override
    protected final RecyclerView.ViewHolder onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup parent) {
        View itemView = layoutInflater.inflate(layoutRes, parent, false);
        final RecyclerView.ViewHolder viewHolder = onCreateViewHolder(itemView);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onViewClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    onViewClickListener.onClick(v, position);
                }
            }
        };
        itemView.setOnClickListener(onClickListener);
        return viewHolder;
    }

    @NonNull
    protected abstract RecyclerView.ViewHolder onCreateViewHolder(@NonNull View itemView);

    /**
     *
     */
    public interface OnViewClickListener {
        /**
         * @param view     clicked view
         * @param position the itemView's position
         */
        void onClick(View view, int position);
    }

    public void setOnViewClickListener(OnViewClickListener listener) {
        this.onViewClickListener = listener;
    }
}
