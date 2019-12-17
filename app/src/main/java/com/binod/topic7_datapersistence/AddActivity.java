package com.binod.topic7_datapersistence;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintStream;

public class AddActivity extends AppCompatActivity {

    EditText etWord, etMeaning;
    Button btnAddWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etWord = findViewById(R.id.etWord);
        etMeaning = findViewById(R.id.etMeaning);
        btnAddWord = findViewById(R.id.btnAddWord);

        btnAddWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();
                etWord.setText(" ");
                etMeaning.setText(" ");
                etWord.setSelection(0);

            }
        });
    }



        private void Save() {
            try{
                PrintStream printStream = new PrintStream(openFileOutput("word.txt",MODE_PRIVATE | MODE_APPEND));
                printStream.println(etWord.getText().toString() + "->" + etMeaning.getText().toString());
                Toast.makeText(this, "Saved to " + getFilesDir(), Toast.LENGTH_SHORT).show();

            }catch (IOException e){
                Log.d("Dictionary App", "msg" + e.toString());
                e.printStackTrace();

            }
        }
    }

