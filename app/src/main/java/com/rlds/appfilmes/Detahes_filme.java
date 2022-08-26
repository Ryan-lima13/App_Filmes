package com.rlds.appfilmes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Detahes_filme extends AppCompatActivity {
    private ImageView dt_capaFilme, play_video;
    private TextView dt_tituloFilme, dt_DescricaoFilme, dt_elencoFilme;
    private Toolbar toolbarDetahes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detahes_filme);
        iniciarComponentes();
        getSupportActionBar().hide();
        // iniciar toolbar
        toolbarDetahes.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        String capa = getIntent().getExtras().getString("capa");
        String titulo = getIntent().getExtras().getString("titulo");
        String descricao = getIntent().getExtras().getString("descricao");
        String elenco = getIntent().getExtras().getString("elenco");
        String video = getIntent().getExtras().getString("video");

        String stVideo = video;

        Glide.with(getApplicationContext()).load(capa).into(dt_capaFilme);
        dt_tituloFilme.setText(titulo);
        dt_DescricaoFilme.setText(descricao);
        dt_elencoFilme.setText(elenco);
        // abrir video
        play_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Video.class);
                intent.putExtra("video", stVideo);
                startActivity(intent);
            }
        });
    }
    private void iniciarComponentes(){
        dt_capaFilme = findViewById(R.id.dt_capaFilme);
        play_video = findViewById(R.id.play_video);
        dt_tituloFilme = findViewById(R.id.dt_tituloFilme);
        dt_DescricaoFilme = findViewById(R.id.dt_descricaoFilme);
        dt_elencoFilme = findViewById(R.id.dt_elencoFilme);
        toolbarDetahes = findViewById(R.id.toolbar_detalhes);
    }
}