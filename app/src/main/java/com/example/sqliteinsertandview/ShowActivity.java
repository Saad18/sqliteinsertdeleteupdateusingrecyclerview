package com.example.sqliteinsertandview;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    UserAdapter adapter;
    List<User> users;
    DatabaseHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        init();

        getData();
    }

    private void getData(){

        Cursor cursor = helper.showData();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(helper.COL_ID));
            String name = cursor.getString(cursor.getColumnIndex(helper.COL_NAME));
            String age = cursor.getString(cursor.getColumnIndex(helper.COL_AGE));

            users.add(new User(id,name,age));
            adapter.notifyDataSetChanged();
        }

    }

    private void init(){

        recyclerView = findViewById(R.id.userRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        users = new ArrayList<>();
        helper = new DatabaseHelper(this);
        adapter = new UserAdapter(users);
        recyclerView.setAdapter(adapter);
    }
}
