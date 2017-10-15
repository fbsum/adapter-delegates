package com.fbsum.android.adapterdelegates.sample.model;

/**
 * Created by sum on 5/10/16.
 */
public class ComplexItem implements Item {
    public int imageRes;
    public String content;

    public ComplexItem() {
    }

    public ComplexItem(String content) {
        this.content = content;
    }
}
