package com.fbsum.android.adapterdelegates.sample.model;

/**
 * Created by sum on 5/10/16.
 */
public class ComplexItem implements Item {
    public int id;
    public int imageRes;
    public String content;

    public ComplexItem(int id, String content) {
        this.id = id;
        this.content = content;
    }
}
