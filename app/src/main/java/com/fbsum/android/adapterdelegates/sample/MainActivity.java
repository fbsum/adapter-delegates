package com.fbsum.android.adapterdelegates.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

        findViewById(R.id.testBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        mainAdapter = new MainAdapter(this);
        recyclerView.setAdapter(mainAdapter);

        List<Item> items = new ArrayList<>();
        items.add(new ContentItem(1,"Content 1"));
        items.add(new ImageItem(2));
        items.add(new ComplexItem(3,"Content 2"));
        items.add(new ComplexItem(4,"Content 3"));
        items.add(new ImageItem(5));
        items.add(new ContentItem(6,"Content 4"));
        mainAdapter.setItems(items);
        mainAdapter.submitList();
    }
}
