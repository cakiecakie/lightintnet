package com.cakiecakie.lightintnet;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by naehokushin on 17/4/14.
 */

public class BaseActivity extends AppCompatActivity {
    protected static List<String> urlList = new ArrayList<>();
    protected static List<String> titleList = new ArrayList<>();
    protected static Map<String, String> map = new HashMap<>();

    protected String homeUrl = "https://www.sogou.com";

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        ActivityCollector.add(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
