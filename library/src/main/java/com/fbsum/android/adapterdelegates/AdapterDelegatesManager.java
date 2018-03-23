/*
 * Copyright (c) 2015 Hannes Dorfmann.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.fbsum.android.adapterdelegates;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;


class AdapterDelegatesManager<T> {

    private static final List<Object> PAYLOADS_EMPTY_LIST = Collections.emptyList();
    private SparseArrayCompat<AdapterDelegate<T>> delegates = new SparseArrayCompat<>();

    AdapterDelegatesManager<T> addDelegate(@NonNull AdapterDelegate<T> delegate) {
        int itemViewType = delegates.size();
        delegates.put(itemViewType, delegate);
        return this;
    }

    int getItemViewType(List<T> items, int position) {
        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++) {
            AdapterDelegate<T> delegate = delegates.valueAt(i);
            if (delegate.isForViewType(position, items.get(position))) {
                return delegates.keyAt(i);
            }
        }
        throw new IllegalArgumentException("No AdapterDelegate added that matches position =" + position + " in data source");
    }

    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterDelegate<T> delegate = delegates.get(viewType);
        if (delegate == null) {
            throw new NullPointerException("No AdapterDelegate added for ViewType " + viewType);
        }
        return delegate.onCreateViewHolder(parent);
    }

    void onBindViewHolder(RecyclerView.ViewHolder viewHolder, T item, List<Object> payloads) {
        AdapterDelegate<T> delegate = delegates.get(viewHolder.getItemViewType());
        if (delegate == null) {
            throw new NullPointerException("No AdapterDelegate added for ViewType " + viewHolder.getItemViewType());
        }
        payloads = payloads != null ? payloads : PAYLOADS_EMPTY_LIST;
        delegate.onBindViewHolder(viewHolder, item, payloads);
    }

    public void onViewRecycled(@NonNull RecyclerView.ViewHolder viewHolder) {
        AdapterDelegate<T> delegate = delegates.get(viewHolder.getItemViewType());
        if (delegate == null) {
            throw new NullPointerException("No delegate found for "
                    + viewHolder
                    + " for item at position = "
                    + viewHolder.getAdapterPosition()
                    + " for viewType = "
                    + viewHolder.getItemViewType());
        }
        delegate.onViewRecycled(viewHolder);
    }

    public boolean onFailedToRecycleView(@NonNull RecyclerView.ViewHolder viewHolder) {
        AdapterDelegate<T> delegate = delegates.get(viewHolder.getItemViewType());
        if (delegate == null) {
            throw new NullPointerException("No delegate found for "
                    + viewHolder
                    + " for item at position = "
                    + viewHolder.getAdapterPosition()
                    + " for viewType = "
                    + viewHolder.getItemViewType());
        }
        return delegate.onFailedToRecycleView(viewHolder);
    }

    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        AdapterDelegate<T> delegate = delegates.get(viewHolder.getItemViewType());
        if (delegate == null) {
            throw new NullPointerException("No delegate found for "
                    + viewHolder
                    + " for item at position = "
                    + viewHolder.getAdapterPosition()
                    + " for viewType = "
                    + viewHolder.getItemViewType());
        }
        delegate.onViewAttachedToWindow(viewHolder);
    }

    public void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
        AdapterDelegate<T> delegate = delegates.get(viewHolder.getItemViewType());
        if (delegate == null) {
            throw new NullPointerException("No delegate found for "
                    + viewHolder
                    + " for item at position = "
                    + viewHolder.getAdapterPosition()
                    + " for viewType = "
                    + viewHolder.getItemViewType());
        }
        delegate.onViewDetachedFromWindow(viewHolder);
    }
}
