package com.example.tallerbd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tallerbd.BaseDatos.MyDatabase;
import com.example.tallerbd.adaptadores.ClientAdapter;
import com.example.tallerbd.entidades.Client;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Redirigir el icono a la vista de registrar
    public void addClient(View view) {
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }

    public void cargarData() {
        MyDatabase db = new MyDatabase(this);
        ArrayList<Client> clients = db.getClients();

        RecyclerView rvClients = findViewById(R.id.rvClients);
        ClientAdapter clientAdapter = new ClientAdapter(clients);
        rvClients.setAdapter(clientAdapter);
        rvClients.setLayoutManager(new LinearLayoutManager(this));
        clientAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarData();
    }

}