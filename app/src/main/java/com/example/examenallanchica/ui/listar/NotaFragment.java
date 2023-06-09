package com.example.examenallanchica.ui.listar;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.examenallanchica.R;
import com.example.examenallanchica.databinding.FragmentNotaBinding;
import com.example.examenallanchica.databinding.ItemNotaBinding;

public class NotaFragment extends Fragment {

    private NotaViewModel viewModel;
    private FragmentNotaBinding binding;

    public static NotaFragment newInstance() {
        return new NotaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNotaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(NotaViewModel.class);

        viewModel.procesarDatos(getArguments());

        viewModel.getNota().observe(getViewLifecycleOwner(), nota -> binding.tvNotaDesc.setText(nota));

        binding.btEliminar.setOnClickListener(v -> {
            avisoEliminacion(getContext());

//            Volver al listado
            viewModel.getEliminado().observe(getViewLifecycleOwner(), eliminado -> {
                if (eliminado) {
                    Navigation.findNavController(v).navigate(R.id.nav_listar);
                }
            });
        });

        return root;
    }

    public void avisoEliminacion(Context context) {
        new AlertDialog.Builder(context)
                .setTitle("Eliminación")
                .setMessage("¿Desea eliminar la nota?")
                .setPositiveButton("Si", (dialog, which) -> viewModel.eliminarNota() )
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .show();
    }

}