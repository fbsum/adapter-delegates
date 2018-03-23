package com.fbsum.android.adapterdelegates.sample.model;

/**
 * Created by sum on 5/10/16.
 */
public class ContentItem implements Item {
    public int id;
    public String content;

    public ContentItem(int id, String content) {
        this.id = id;
        this.content = content;
    }
}
