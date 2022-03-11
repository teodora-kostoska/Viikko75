package com.example.viikko75;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    Button save;
    Button load;
    EditText text;
    EditText filename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save = findViewById(R.id.save);
        load = findViewById(R.id.load);
        text = findViewById(R.id.inputField);
        filename = findViewById(R.id.fileName);
        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                saveToFile();
            }
        });
        load.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               loadFromFile();
           }
        });
    }
    public void saveToFile(){
        try {
            String file = filename.getText().toString();
            String input = text.getText().toString();
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput(file, 0));
            out.write(input);
            out.close();
            String savedText = "Input saved to file " + file;
            text.setText(savedText);
        }catch(FileNotFoundException e){
            System.out.println("File not found!");
            e.printStackTrace();
        }catch(IOException i){
            System.out.println("IO Exception!");
            i.printStackTrace();
        }
    }

    public void loadFromFile(){
        try{
            String file = filename.getText().toString();
            InputStream in = openFileInput(file);
            if(in !=null){
                InputStreamReader inputReader = new InputStreamReader(in);
                BufferedReader bufferedReader = new BufferedReader(inputReader);
                StringBuilder buildString = new StringBuilder();
                String fileContent;
                while((fileContent= bufferedReader.readLine()) != null){
                    buildString.append(fileContent + " ");
                }
                bufferedReader.close();
                inputReader.close();
                in.close();
                text.setText(buildString.toString());
            }
        }catch(FileNotFoundException e){
            System.out.println("File not found!");
            e.printStackTrace();
        }catch(IOException i){
            System.out.println("IO Exception");
            i.printStackTrace();
        }
    }
}