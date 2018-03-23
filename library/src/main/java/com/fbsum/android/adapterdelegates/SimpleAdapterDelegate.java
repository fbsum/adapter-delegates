package com.fbsum.android.adapterdelegates;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class SimpleAdapterDelegate<T> extends AdapterDelegate<T> {

    protected LayoutInflater layoutInflater;
    protected int layoutRes;
    protected OnViewClickListener onViewClickListener;

    public SimpleAdapterDelegate(Activity activity, @LayoutRes int layoutRes) {
        this.layoutInflater = activity.getLayoutInflater();
        this.layoutRes = layoutRes;
    }

    @NonNull
    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
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
     * On view click listener
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
