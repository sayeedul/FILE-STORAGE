package com.sayeedul.filestorage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class MainActivity extends AppCompatActivity implements OnClickListener, AdapterView.OnItemSelectedListener {

    String [] country = {"India","China","Pakistan","USA"};
    String [] state1 = {"Ind.State","W.B","M.P","Punjab","Maharashtra"};
    String [] state2 = {"Chin.State","Sichuan","Gansu","Yunnan","Hunan"};
    String [] state3 = {"Pak.State","Punjab.","Sindh","Balochistan","Khyber Pakhtunkhwa"};
    String [] state4 = {"USA.State","Georgia","Hawaii","Texas"," Colorado"};

    Spinner CountrySpinner,StateSpinner;
    ArrayAdapter<String> countryAdapter;
    ArrayAdapter<String> stateAdapter;

    EditText user,pass;
    Button submit;
    String FILENAME = "myFile.txt";
    String details ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText)findViewById(R.id.userET);
        pass = (EditText)findViewById(R.id.passET);
        submit = (Button)findViewById(R.id.signupET);

        CountrySpinner = (Spinner)findViewById(R.id.countrySpinner);
        StateSpinner = (Spinner)findViewById(R.id.stateSpinner);


        String  username,password;

        username = user.getText().toString();
        password = pass.getText().toString();
        details = user.getText().toString() +"\n\n\n"+pass.getText().toString()+"\n";

        countryAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,country);
        CountrySpinner.setAdapter(countryAdapter);

        stateAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,state1);
        StateSpinner.setAdapter(stateAdapter);

        CountrySpinner.setOnItemSelectedListener(this);

        submit.setOnClickListener(this);

    }




    @Override
    public void onClick(View v) {

        // Save Data To File
        try {
            FileOutputStream fileOutputStream = openFileOutput(FILENAME,MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write("USERNAME : "+user.getText().toString() +"\n\nPASSWORD : "+pass.getText().toString());


            outputStreamWriter.close();
            Toast.makeText(this, "File Saved Successfully...\n...loading display page...", Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent i = new Intent(MainActivity.this,DisplayActivity.class);
        startActivity(i);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch(position)
        {
            case 0 :   stateAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,state1);
                StateSpinner.setAdapter(stateAdapter);
                break;

            case 1 :   stateAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,state2);
                StateSpinner.setAdapter(stateAdapter);
                break;

            case 2 :   stateAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,state3);
                StateSpinner.setAdapter(stateAdapter);
                break;

            case 3 :    stateAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,state4);
                StateSpinner.setAdapter(stateAdapter);
                break;


        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        Toast.makeText(MainActivity.this, "NOTHING SELECTED. PLEASE SELECT", Toast.LENGTH_SHORT).show();

    }
}

