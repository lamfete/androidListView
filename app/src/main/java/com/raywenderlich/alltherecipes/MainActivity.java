/*
 * Copyright (c) 2016 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.raywenderlich.alltherecipes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.content.Intent;
import android.content.Context;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mListView = (ListView) findViewById(R.id.recipe_list_view);

    //load a list of recipe objects from a json asset in the app.
    //the starter project contains Recipe class that models and stores the information about the recipes that will be displayed
    final ArrayList<Recipe> recipeList = Recipe.getRecipesFromFile("recipe.json", this);

    //create array of strings that'll contain the text to be displayed in the List View
    String[] listItems = new String[recipeList.size()];

    //looping ListView's data source with the titles of the recipe
    for(int i = 0; i < recipeList.size(); i++){
        Recipe recipe = recipeList.get(i);
        listItems[i] = recipe.title;
    }

    /* create and sets a simple adapter for the ListView. The ArrayAdapter takes in the current context (a layout file
     * specifying what each row in the list should look like) and the data that will populate the list as arguments.
     */
    ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
      mListView.setAdapter(adapter);
  }

}
