package com.example.qrcodegenerator;

import static android.app.PendingIntent.getActivity;
import static android.provider.Contacts.SettingsColumns.KEY;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.BitmapKt;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Locale;
import java.util.jar.Attributes;

public class RegisterActivity extends AppCompatActivity {
 String KEY_DATA  ;
    String PREF_NAME  ;
    String value;
    EditText edt1, edt2, edt3, edt4,edt5;
    Button subbtn;
    RadioButton radiobutton;
    RadioGroup radiogroup ;
    ImageView imageqr;
    Context ctx;
    Calendar myCalendar= Calendar.getInstance();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Register Activity");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#989595")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        edt3 = findViewById(R.id.edt3);
        edt4 = findViewById(R.id.edt4);
        edt5 = findViewById(R.id.edt5);
        subbtn = findViewById(R.id.subbtn);
        imageqr = findViewById(R.id.imageqr);
        radiogroup = findViewById(R.id.radiogroup);

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };

        edt5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                new DatePickerDialog(RegisterActivity.this,date,myCalendar.get(Calendar.DAY_OF_MONTH),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.YEAR)).show();
            }
        });


        subbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Gender = radiogroup();
                String Name = edt1.getText().toString().trim();
                String Mobile = edt2.getText().toString().trim();
                String city = edt3.getText().toString().trim();
                String Education = edt4.getText().toString().trim();
                String Date=edt5.getText().toString();
                String data = "Name:"+ Name  + "\nMobile:" +Mobile + "\ncity:"+ city + "\nEducation:"+Education +"\nGender:"+ Gender +"\nDate:"+ Date;
                if (Name.isEmpty() || city.isEmpty() || Education.isEmpty() || Mobile.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Required All fields", Toast.LENGTH_SHORT).show();
                    if (isvalidMobile(Mobile)) {
                        Toast.makeText(RegisterActivity.this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Enter 10 digit Mobile NO.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                }
                saveData(data);
                edt1.setText("");
                edt2.setText("");
                edt3.setText("");
                edt4.setText("");
                Intent i = new Intent(RegisterActivity.this, ScanQR.class);
                i.putExtra("Data", data);
                startActivity(i);
            }
        });
    }

    public boolean isvalidMobile(String mobile) {
        return Patterns.PHONE.matcher(mobile).matches() && mobile.length() == 10;
    }
    public void saveData(String data){
        SharedPreferences sharedPreferences=getSharedPreferences(PREF_NAME,MODE_PRIVATE);
      SharedPreferences.Editor editor=sharedPreferences.edit();
      editor.putString(KEY_DATA,data);
      editor.apply();

    }

    public String radiogroup(){
        String result="";

        int value= radiogroup.getCheckedRadioButtonId();
        radiobutton=findViewById(value);

        if(value!=-1){
            radiobutton=findViewById(value);
           result=radiobutton.getText().toString();
        }
        return result;

    }
    private void updateLabel(){
        String myFormat="dd/MM/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        edt5.setText(dateFormat.format(myCalendar.getTime()));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(getApplicationContext(),OptionsActivity.class));
        return super.onOptionsItemSelected(item);
    }



}


