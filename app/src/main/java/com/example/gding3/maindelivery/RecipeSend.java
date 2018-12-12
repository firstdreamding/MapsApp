package com.example.gding3.maindelivery;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RecipeSend extends AppCompatActivity {

    OkHttpClient client;
    EditText username;
    EditText title;
    EditText recipetext;
    Button submitButton;
    private boolean isReached = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_send);
        client = new OkHttpClient();



        title = (EditText) findViewById(R.id.titlerecipe);
        username = (EditText) findViewById(R.id.usernamerecipe);
        recipetext = (EditText) findViewById(R.id.submitrecipetext);
        submitButton = (Button) findViewById(R.id.submitrecipe);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request request = new Request.Builder()
                        .url("http://24.15.230.170/AddRecipe.php?authorid="+username.getText().toString().hashCode()+"&title=\"" + title.getText().toString() + "\"&recipetext=\"" + recipetext.getText().toString() + "\"")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    System.out.println(response.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                finish();
            }
        });

    }
}
