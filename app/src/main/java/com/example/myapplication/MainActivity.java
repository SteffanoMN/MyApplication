package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MahasiswaAdapter adapter;
    private ArrayList<Mahasiswa> mahasiswaArrayList;
    private FloatingActionButton fabSwitcher;
    boolean isDark = false;
    ConstraintLayout rootLayout;
    EditText search;
    CharSequence searchresult="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
        fabSwitcher = (FloatingActionButton) findViewById(R.id.brightness);
        rootLayout = (ConstraintLayout) findViewById(R.id.rootlayout);
        search = (EditText) findViewById(R.id.search_input);

        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();

        addData();

        mahasiswaArrayList = new ArrayList<>();
        adapter = new MahasiswaAdapter(mahasiswaArrayList, this, isDark);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        isDark = getThemePref();
        if(isDark) {
            rootLayout.setBackgroundColor(getResources().getColor(R.color.black));
            search.setBackgroundResource(R.drawable.search_style_dark);
        } else {
            search.setBackgroundResource(R.drawable.search_style);
            rootLayout.setBackgroundColor(getResources().getColor(R.color.white));
        }

        fabSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            isDark = !isDark;
            if(isDark){
                search.setBackgroundResource(R.drawable.search_style_dark);
                rootLayout.setBackgroundColor(getResources().getColor(R.color.black));
            } else {
                search.setBackgroundResource(R.drawable.search_style);
                rootLayout.setBackgroundColor(getResources().getColor(R.color.white));
            }
            adapter = new MahasiswaAdapter( mahasiswaArrayList, getApplicationContext(), isDark);
            if (!searchresult.toString().isEmpty()) {
                adapter.getFilter().filter(searchresult);
            }
            recyclerView.setAdapter(adapter);
            saveThemePref(isDark);
            }
            });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
                searchresult = s;
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void saveThemePref(boolean isDark) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isDark", isDark);
        editor.commit();
    }

    private boolean getThemePref() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPref", MODE_PRIVATE);
        boolean isDark = pref.getBoolean("isDark", false);
        return isDark;
    }

    void addData(){
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new Mahasiswa("Dimas Maulana", "999", "081222","email"));
        mahasiswaArrayList.add(new Mahasiswa("Budi", "000", "081222", "email"));
        mahasiswaArrayList.add(new Mahasiswa("Ani", "222", "083333", "email budi"));
        Mahasiswa Sari = new Mahasiswa("Sari", "xxx" , "000", "sari@gmail.com");
        mahasiswaArrayList.add(Sari);
    }

}