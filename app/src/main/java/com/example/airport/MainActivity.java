package com.example.airport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private MyDatabaseHelper myDB;
    private FloatingActionButton fabAdd;
    private RecyclerView rvAirport;
    private AdapterAirport adapterAirport;
    private ArrayList<String> arrID, arrName, arrCity, arrAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvAirport = findViewById(R.id.rv_airport);
        myDB = new MyDatabaseHelper(MainActivity.this);
        fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        }
        );
    }
    protected void onResume()
    {
        super.onResume();
        showAirport();
    }
    private void SQLiteToArrayList()
    {
        Cursor cursor = myDB.readDataAirport();
        if (cursor.getCount() == 0)
        {
            Toast.makeText(this, "Tidak ada data", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext())
            {
                arrID.add(cursor.getString(0));
                arrName.add(cursor.getString(1));
                arrCity.add(cursor.getString(2));
                arrAddress.add(cursor.getString(3));
            }
        }
    }
    private void showAirport()
    {
        arrID = new ArrayList<>();
        arrName = new ArrayList<>();
        arrCity = new ArrayList<>();
        arrAddress = new ArrayList<>();
        SQLiteToArrayList();
        adapterAirport = new AdapterAirport(MainActivity.this, arrID, arrName, arrCity, arrAddress);
        rvAirport.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvAirport.setAdapter(adapterAirport);
    }
}