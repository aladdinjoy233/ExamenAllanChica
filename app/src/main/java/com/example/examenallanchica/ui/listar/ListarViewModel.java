package com.example.examenallanchica.ui.listar;

import static com.example.examenallanchica.MainActivity.listaNotas;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ListarViewModel extends ViewModel {

    private MutableLiveData<ArrayList<String>> listaDeNotasLiveData = new MutableLiveData<>();

    public ListarViewModel() {
        listaDeNotasLiveData.setValue(listaNotas);
    }

    public LiveData<ArrayList<String>> getListaDeNotasLiveData() {
        if (listaDeNotasLiveData == null) {
            listaDeNotasLiveData = new MutableLiveData<>();
            listaDeNotasLiveData.setValue(listaNotas);
        }
        return listaDeNotasLiveData;
    }

    public void agregarNota(String nota) {
        if (nota.length() <= 0) return;

        listaNotas.add(nota);
        listaDeNotasLiveData.setValue(listaNotas);
    }

}