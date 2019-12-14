package com.example.sqliteinsertandview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nameEt, ageEt;
    private Button insertBtn;
    String name, age;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        insertData();


    }

    private void insertData() {
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = nameEt.getText().toString();
                age = ageEt.getText().toString();

                long id = helper.insertData(name, age);
                Toast.makeText(MainActivity.this, "Your id" + id, Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void init() {

        nameEt = findViewById(R.id.nameEt);
        ageEt = findViewById(R.id.ageEt);
        insertBtn = findViewById(R.id.insertBtn);
        helper = new DatabaseHelper(this);

    }

    //Here showData is onClick Button from xml
    public void ShowData(View view) {
        startActivity(new Intent(this, ShowActivity.class));

    }
}
