package com.example.sqliteinsertandview;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateData extends AppCompatActivity {

    private EditText nameEt, ageEt,idEt;
    private Button updateBtn;
    String name, age,id;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        init();
        updateValues();
    }

    private void init(){
        idEt = findViewById(R.id.idEt);
        nameEt = findViewById(R.id.nameEt);
        ageEt = findViewById(R.id.ageEt);
        updateBtn = findViewById(R.id.updateBtn);
        helper = new DatabaseHelper(this);

    }

    private void updateValues(){
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameEt.getText().toString();
                age = ageEt.getText().toString();
                id = idEt.getText().toString();
                boolean isUpdate = helper.updateValues(id,name, age);
                if(isUpdate){
                    Toast.makeText(UpdateData.this,"Data Update",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(UpdateData.this,"Data not Updated",Toast.LENGTH_LONG).show();
                }
                //it has been used for displaying updated data using recyclerviwq
                startActivity(new Intent(UpdateData.this,ShowActivity.class));
            }
        });
    }

}
