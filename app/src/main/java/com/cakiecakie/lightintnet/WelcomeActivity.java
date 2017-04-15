package com.cakiecakie.lightintnet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        String titles = pref.getString("titles", "");
        String urls = pref.getString("urls", "");

        String[] titlesArray = null;
        String[] urlsArray = null;
        if (! titles.equals("") && ! urls.equals("")) {
            titlesArray = titles.split("#");//title之间用#分割
            urlsArray = urls.split(" ");//url之间用空格做分割，因为url中不会有空格
        }
        if (titlesArray != null) {
            titleList = new ArrayList<>();
            urlList = new ArrayList<>();
            map = new HashMap<>();
            for (int i = 0; i < titlesArray.length; i++) {
                titleList.add(titlesArray[i]);
                urlList.add(urlsArray[i]);
                map.put(titlesArray[i], urlsArray[i]);
            }
        }
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        intent.putExtra("url", homeUrl);
        startActivity(intent);
    }
}
