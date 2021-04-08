package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

public class MahasiswaDetails extends AppCompatActivity {

    TextView Nama, Nomor, Email, NPM;
    String Text1, Text2, Text3, Text4;
    Button Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_details);

        Nama = findViewById(R.id.Nama);
        Nomor = findViewById(R.id.Nomor);
        Email = findViewById(R.id.Email);
        NPM = findViewById(R.id.NPM);
        Back = findViewById(R.id.backbbutton);

        getData();
        setData();

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent BackLayout = new Intent(MahasiswaDetails.this, MahasiswaAdapter.class);
                startActivity(BackLayout);
            }
        });
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