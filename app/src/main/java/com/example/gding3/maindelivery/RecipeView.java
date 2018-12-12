package com.example.gding3.maindelivery;

import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecipeView extends AppCompatActivity {

    OkHttpClient client;
    EditText username;
    EditText title;
    EditText recipetext;
    TextView view;
    Button submitButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final LinearLayout linearLayout = new LinearLayout(this);
        final ScrollView scroll = new ScrollView(this);
        scroll.addView(linearLayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(scroll);
        client = new OkHttpClient();



        title = (EditText) findViewById(R.id.titlerecipe);
        username = (EditText) findViewById(R.id.usernamerecipe);
        recipetext = (EditText) findViewById(R.id.submitrecipetext);
        submitButton = (Button) findViewById(R.id.submitrecipe);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String url = "http://24.15.230.170/GetTitles.php";

        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                final String x = e.toString();
                RecipeView.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.setText(x);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonData = response.body().string();

                    try {
                        JSONArray array = new JSONArray(jsonData);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject row = array.getJSONObject(i);
                            final String recipe = row.getString("recipetext");
                            final String name = row.getString("title");
                            final LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            lp.setMargins(25,25,25,25);
                            RecipeView.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    final LinearLayout test = new LinearLayout(RecipeView.this);
                                    test.setOrientation(LinearLayout.VERTICAL);
                                    test.setLayoutParams(lp);
                                    TextView title = new TextView(RecipeView.this);
                                    title.setText(name);
                                    title.setTextSize(35);
                                    test.addView(title);
                                    TextView recipeText = new TextView(RecipeView.this);
                                    recipeText.setText(recipe);
                                    recipeText.setTextSize(20);
                                    test.addView(recipeText);
                                    linearLayout.addView(test);

                                }
                            });
                        }
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}
