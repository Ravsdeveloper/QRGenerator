package com.example.qrcodegenerator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {
    TextView textview;
    String output;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);
        textview=findViewById(R.id.textview);
        getSupportActionBar().setTitle("Result Activity");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF9E9696")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i=getIntent();
        output = i.getStringExtra("Result");
        textview.setText(output);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(ResultActivity.this,OptionsActivity.class));
        return super.onOptionsItemSelected(item);
    }
}