package com.cakiecakie.lightintnet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UrlListActivity extends BaseActivity {
    UrlAdapter adapter;
    LinearLayout listLayout;
    boolean isDeleting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_list);

        adapter = new UrlAdapter(UrlListActivity.this,
                R.layout.url_item, titleList);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isDeleting) {
                    map.remove(titleList.get(position));
                    titleList.remove(position);
                    urlList.remove(position);
                    adapter.notifyDataSetChanged();
                    return;
                }
                String title = titleList.get(position);
                String url = map.get(title);
                Intent intent = new Intent(UrlListActivity.this, MainActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
        final Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDeleting) {
                    delete.setBackgroundColor(getResources().getColor(R.color.delete));
                    isDeleting = false;
                } else {
                    delete.setBackgroundColor(getResources().getColor(R.color.deleting));
                    isDeleting = true;
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
    }

}
