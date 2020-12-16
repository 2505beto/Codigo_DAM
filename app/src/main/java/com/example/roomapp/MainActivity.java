package com.example.roomapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText etTitulo, etDescripcion, etGenero, etDirector;
    PeliculaDB db;
    TextView tvMostrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitulo = findViewById(R.id.etTitulo);
        etDescripcion = findViewById(R.id.etDescripcion);
        etGenero = findViewById(R.id.etGenero);
        etDirector = findViewById(R.id.etDirector);

        db = Room.databaseBuilder(getApplicationContext(), PeliculaDB.class, "Peliculas.db")
                .allowMainThreadQueries()
                .build();

        tvMostrar = findViewById(R.id.tvMostrar);
        List<Pelicula> peliculas = new ArrayList<Pelicula>();
        peliculas = db.peliculaDAO().PeliculasTodas();
        tvMostrar.setText("");
        for (Pelicula item : peliculas){
            tvMostrar.append("Titulo: "+item.titulo+ "\n"+
                             "Descripcion: "+item.descripcion+ "\n"+
                             "Genero: "+item.genero+ "\n"+
                             "Director: "+item.director+ "\n"
                              );
        }
    }

    public void GuardarInfo(View view) {


        String titulo = etTitulo.getText().toString();
        String desripcion = etDescripcion.getText().toString();
        String genero = etGenero.getText().toString();
        String director = etDirector.getText().toString();

        Pelicula pelicula = new Pelicula();
        pelicula.titulo = titulo;
        pelicula.descripcion = desripcion;
        pelicula.genero = genero;
        pelicula.director = director;
        db.peliculaDAO().InsertarPelicula(pelicula);
    }
}