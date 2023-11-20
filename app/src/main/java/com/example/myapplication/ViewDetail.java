package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewDetail extends AppCompatActivity {
    private ListView listView;
    private CustomAdapter adapter;
    private Button backButton;  // Bạn đã khai báo biến backButton ở mức lớp
    private List<UserModel> listUser = new ArrayList<UserModel>();
    private CustomAdapter customAdapter;
    private RecyclerView recyclerView;
    public DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        recyclerView = findViewById(R.id.recyclerView);

        dbHelper = new DatabaseHelper(ViewDetail.this);
        displayUsers();


        customAdapter = new CustomAdapter(ViewDetail.this, this, listUser);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewDetail.this));

        setListener();
    }


    public void displayUsers() {
        Cursor cursorHike = dbHelper.getUsersData();
        if (cursorHike.getCount() == 0) {
            Toast.makeText(ViewDetail.this, "No Data User", Toast.LENGTH_SHORT).show();
        } else {
            while (cursorHike.moveToNext()) {
                listUser.add(new UserModel(Integer.parseInt(cursorHike.getString(0)),
                        cursorHike.getString(1),
                        cursorHike.getString(2),
                        cursorHike.getString(3),
                        cursorHike.getString(4)));
            }
        }
    }

    public void setListener() {
        if (backButton != null) {
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed(); // This will simulate the system's back button press
                }
            });
        }
    }

}










