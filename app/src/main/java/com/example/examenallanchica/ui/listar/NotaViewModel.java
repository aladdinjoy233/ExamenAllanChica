package com.example.examenallanchica.ui.listar;

import static com.example.examenallanchica.MainActivity.listaNotas;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotaViewModel extends ViewModel {

    private MutableLiveData<String> nota = new MutableLiveData<>();

    public LiveData<String> getNota() { return nota; }

    public void procesarDatos(Bundle bundle) {
        if (bundle == null) {
            nota.setValue("");
            return;
        }

        String varNota = bundle.getString("nota");
        if (varNota != null) nota.setValue(varNota);
    }

    public void eliminarNota() {
        listaNotas.remove(nota.getValue());
    }
}