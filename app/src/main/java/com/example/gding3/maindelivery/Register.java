package com.example.gding3.maindelivery;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Register extends AppCompatActivity {

    OkHttpClient client;
    EditText username;
    EditText password;
    Button submitButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        client = new OkHttpClient();

        username = (EditText) findViewById(R.id.RegisterUsername);
        password = (EditText) findViewById(R.id.RegisterPassword);
        submitButton = (Button) findViewById(R.id.RegisterButton);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String hashedPassword = encyrpt(password.getText().toString());
                    Request request = new Request.Builder()
                            .url("http://foodcall.org/Register.php?username=\""+username.getText().toString()+"\"&passhash=\"" + hashedPassword + "\"")
                            .build();

                    Response response = client.newCall(request).execute();
                    System.out.println(response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                finish();
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
