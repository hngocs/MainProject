package com.hngocs.mainproject.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hngocs.mainproject.R;

public class MultiThreadingCategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_multi_threading_categories);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void openHandlerSendMessageActivity(View view) {
        Intent intent=new Intent(
                MultiThreadingCategoriesActivity.this,
                HandlerSendMessageActivity.class);
        startActivity(intent);
    }

    public void openHandlerPostMessageActivity(View view) {
        Intent intent=new Intent(
                MultiThreadingCategoriesActivity.this,
                HandlerPostMessageActivity.class);
        startActivity(intent);
    }
}