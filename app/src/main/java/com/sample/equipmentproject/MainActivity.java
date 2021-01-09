package com.sample.equipmentproject;


import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sample.equipmentproject.Adapters.ExpandableAdapter;
import com.sample.equipmentproject.Models.Root;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Json from Assets
        new Thread() {
            @Override
            public void run() {
                super.run();
                String strJson = loadJsonFromAssert();
                Log.d("TAG", strJson);
                Gson gson = new GsonBuilder().create();
                Root[] root = gson.fromJson(strJson, Root[].class);
                runOnUiThread(() -> {
                    //Dynamic views
                    ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
                    getSupportActionBar().setTitle(getResources().getString(R.string.choose_equipment));
                    Spannable text = new SpannableString(getSupportActionBar().getTitle());
                    text.setSpan(new ForegroundColorSpan(Color.BLACK), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                    getSupportActionBar().setTitle(text);


                    ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    RecyclerView recyclerView = new RecyclerView(MainActivity.this);
                    recyclerView.setBackgroundColor(Color.LTGRAY);
                    recyclerView.setLayoutParams(layoutParams);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);

                    //Attaching adapter to recyclerView
                    recyclerView.setAdapter(new ExpandableAdapter(root, MainActivity.this));
                    constraintLayout.addView(recyclerView);

                });
            }
        }.start();


    }

    private String loadJsonFromAssert() {
        String json = null;
        try {
            InputStream is = getAssets().open("assignment.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        return json;
    }
}