package com.fbsum.android.adapterdelegates.sample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.fbsum.android.adapterdelegates.sample.model.ComplexItem;
import com.fbsum.android.adapterdelegates.sample.model.ContentItem;
import com.fbsum.android.adapterdelegates.sample.model.ImageItem;
import com.fbsum.android.adapterdelegates.sample.model.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        List<Item> items = new ArrayList<>();
        items.add(new ContentItem("Content 1"));
        items.add(new ImageItem());
        items.add(new ComplexItem("Content 2"));
        items.add(new ComplexItem("Content 3"));
        items.add(new ImageItem());
        items.add(new ContentItem("Content 4"));

        mainAdapter = new MainAdapter(this, items);
        recyclerView.setAdapter(mainAdapter);
    }
}
