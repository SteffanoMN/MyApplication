package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recview;
    private MovieAdapter adapter;
    private ArrayList<MovieModel> arrayList;
    private ProgressBar progressBar;
    ConstraintLayout rootLayout;
    EditText search;
    CharSequence searchresult="";

    void getData() {
        AndroidNetworking.get("https://api.themoviedb.org/3/movie/upcoming?api_key=b9dfca59664f58a8ac10cb5506272133&language=en-US&page=1")
                .addPathParameter("pageNumber", "0")
                .addQueryParameter("limit", "3")
                .addHeaders("token", "1234")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray result = response.getJSONArray("results");
                            for (int i = 0; i < result.length(); i++) {
                                arrayList = new ArrayList<>();
                                JSONObject movieObject = result.getJSONObject(i);
                                String name = movieObject.getString("original_title");
                                String date = movieObject.getString("release_date");
                                String overview = movieObject.getString("overview");
                                String image = movieObject.getString("poster_path");
                                arrayList.add(new MovieModel(name, date, overview, image));
                            }
                            adapter = new MovieAdapter(arrayList, getApplicationContext());
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            recview.setLayoutManager(layoutManager);
                            recview.setAdapter(adapter);
                            AnimationSet set = new AnimationSet(true);

                            Animation animation = new AlphaAnimation(0.0f, 1.0f);
                            animation.setDuration(500);
                            set.addAnimation(animation);

                            animation = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                                    Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f
                            );
                            animation.setDuration(100);
                            set.addAnimation(animation);

                            LayoutAnimationController controller = new LayoutAnimationController(set, 0.5f);

                            recview.setLayoutAnimation(controller);
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
                            adapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                                    intent.putExtra("original_title", arrayList.get(position).getOriginal_title());
                                    intent.putExtra("poster_path", arrayList.get(position).getPoster_path());
                                    intent.putExtra("release date", arrayList.get(position).getRelease_date());
                                    intent.putExtra("overview", arrayList.get(position).getOverview());
                                    startActivity(intent);
                                }
                            });
                            progressBar.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            Log.d("error", e.toString());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("error", anError.toString());
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        recview = (RecyclerView) findViewById(R.id.rvdata);
        rootLayout = (ConstraintLayout) findViewById(R.id.rootlayout);
        search = (EditText) findViewById(R.id.search_input);
        progressBar = findViewById(R.id.progress_bar);
        getData();
    }

}