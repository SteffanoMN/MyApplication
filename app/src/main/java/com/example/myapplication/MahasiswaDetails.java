package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MahasiswaDetails extends AppCompatActivity {

    TextView Nama, Nomor, Email, NPM;
    String Text1, Text2, Text3, Text4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_details);

        Nama = findViewById(R.id.Nama);
        Nomor = findViewById(R.id.Nomor);
        Email = findViewById(R.id.Email);
        NPM = findViewById(R.id.NPM);


    }

    private void getData() {
        if (getIntent().hasExtra("Nama") && getIntent().hasExtra("Nim") &&
                getIntent().hasExtra("Nomor") && getIntent().hasExtra("Email")) {
            Text1 = getIntent().getStringExtra("Nama");
            Text2 = getIntent().getStringExtra("Nomor");
            Text3 = getIntent().getStringExtra("Email");
            Text4 = getIntent().getStringExtra("NPM");
        }

    }

    private void setData() {
        Nama.setText(Text1);
        Nomor.setText(Text2);
        Email.setText(Text3);
        NPM.setText(Text4);
    }
}