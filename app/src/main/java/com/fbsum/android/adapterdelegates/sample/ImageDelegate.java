package com.fbsum.android.adapterdelegates.sample;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.fbsum.android.adapterdelegates.SimpleAdapterDelegate;
import com.fbsum.android.adapterdelegates.sample.model.ImageItem;
import com.fbsum.android.adapterdelegates.sample.model.Item;

import java.util.List;

/**
 * Created by sum on 5/10/16.
 */
public class ImageDelegate extends SimpleAdapterDelegate<Item> {

    public ImageDelegate(Activity activity) {
        super(activity, R.layout.layout_image);
    }

    @Override
    public boolean isForViewType(@NonNull Item item) {
        return item instanceof ImageItem;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull Item item, @NonNull List<Object> payloads) {
        ViewHolder viewHolder = (ViewHolder) holder;
        ImageItem imageItem = (ImageItem) item;

        viewHolder.ivIcon.setImageResource(imageItem.imageRes != 0 ? imageItem.imageRes : R.mipmap.ic_launcher);
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivIcon;

        ViewHolder(View itemView) {
            super(itemView);

            ivIcon = (ImageView) itemView.findViewById(R.id.iv_main_item_icon);
        }
    }

}
