package com.example.tallerbd.BaseDatos;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.tallerbd.entidades.Client;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {

    //Crear BASE DE DATOS
    private Context context;
    private static final String DATABASE_NAME = "MyDataBase.db";
    private static final int DATABASE_VERSION= 1;

    public MyDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context= context;
    }

    //Crear tabla y sus respectivas columnas
    private static final String TABLE_CLIENT = "client";
    private static final String COLUMN_ID = "client_id";
    private static final String COLUMN_NAME = "client_name";
    private static final String COLUMN_ADDRESS = "client_address";
    private static final String COLUMN_NUMBER_PHONE = "client_number_phone";

    //Crear query o crear la tabla en la BD SQLITE
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_CLIENT + " ("+
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_NUMBER_PHONE + " TEXT);";
        db.execSQL(query);
    }

    //Sentencia para borrar la tabla
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CLIENT);
        onCreate(db);
    }

    //Insertar cliente
    public Boolean addClient(Client client){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, client.getName());
        values.put(COLUMN_ADDRESS, client.getAddress());
        values.put(COLUMN_NUMBER_PHONE, client.getNumberPhone());

        long result = db.insert(TABLE_CLIENT, null, values);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    //Obtener clientes
    @SuppressLint("Range")
    public ArrayList<Client> getClients() {
        ArrayList<Client> clients = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_CLIENT;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount()!=0) {
            while (cursor.moveToNext()) {
                Client client = new Client();
                    client.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                    client.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                    client.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
                    client.setNumberPhone(cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER_PHONE)));
                    clients.add(client);
            }
        }
        return clients;
    }
}
