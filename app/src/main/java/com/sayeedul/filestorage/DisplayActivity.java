package com.sayeedul.filestorage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class DisplayActivity extends AppCompatActivity{

    TextView detail;
    String FILENAME = "myFile.txt";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_main);

        detail = (TextView)findViewById(R.id.detailsTV);

        try {
            FileInputStream fileInputStream = openFileInput(FILENAME);

            InputStreamReader inputReader = new InputStreamReader(fileInputStream);

            char [] inputText = new char[200];
            String finalFileText = "";

            int charCount;

            while ((charCount = inputReader.read(inputText))>0){
                finalFileText = finalFileText + String.copyValueOf(inputText);
            }
            inputReader.close();
             detail.setText(finalFileText);
             detail.setVisibility(TextView.VISIBLE);

            Toast.makeText(this, "File Read , see the details", Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
