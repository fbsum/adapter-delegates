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
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public abstract class DelegationAdapter<T> extends ListAdapter<T, RecyclerView.ViewHolder> {

    private AdapterDelegatesManager<T> delegatesManager = new AdapterDelegatesManager<>();
    protected List<T> items;

    protected DelegationAdapter(@NonNull DiffUtil.ItemCallback<T> diffCallback) {
        super(diffCallback);
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public List<T> getItems() {
        return items;
    }

    public void submitList() {
        super.submitList(items);
    }

    protected void addDelegate(AdapterDelegate<T> delegate) {
        delegatesManager.addDelegate(delegate);
    }

    @Override
    public int getItemViewType(int position) {
        return delegatesManager.getItemViewType(items, position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return delegatesManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vh, int position) {
        delegatesManager.onBindViewHolder(vh, items.get(position), null);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vh, int position, @NonNull List<Object> payloads) {
        delegatesManager.onBindViewHolder(vh, items.get(position), payloads);
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder vh) {
        delegatesManager.onViewRecycled(vh);
    }

    @Override
    public boolean onFailedToRecycleView(@NonNull RecyclerView.ViewHolder vh) {
        return delegatesManager.onFailedToRecycleView(vh);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder vh) {
        delegatesManager.onViewAttachedToWindow(vh);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder vh) {
        delegatesManager.onViewDetachedFromWindow(vh);
    }
}
