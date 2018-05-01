package com.raywenderlich.alltherecipes;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lamfete on 30-Apr-18.
 */

public class RecipeAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Recipe> mDataSource;

    private static final HashMap<String, Integer> LABEL_COLORS = new HashMap<String, Integer>() {{
        put("Low-Carb", R.color.colorLowCarb);
        put("Low-Fat", R.color.colorLowFat);
        put("Low-Sodium", R.color.colorLowSodium);
        put("Medium-Carb", R.color.colorMediumCarb);
        put("Vegetarian", R.color.colorVegetarian);
        put("Balanced", R.color.colorBalanced);
    }};

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

        /*
        // get title element
        TextView titleTextView = (TextView) rowView.findViewById(R.id.recipe_list_title);

        // get subtitle element
        TextView subtitleTextView = (TextView) rowView.findViewById(R.id.recipe_list_subtitle);

        // get detail element
        TextView detailTextView = (TextView) rowView.findViewById(R.id.recipe_list_detail);

        // get thumbnail element
        ImageView thumbnailImageView = (ImageView) rowView.findViewById(R.id.recipe_list_thumbnail);
        */

        ViewHolder holder;

        //
        if(convertView == null) {
            //
            convertView = mInflater.inflate(R.layout.list_item_recipe, parent, false);

            //
            holder = new ViewHolder();
            holder.thumbnailImageView = (ImageView) convertView.findViewById(R.id.recipe_list_thumbnail);
            holder.titleTextView = (TextView) convertView.findViewById(R.id.recipe_list_title);
            holder.subtitleTextView = (TextView) convertView.findViewById(R.id.recipe_list_subtitle);
            holder.detailTextView = (TextView) convertView.findViewById(R.id.recipe_list_detail);

            //
            convertView.setTag(holder);
        } else {
            //
            holder = (ViewHolder) convertView.getTag();
        }

        //
        TextView titleTextView = holder.titleTextView;
        TextView subtitleTextView = holder.subtitleTextView;
        TextView detailTextView = holder.detailTextView;
        ImageView thumbnailImageView = holder.thumbnailImageView;

        // getting the corresponding recipe for the current row
        Recipe recipe = (Recipe) getItem(position);

        // update the row view's text view so they are displaying the recipe
        titleTextView.setText(recipe.title);
        subtitleTextView.setText(recipe.description);
        detailTextView.setText(recipe.label);

        // use open source picasso lib for async image loading
        Picasso.with(mContext).load(recipe.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView);

        // styling
        Typeface titleTypeFace = Typeface.createFromAsset(mContext.getAssets(), "fonts/JosefinSans-Bold.ttf");
        titleTextView.setTypeface(titleTypeFace);

        Typeface subtitleTypeFace = Typeface.createFromAsset(mContext.getAssets(), "fonts/JosefinSans-SemiBoldItalic.ttf");
        subtitleTextView.setTypeface(subtitleTypeFace);

        Typeface detailTypeFace = Typeface.createFromAsset(mContext.getAssets(), "fonts/Quicksand-Bold.otf");
        detailTextView.setTypeface(detailTypeFace);

        detailTextView.setTextColor(ContextCompat.getColor(mContext, LABEL_COLORS.get(recipe.label)));

        return convertView;
    }

    private static class ViewHolder {
        public TextView titleTextView;
        public TextView subtitleTextView;
        public TextView detailTextView;
        public ImageView thumbnailImageView;
    }
}
