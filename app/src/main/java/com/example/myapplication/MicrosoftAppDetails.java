package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MicrosoftAppDetails extends AppCompatActivity {

    TextView Name, Desc, Vers;
    String Text1, Text2, Text3;
    int Picture;
    ImageView imgProduct;
    Button Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_details);

        Name = findViewById(R.id.Name);
        Desc = findViewById(R.id.Desc);
        Vers = findViewById(R.id.Vers);
        imgProduct = findViewById(R.id.imgProduct);
        Back = findViewById(R.id.backbbutton);

        getData();
        setData();

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent BackLayout = new Intent(MicrosoftAppDetails.this, MicrosoftAdapter.class);
                startActivity(BackLayout);
            }
        });
    }

    private void getData() {
        if (getIntent().hasExtra("Name") && getIntent().hasExtra("Description")
                && getIntent().hasExtra("Version")) {
            Text1 = getIntent().getStringExtra("Name");
            Text2 = getIntent().getStringExtra("Description");
            Text3 = getIntent().getStringExtra("Version");
            Picture = getIntent().getIntExtra("Picture", 1);
        }

    }

    private void setData() {
        Name.setText(Text1);
        Desc.setText(Text2);
        Vers.setText(Text3);
        imgProduct.setImageResource(Picture);
    }


}