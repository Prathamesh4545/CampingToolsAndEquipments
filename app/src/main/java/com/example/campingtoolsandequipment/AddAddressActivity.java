package com.example.campingtoolsandequipment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toolbar;

public class AddAddressActivity extends AppCompatActivity {

    EditText userName,address_Lane,city,postalCode,phoneNo;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

    }
}