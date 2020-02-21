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

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.collection.SparseArrayCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;


class AdapterDelegatesManager<T> {

    /**
     * empty payload parameter
     */
    private static final List<Object> PAYLOADS_EMPTY_LIST = Collections.emptyList();

    /**
     * Map for ViewType to AdapterDelegate
     */
    private SparseArrayCompat<AdapterDelegate<T>> delegates = new SparseArrayCompat<>();

    /**
     * Adds an {@link AdapterDelegate}.
     * <b>This method automatically assign internally the view type integer by using the next unused</b>
     *
     * @param delegate the delegate to add
     * @return self
     */
    AdapterDelegatesManager<T> addDelegate(@NonNull AdapterDelegate<T> delegate) {
        // algorithm could be improved since there could be holes,
        // but it's very unlikely that we reach Integer.MAX_VALUE and run out of unused indexes
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

    /**
     * Must be called from {@link RecyclerView.Adapter#onViewRecycled(RecyclerView.ViewHolder)}
     *
     * @param viewHolder The ViewHolder for the view being recycled
     */
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

    /**
     * Must be called from {@link RecyclerView.Adapter#onFailedToRecycleView(RecyclerView.ViewHolder)}
     *
     * @param viewHolder The ViewHolder containing the View that could not be recycled due to its
     *                   transient state.
     * @return True if the View should be recycled, false otherwise. Note that if this method
     * returns <code>true</code>, RecyclerView <em>will ignore</em> the transient state of
     * the View and recycle it regardless. If this method returns <code>false</code>,
     * RecyclerView will check the View's transient state again before giving a final decision.
     * Default implementation returns false.
     */
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

    /**
     * Must be called from {@link RecyclerView.Adapter#onViewAttachedToWindow(RecyclerView.ViewHolder)}
     *
     * @param viewHolder Holder of the view being attached
     */
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

    /**
     * Must be called from {@link RecyclerView.Adapter#onViewDetachedFromWindow(RecyclerView.ViewHolder)}
     *
     * @param viewHolder Holder of the view being attached
     */
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
