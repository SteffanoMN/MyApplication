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
    private MicrosoftAdapter adapter;
    private ArrayList<Microsoft> microsoftArrayList;
    ConstraintLayout rootLayout;
    EditText search;
    CharSequence searchresult="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
        rootLayout = (ConstraintLayout) findViewById(R.id.rootlayout);
        search = (EditText) findViewById(R.id.search_input);
        microsoftArrayList = new ArrayList<>();

        microsoftArrayList = new ArrayList<>();
        int[] images = {R.drawable.word, R.drawable.powerpoint, R.drawable.excel, R.drawable.sway,
                R.drawable.sharepoint, R.drawable.onenote, R.drawable.teams, R.drawable.outlook};  ;
        microsoftArrayList.add(new Microsoft("Microsoft Word", "4.2.0", "new and super polished word document app", images[0]));
        microsoftArrayList.add(new Microsoft("Microsoft Powerpoint", "3.9.0", "email", images[1]));
        microsoftArrayList.add(new Microsoft("Microsoft Excel", "2.2.2", "email budi", images[2]));
        microsoftArrayList.add(new Microsoft("Microsoft Sway", "2.2.2", "email budi", images[3]));
        microsoftArrayList.add(new Microsoft("Microsoft Sharepoint", "2.2.2", "email budi", images[4]));
        microsoftArrayList.add(new Microsoft("Microsoft Onenotet", "2.2.2", "email budi", images[5]));
        microsoftArrayList.add(new Microsoft("Microsoft Teams", "2.2.2", "email budi", images[6]));
        Microsoft Outlook = new Microsoft("Microsoft Outlook", "5.7.1" , "sari@gmail.com", images[7]);
        microsoftArrayList.add(Outlook);


        adapter = new MicrosoftAdapter(microsoftArrayList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

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

}