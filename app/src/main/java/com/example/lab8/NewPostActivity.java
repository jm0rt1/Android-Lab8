package com.example.lab8;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);


        setSupportActionBar(findViewById(R.id.custom_toolbar));
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addNewPost(View v){

        EditText titleEditText =  findViewById(R.id.post_title);
        EditText contentEditText = findViewById(R.id.post_content);
        ServerInterface.Posts.sendPost(1,
                titleEditText.getText().toString(),
                contentEditText.getText().toString(),
                getApplicationContext());

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

}