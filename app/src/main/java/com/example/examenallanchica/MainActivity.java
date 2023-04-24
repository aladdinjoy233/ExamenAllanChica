package com.example.examenallanchica;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.examenallanchica.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> listaNotas = new ArrayList<>();
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cargarListaDeNotas();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getMutableDialogoSalida().observe(this, dialogoSalida -> {
            if (dialogoSalida) mostrarDialogoSalida(this);
        });

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_listar)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(menuItem -> {
            int selectedItem = menuItem.getItemId();

//            Si seleccionan listar, que vaya al fragment
            if (selectedItem == R.id.nav_listar) {
                navController.navigate(selectedItem);
                drawer.closeDrawers();
                return true;
            }

//            Si seleccionan salir, que abra el modal de salida
            if (selectedItem == R.id.nav_salir) {
                viewModel.dialogoSalida();
                return true;
            }

            return false;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void cargarListaDeNotas() {
        listaNotas = new ArrayList<>();
        listaNotas.add("Lavar la ropa");
        listaNotas.add("Sacar a pasear al perro");
        listaNotas.add("Aca voy a probar una nota muy larga para ver como se ve en la lista de notas");
        listaNotas.add("Realizar el examen de moviles");
        listaNotas.add("Dormir una siesta");
    }

    private void mostrarDialogoSalida(Context context) {
        new AlertDialog.Builder(context)
                .setTitle("Salir")
                .setMessage("¿Desea salir de la aplicación?")
                .setPositiveButton("Si", (dialog, which) -> ((Activity) context).finishAffinity() )
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .show();
    }
}