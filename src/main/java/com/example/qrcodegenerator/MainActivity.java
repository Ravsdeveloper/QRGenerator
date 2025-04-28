package com.example.qrcodegenerator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText ET1, ET2, ET3;
    Button btnlogin;
    Spinner spinner;
    TextView text1;
    String[] logintype={"Email","Mobile"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ET1 = findViewById(R.id.ET1);
        ET2 = findViewById(R.id.ET2);
        ET3 = findViewById(R.id.ET3);
        btnlogin = findViewById(R.id.btnlogin);
        spinner=findViewById(R.id.spinner);
        text1=findViewById(R.id.text1);
        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(MainActivity.this,R.layout.simplespinner,R.id.text1,logintype);
        arrayAdapter.setDropDownViewResource(R.layout.simplespinner);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value=parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this,value,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


    }

    public void login() {
        int Mobile = Integer.parseInt(ET1.getText().toString());
        String Email = ET2.getText().toString();
        String password = ET3.getText().toString();
        if (!(Mobile==0)) {
            if (!password.equals("")) {
                if (Mobile == 1234 && password.equals("admin")) {
                    Toast.makeText(this, "Login Successful..:)", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, OptionsActivity.class);
                    startActivity(intent);
                    finish();
                    if (Email.equals("admin") && password.equals("admin")) {
                        ET2.setVisibility(View.VISIBLE);
                            Toast.makeText(this, "Login Successful..:)", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(this, OptionsActivity.class);
                            startActivity(intent);
                            finish();

                        } else
                            Toast.makeText(this, "Password or Email can be wrong", Toast.LENGTH_SHORT).show();
                    ET2.setVisibility(View.INVISIBLE);

                    } else
                    Toast.makeText(this, "Password or Mobile can be wrong", Toast.LENGTH_SHORT).show();
            } else
                    Toast.makeText(this, "Password cannot be blank", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Mobile cannot be blank", Toast.LENGTH_SHORT).show();
}
//finished activity

    }


