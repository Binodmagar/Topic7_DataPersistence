package com.binod.topic7_datapersistence;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MeaningActivity extends AppCompatActivity {

    TextView tvSearchMeaning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meaning);

        tvSearchMeaning = findViewById(R.id.tvSearchMeaning);

        Bundle bundle = getIntent().getExtras();

        if(bundle!=null){
            String meaning = bundle.getString("meaning");
            tvSearchMeaning.setText(meaning);
        }
    }
}
