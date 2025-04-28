package com.example.qrcodegenerator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.net.URI;

public class ScanQR extends AppCompatActivity {
    ImageView imageqr;
    Button gcode,share;
    Bitmap qrbitmap;
    Uri uri;
    String path;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);
        imageqr=findViewById(R.id.imageqr);
        gcode=findViewById(R.id.gcode);
        share=findViewById(R.id.share);

        getSupportActionBar().setTitle("QRcode");


        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#C2B2DF")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i=getIntent();
        String data=i.getStringExtra("Data");
        gcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GenerateQRcode(data);
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shareQRCode(qrbitmap);

            }
        });

        }


    private void GenerateQRcode(String data)

    {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitmap = multiFormatWriter.encode(data, BarcodeFormat.QR_CODE, 500, 500);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
             qrbitmap=barcodeEncoder.createBitmap(bitmap);
            imageqr.setImageBitmap(qrbitmap );

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private void shareQRCode(Bitmap qrbitmap) {
//        Drawable mDrawable = imageqr.getDrawable();
//    bitmap = ((BitmapDrawable) mDrawable).getBitmap();

        path = MediaStore.Images.Media.insertImage(getContentResolver(), qrbitmap, "QR Code", null);

        if(path!= null) {
             uri = Uri.parse(path);
        }

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpeg");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(shareIntent, "Share QR Code"));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(ScanQR.this, RegisterActivity.class));
        return super.onOptionsItemSelected(item);
    }
}