package com.example.pixabayapi;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final List<Hit> hitList = new ArrayList<>();
    private PixaApi adepter;


    private  static final String URL="https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView=findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        adepter = new PixaApi(this,hitList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adepter);

       jsonResquest();
}

    private void jsonResquest() {
        StringRequest stringRequest =new StringRequest(Request.Method.GET, URL, response -> {

            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.serializeNulls().create();

            User users = gson.fromJson(response,User.class);

            hitList.clear();

            hitList.addAll(users.getHits());
            adepter.notifyDataSetChanged();

        }, error -> Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show()


        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    }
