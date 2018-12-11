package com.example.gding3.maindelivery;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;


public class BrowseRecipesActivity extends Activity {
    GridView gridView;
    public static String[] names = {
            "Cupcake",
            "Muffin",
            "Breakfast Ideas"
    };
    public static int[] Images = {
            //R.mipmap.


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout_browse);
        gridView = findViewById(R.id.browseRecipesGridView);
        //gridView.setAdapter(new CustomGVAdapter(this, names, Images));
    }
}
