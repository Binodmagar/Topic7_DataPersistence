package com.binod.topic7_datapersistence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity{
    Button btnAddActivity, btnMeaningActivity;
    ListView lvList;
    private Map<String, String>dictionary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddActivity = findViewById(R.id.btnAddActivity);
        btnMeaningActivity = findViewById(R.id.btnMeaningActivity);
        lvList = findViewById(R.id.lvList);
        dictionary = new HashMap<>();

        readFromFile();
        ArrayAdapter adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(dictionary.keySet()));

        lvList.setAdapter(adapter);

        lvList.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key = parent.getItemAtPosition(position).toString();//get current position
                String meaning = dictionary.get(key);//get the meaning of the current position key

                //Intent will call MeaningActivity from MainActivity
                Intent intent = new Intent(MainActivity.this, MeaningActivity.class);
                //to pass message from this activity to meaning activity
                intent.putExtra("meaning", meaning);
                startActivity(intent);
            }
        }));

        btnAddActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        btnMeaningActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this,MeaningActivity.class);
                startActivity(intent1);
            }
        });

    }


    private void readFromFile(){
        try{
            FileInputStream fileInputStream = openFileInput("word.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = "";

            while ((line= bufferedReader.readLine()) != null){
                String[] parts = line.split("->");
                dictionary.put(parts[0], parts[1]);

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
