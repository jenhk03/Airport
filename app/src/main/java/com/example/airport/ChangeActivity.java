package com.example.airport;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangeActivity extends AppCompatActivity
{
    private EditText etName, etCity, etAddress;
    private Button btnChange;
    MyDatabaseHelper myDB = new MyDatabaseHelper(ChangeActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        etName = findViewById(R.id.et_name);
        etCity = findViewById(R.id.et_city);
        etAddress = findViewById(R.id.et_address);
        btnChange = findViewById(R.id.btn_change);
        Intent intent = getIntent();
        String id = intent.getStringExtra("varID");
        String name = intent.getStringExtra("varName");
        String city = intent.getStringExtra("varCity");
        String address = intent.getStringExtra("varAddress");
        etName.setText(name);
        etCity.setText(city);
        etAddress.setText(address);
        btnChange.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String getName, getCity, getAddress;
                getName = etName.getText().toString();
                getCity = etCity.getText().toString();
                getAddress = etAddress.getText().toString();
                if (getName.trim().equals(""))
                {
                    etName.setError("Nama Bandara tidak boleh kosong!!");
                }
                else if (getCity.trim().equals(""))
                {
                    etCity.setError("Kabupaten/Kota tidak boleh kosong!!");
                }
                else if (getAddress.trim().equals(""))
                {
                    etAddress.setError("Alamat Bandara tidak boleh kosong!!");
                }
                else
                {
                    long exec = myDB.changeAirport(id, getName, getCity, getAddress);
                    if (exec == -1)
                    {
                        Toast.makeText(ChangeActivity.this, "Gagal mengubah data!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(ChangeActivity.this, "Berhasil mengubah data!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        );
    }
}