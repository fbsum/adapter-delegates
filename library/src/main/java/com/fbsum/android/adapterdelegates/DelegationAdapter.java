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

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class DelegationAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private AdapterDelegatesManager<T> delegatesManager = new AdapterDelegatesManager<>();
    protected List<T> items;

    protected void addDelegate(AdapterDelegate<T> delegate) {
        delegatesManager.addDelegate(delegate);
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return delegatesManager.getItemViewType(items, position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegatesManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vh, int position) {
        delegatesManager.onBindViewHolder(vh, items.get(position), null);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vh, int position, List<Object> payloads) {
        delegatesManager.onBindViewHolder(vh, items.get(position), payloads);
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder vh) {
        delegatesManager.onViewRecycled(vh);
    }

    @Override
    public boolean onFailedToRecycleView(RecyclerView.ViewHolder vh) {
        return delegatesManager.onFailedToRecycleView(vh);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder vh) {
        delegatesManager.onViewAttachedToWindow(vh);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder vh) {
        delegatesManager.onViewDetachedFromWindow(vh);
    }
}
