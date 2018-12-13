package com.example.gding3.maindelivery;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.security.MessageDigest;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Login extends AppCompatActivity {

    OkHttpClient client;
    EditText username;
    EditText password;
    Button submitButton;
    public static String currentUser;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        client = new OkHttpClient();

        username = (EditText) findViewById(R.id.UsernameLogin);
        password = (EditText) findViewById(R.id.PasswordLogin);
        submitButton = (Button) findViewById(R.id.loginbutton);
        Button button = (Button) findViewById(R.id.registernow);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    final String hashedPassword = encyrpt(password.getText().toString());
                    Request request = new Request.Builder()
                            .url("http://foodcall.org/Login.php?username=\""+username.getText().toString()+"\"")
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            final String myResponce = response.body().string();
                            if (myResponce.equals(hashedPassword)) {
                                currentUser = username.getText().toString();
                                startActivity(new Intent(Login.this, MainMenuActivity.class));
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private String encyrpt(String s) throws Exception {
        String passwordToHash = s;
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwordToHash.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
