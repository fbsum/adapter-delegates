package com.fbsum.android.adapterdelegates.sample;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fbsum.android.adapterdelegates.SimpleAdapterDelegate;
import com.fbsum.android.adapterdelegates.sample.model.ComplexItem;
import com.fbsum.android.adapterdelegates.sample.model.Item;

import java.util.List;

/**
 * Created by sum on 5/10/16.
 */
public class ComplexDelegate extends SimpleAdapterDelegate<Item> {

    public ComplexDelegate(Activity activity) {
        super(activity, R.layout.layout_complex);
    }

    @Override
    public boolean isForViewType(int position, @NonNull Item item) {
        return item instanceof ComplexItem;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull View itemView) {
        final ComplexViewHolder viewHolder = new ComplexViewHolder(itemView);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onViewClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    onViewClickListener.onClick(v, position);
                }
            }
        };
        viewHolder.tvContent.setOnClickListener(onClickListener);
        viewHolder.ivIcon.setOnClickListener(onClickListener);
        return viewHolder;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull Item item, @NonNull List<Object> payloads) {
        ComplexViewHolder viewHolder = (ComplexViewHolder) holder;
        ComplexItem complexItem = (ComplexItem) item;

        viewHolder.tvContent.setText(complexItem.content != null ? complexItem.content : "Hello World!!!");
        viewHolder.ivIcon.setImageResource(complexItem.imageRes != 0 ? complexItem.imageRes : R.mipmap.ic_launcher);
    }

    private static class ComplexViewHolder extends RecyclerView.ViewHolder {

        private TextView tvContent;
        private ImageView ivIcon;

        ComplexViewHolder(View itemView) {
            super(itemView);

            tvContent = (TextView) itemView.findViewById(R.id.tv_main_item_content);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_main_item_icon);
        }
    }
}
