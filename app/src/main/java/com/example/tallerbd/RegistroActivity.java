package com.example.tallerbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tallerbd.BaseDatos.MyDatabase;
import com.example.tallerbd.entidades.Client;
import com.google.android.material.textfield.TextInputEditText;

public class RegistroActivity extends AppCompatActivity {

    //Variables
    TextInputEditText tieName, tieAddress, tieNumberPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Instancias de los widgets o asignar la variable correspondiente desde la vista
        tieName = findViewById(R.id.tieName);
        tieAddress = findViewById(R.id.tieAddress);
        tieNumberPhone = findViewById(R.id.tieNumberPhone);
    }

    public void guardarCliente(View view) {
        String name = tieName.getText().toString();
        String address = tieAddress.getText().toString();
        String numberPhone = tieNumberPhone.getText().toString();
        Client client = new Client();
        client.setName(name);
        client.setAddress(address);
        client.setNumberPhone(numberPhone);
        MyDatabase myDatabase = new MyDatabase(RegistroActivity.this);
        Boolean id= myDatabase.addClient(client);
        Toast.makeText(RegistroActivity.this, "Registro guardado", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}