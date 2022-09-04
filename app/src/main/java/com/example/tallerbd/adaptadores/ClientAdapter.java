package com.example.tallerbd.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tallerbd.R;
import com.example.tallerbd.entidades.Client;

import java.util.ArrayList;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ViewHolder> {

    ArrayList<Client> clients;

    public ClientAdapter(ArrayList<Client> client) {
        this.clients = client;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Client client = clients.get(position);
        holder.tvNameContact.setText(client.getName());
        holder.tvAddressContact.setText(client.getAddress());
        holder.tvPhoneContact.setText(client.getNumberPhone());
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameContact, tvAddressContact, tvPhoneContact;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameContact = itemView.findViewById(R.id.tvNameContact);
            tvAddressContact = itemView.findViewById(R.id.tvAddressContact);
            tvPhoneContact = itemView.findViewById(R.id.tvPhoneContact);
        }
    }
}
