package com.cakiecakie.lightintnet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UrlListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_list);

        UrlAdapter adapter = new UrlAdapter(UrlListActivity.this,
                R.layout.url_item, titleList);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title = titleList.get(position);
                String url = map.get(title);
                Intent intent = new Intent(UrlListActivity.this, MainActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
    }
}
