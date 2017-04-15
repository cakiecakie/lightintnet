package com.cakiecakie.lightintnet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    WebView webView;
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init view
        final TextView textView = (TextView) findViewById(R.id.title);
        Button add = (Button) findViewById(R.id.add);
        Button list = (Button) findViewById(R.id.list);
        add.setOnClickListener(this);
        list.setOnClickListener(this);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        webView = (WebView) findViewById(R.id.web_view);
        //支持JavaScript
        webView.getSettings().setJavaScriptEnabled(true);
        //打开新的url时，同样使用这个webview
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient() {
            //设置打开网页的进度条
            @Override
            public void onProgressChanged(WebView webView, int progress) {
                super.onProgressChanged(webView, progress);
                if (progress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    if (progressBar.getVisibility() == View.GONE) {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                    progressBar.setProgress(progress);
                }
            }
            //在上部的textView中显示当前网页加载的标题
            @Override
            public void onReceivedTitle(WebView webView, String title) {
                super.onReceivedTitle(webView, title);
                MainActivity.this.title = title;
                textView.setText(title);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        if(url != null && url.length() != 0) {
            webView.loadUrl(url);
        } else {
            webView.loadUrl(homeUrl);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                if (map.containsKey(title)) {
                    Toast.makeText(this, "已添加", Toast.LENGTH_SHORT).show();
                    break;
                }
                titleList.add(title);
                urlList.add(webView.getOriginalUrl());
                map.put(title, webView.getOriginalUrl());
                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.list:
                Intent intent = new Intent(MainActivity.this, UrlListActivity.class);
                startActivity(intent);
        }
    }
    //按下返回键，可让WebView返回到上一个网页
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        ActivityCollector.removeAll();
        return false;
    }

    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        editor.clear();
        StringBuilder titles = new StringBuilder();
        for(String title : titleList) {
            titles.append(title + "#");
        }
        editor.putString("titles", titles.toString().substring(0, titles.length() - 1));
        StringBuilder urls = new StringBuilder();
        for (String url : urlList) {
            urls.append(url + " ");
        }
        editor.putString("urls", urls.toString().trim());
        editor.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
