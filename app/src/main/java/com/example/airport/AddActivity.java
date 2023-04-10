package com.example.airport;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity
{
    private EditText etName, etCity, etAddress;
    private Button btnAdd;
    MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        etName = findViewById(R.id.et_name);
        etCity = findViewById(R.id.et_city);
        etAddress = findViewById(R.id.et_address);
        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String name, city, address;
                name = etName.getText().toString();
                city = etCity.getText().toString();
                address = etAddress.getText().toString();
                if (name.trim().equals(""))
                {
                    etName.setError("Nama tidak boleh kosong");
                }
                else if (city.trim().equals(""))
                {
                    etCity.setError("Kota tidak boleh kosong");
                }
                else if (address.trim().equals(""))
                {
                    etAddress.setError("Alamat tidak boleh kosong");
                }
                else
                {
                    long ex = myDB.AddAirport(name, city, address);
                    if (ex == -1)
                    {
                        Toast.makeText(AddActivity.this, "Gagal Menambah Data", Toast.LENGTH_SHORT).show();
                        etName.requestFocus();
                    }
                    else
                    {
                        Toast.makeText(AddActivity.this, "Berhasil Menambah Data", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        }
        );
    }
}