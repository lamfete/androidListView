package com.raywenderlich.alltherecipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by lamfete on 30-Apr-18.
 */

public class RecipeAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Recipe> mDataSource;

    public RecipeAdapter(Context context, ArrayList<Recipe> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // return the size of the data source
    @Override
    public int getCount() {
        return mDataSource.size();
    }

    /* returns an item to be placed in a given position from the data source, specifically, Recipe
     * object obtained from mDataSource
     */
    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    /* defines a unique ID for each row in the list.
     * use the position of the items as its ID
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /*
     * create a view to be used as a row in the list.
     * define what information shows and where it sits within the ListView.
     * also inflate a custom view from the XML layout.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Get view from row item
        View rowView = mInflater.inflate(R.layout.list_item_recipe, parent, false);

        // get title element
        TextView titleTextView = (TextView) rowView.findViewById(R.id.recipe_list_title);

        // get subtitle element
        TextView subtitleTextView = (TextView) rowView.findViewById(R.id.recipe_list_subtitle);

        // get detail element
        TextView detailTextView = (TextView) rowView.findViewById(R.id.recipe_list_detail);

        // get thumbnail element
        ImageView thumbnailImageView = (ImageView) rowView.findViewById(R.id.recipe_list_thumbnail);

        // getting the corresponding recipe for the current row
        Recipe recipe = (Recipe) getItem(position);

        // update the row view's text view so they are displaying the recipe
        titleTextView.setText(recipe.title);
        subtitleTextView.setText(recipe.description);
        detailTextView.setText(recipe.label);

        // use open source picasso lib for async image loading
        Picasso.with(mContext).load(recipe.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView);

        return rowView;
    }
}
