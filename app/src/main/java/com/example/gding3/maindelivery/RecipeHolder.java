package com.example.gding3.maindelivery;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
public class RecipeHolder extends RecyclerView.ViewHolder{
    private static final String TAG = RecipeHolder.class.getSimpleName();
    public TextView recipeName;
    public ImageView recipeImage;
    public RecipeHolder(View itemView) {
        super(itemView);
        recipeName = itemView.findViewById(R.id.recipe_name);
        recipeImage = itemView.findViewById(R.id.recipe_image);
    }
}
