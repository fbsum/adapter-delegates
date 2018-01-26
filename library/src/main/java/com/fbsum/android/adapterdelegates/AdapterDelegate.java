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


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;


public abstract class AdapterDelegate<T> {

    protected LayoutInflater layoutInflater;

    public AdapterDelegate(Activity activity) {
        this.layoutInflater = LayoutInflater.from(activity);
    }

    public abstract boolean isForViewType(int position, @NonNull T item);

    @NonNull
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return onCreateViewHolder(layoutInflater, parent);
    }

    protected abstract RecyclerView.ViewHolder onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup parent);

    protected abstract void onBindViewHolder(@NonNull RecyclerView.ViewHolder vh,
                                             @NonNull T item,
                                             @NonNull List<Object> payloads);

    protected void onViewRecycled(@NonNull RecyclerView.ViewHolder vh) {
    }

    protected boolean onFailedToRecycleView(@NonNull RecyclerView.ViewHolder vh) {
        return false;
    }

    protected void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder vh) {
    }

    protected void onViewDetachedFromWindow(RecyclerView.ViewHolder vh) {
    }
}
