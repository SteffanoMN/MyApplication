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
        microsoftArrayList.add(new Microsoft("Microsoft Word", "version 16.47", "The trusted Word app lets you create, edit, view," +
                " and share your files with others quickly and easily. Send, view and edit Office docs attached to emails from your phone with this " +
                "powerful word processing app.", images[0]));
        microsoftArrayList.add(new Microsoft("Microsoft Powerpoint", "version 16.47", "The PowerPoint app gives you access to the " +
                "familiar slideshow maker tool you already know. Create, edit, view, present, or share presentations quickly and easily from anywhere.", images[1]));
        microsoftArrayList.add(new Microsoft("Microsoft Excel", "version 16.47.1", "Microsoft Excel, the spreadsheet app, lets you " +
                "create, view, edit, and share your files quickly and easily. Manage spreadsheets, tables and workbooks attached to email messages from" +
                " your phone with this powerful productivity app.", images[2]));
        microsoftArrayList.add(new Microsoft("Microsoft Sway", "version 1.20.1", "Create visually striking newsletters, presentations, and" +
                " documentation in minutes. Sway is integrated with your device, social networks, and the web. Sway suggests searches to help you find relevant" +
                " contents that you can add right into your creation. Sway's design engine brings your content together beautifully. Sway is for you", images[3]));
        microsoftArrayList.add(new Microsoft("Microsoft Sharepoint", "version 3.24.0", "Your mobile, intelligent intranet. Share and organize" +
                " content, knowledge and apps to drive teamwork. Quickly find information and get work done collaboratively and seamlessly across the organization.", images[4]));
        microsoftArrayList.add(new Microsoft("Microsoft Onenote", "version 16.47", "Capture your thoughts, discoveries, and ideas and " +
                "simplify overwhelming planning moments in your life with your very own digital notepad. With OneNote, you can plan that big event, seize" +
                " that moment of inspiration to create something new, and track that list of errands that are too important to forget.", images[5]));
        microsoftArrayList.add(new Microsoft("Microsoft Teams", "version 1.00.330874", "Microsoft Teams is your hub for teamwork," +
                " which brings together everything a team needs: chat and threaded conversations, meetings & video conferencing, calling, content collaboration" +
                " with the power of Microsoft 365 applications, and the ability to create and integrate apps and workflows that your business relies on.", images[6]));
        Microsoft Outlook = new Microsoft("Microsoft Outlook", "version 16.47" , "Outlook is making it easier to identify emails from" +
                " senders outside your organization to protect against spam & phishing threats. If admin configured, there will be a new external label" +
                " on emails and the sender's email address can be viewed by tapping the external label at the top of the email.", images[7]);
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