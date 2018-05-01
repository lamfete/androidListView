package com.raywenderlich.alltherecipes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class RecipeDetailActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        // retrieve the recipe data from the intent passed by MainActivity by using getExtras() method
        String title = this.getIntent().getExtras().getString("title");
        String url = this.getIntent().getExtras().getString("url");

        // set the title on the action bar of this activity to the recipe title
        setTitle(title);

        //intialize mWebView to the web view defined in the xml layout
        mWebView = (WebView) findViewById(R.id.detail_web_view);

        // load the recipe web page by calling loadUrl() with teh corresponding recipe's url on the web view object
        mWebView.loadUrl(url);
    }
}
