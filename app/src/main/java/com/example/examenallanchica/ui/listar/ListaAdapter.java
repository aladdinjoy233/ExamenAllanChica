package com.example.examenallanchica.ui.listar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examenallanchica.R;
import com.example.examenallanchica.databinding.ItemNotaBinding;

import java.util.ArrayList;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolder> {

    private ArrayList<String> notas;
    private LayoutInflater inflater;

    public ListaAdapter(ArrayList<String> notas, LayoutInflater inflater) {
        this.notas = notas;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ListaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNotaBinding binding = ItemNotaBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaAdapter.ViewHolder holder, int position) {
        holder.binding.tvNota.setText(notas.get(position));
    }

    @Override
    public int getItemCount() { return notas.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemNotaBinding binding;

        public ViewHolder(@NonNull ItemNotaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v -> {
               Bundle bundle = new Bundle();
               bundle.putString("nota", notas.get(getAdapterPosition()));
               Navigation.findNavController(v).navigate(R.id.frag_nota, bundle);
            });
        }
    }
}
