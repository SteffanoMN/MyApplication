package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView movie_name = findViewById(R.id.movie_name);
        TextView movie_release_date = findViewById(R.id.movie_release_date);
        TextView movie_overview = findViewById(R.id.movie_desc);
        ImageView movie_img = findViewById(R.id.movie_image);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String title = bundle.getString("original_title");
            String date = bundle.getString("release_date");
            String desc = bundle.getString("overview");
            String image = bundle.getString("poster_path");

            movie_name.setText(title);
            movie_release_date.setText(date);
            movie_overview.setText(desc);

            Picasso.get().load(image).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher_round).into(movie_img);
        }
    }
}