package com.example.examenallanchica.ui.listar;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.examenallanchica.databinding.FragmentListarBinding;

public class ListarFragment extends Fragment {

    private FragmentListarBinding binding;
    private ListarViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(ListarViewModel.class);

        binding = FragmentListarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewModel.getListaDeNotasLiveData().observe(getViewLifecycleOwner(), notas -> {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
            binding.rvListaNotas.setLayoutManager(gridLayoutManager);
            ListaAdapter adapter = new ListaAdapter(notas, inflater);
            binding.rvListaNotas.setAdapter(adapter);

//            Por si agregan uno nuevo
            binding.etNotaNueva.setText("");
        });

        binding.btAgregar.setOnClickListener(v -> {
            viewModel.agregarNota(binding.etNotaNueva.getText().toString());

//            Cerrar el teclado cuando apretan el boton
            binding.etNotaNueva.clearFocus();
            InputMethodManager inputMethodManager = (InputMethodManager) requireContext().getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}