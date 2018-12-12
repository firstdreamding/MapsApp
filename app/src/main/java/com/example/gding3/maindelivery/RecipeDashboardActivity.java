package com.example.gding3.maindelivery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RecipeDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button nextButton = (Button) findViewById(R.id.addRecipeButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RecipeDashboardActivity.this, RecipeSend.class));
            }
        });

        Button nextButton2 = (Button) findViewById(R.id.browserecipes);
        nextButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RecipeDashboardActivity.this, RecipeView.class));
            }
        });
    }
}
