package com.fbsum.android.adapterdelegates.sample;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fbsum.android.adapterdelegates.SimpleAdapterDelegate;
import com.fbsum.android.adapterdelegates.sample.model.ContentItem;
import com.fbsum.android.adapterdelegates.sample.model.Item;

import java.util.List;

/**
 * Created by sum on 5/10/16.
 */
public class ContentDelegate extends SimpleAdapterDelegate<Item> {

    public ContentDelegate(Activity activity) {
        super(activity, R.layout.layout_content);
    }

    @Override
    public boolean isForViewType(int position, @NonNull Item item) {
        return item instanceof ContentItem;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull View itemView) {
        final ViewHolder viewHolder = new ViewHolder(itemView);
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
        return viewHolder;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull Item item, @NonNull List<Object> payloads) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        ContentItem contentItem = (ContentItem) item;

        viewHolder.tvContent.setText(contentItem.content != null ? contentItem.content : "Hello World!!!");
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvContent;

        ViewHolder(View itemView) {
            super(itemView);

            tvContent = (TextView) itemView.findViewById(R.id.tv_main_item_content);
        }
    }

}
