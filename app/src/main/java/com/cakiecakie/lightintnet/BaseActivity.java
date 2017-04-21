package com.cakiecakie.lightintnet;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by naehokushin on 17/4/14.
 */

public class BaseActivity extends Activity {
    protected static List<String> urlList = new ArrayList<>();
    protected static List<String> titleList = new ArrayList<>();
    protected static Map<String, String> map = new HashMap<>();

    protected String homeUrl = "https://www.sogou.com";

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        ActionBar actionBar = getActionBar();
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
