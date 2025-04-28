package com.example.qrcodegenerator;

import static androidx.appcompat.app.AlertDialog.*;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class OptionsActivity extends AppCompatActivity {
    Button scanqr,register;
    CameraManager cameraManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        getSupportActionBar().setTitle("Options Activity");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7AA8BD")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        scanqr=findViewById(R.id.scanqr);
        register=findViewById(R.id.register);
        scanqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
                Scan();


            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OptionsActivity.this, RegisterActivity.class);
                startActivity(intent);


            }
        });

    }
    public void Scan()
    {
        ScanOptions scanOptions=new ScanOptions();
        scanOptions.setPrompt("Flash On");
        scanOptions.setBeepEnabled(false);
        scanOptions.setOrientationLocked(true);
        scanOptions.setCaptureActivity(CaptureAct.class);
       Barlauncher.launch(scanOptions);

    }
    ActivityResultLauncher<ScanOptions> Barlauncher=registerForActivityResult(new ScanContract(),result->{
       if (result.getContents()!=null){

      String output=result.getContents();

        Intent i=new Intent(OptionsActivity.this,ResultActivity.class);
        i.putExtra("Result",output);
        startActivity(i);
       }

    });

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(OptionsActivity.this,MainActivity.class));
        return super.onOptionsItemSelected(item);

    }
}