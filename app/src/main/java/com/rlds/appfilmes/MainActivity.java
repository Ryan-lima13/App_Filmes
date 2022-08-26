package com.rlds.appfilmes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import Adapter.AdapterFilmes;
import Model.FilmeApi;
import Model.Filmes;
import RecyclerItemClickListener.RecyclerItemClickListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView_filmes;
    private AdapterFilmes adapterFilmes;
    private List<Filmes>filmeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarComponentes();
        filmeList = new ArrayList<>();
        // eventos  de clique no recyclerView
        recyclerView_filmes.addOnItemTouchListener( new RecyclerItemClickListener(
                getApplicationContext(),
                recyclerView_filmes,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getApplicationContext(),Detahes_filme.class);
                        intent.putExtra("capa",filmeList.get(position).getCapa());
                        intent.putExtra("titulo",filmeList.get(position).getTitulo());
                        intent.putExtra("descricao",filmeList.get(position).getDescricao());
                        intent.putExtra("elenco", filmeList.get(position).getElenco());
                        intent.putExtra("video", filmeList.get(position).getVideo());
                        startActivity(intent);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                }
        ));




        // configurar retrofite
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://firebasestorage.googleapis.com/v0/b/chat-d26ff.appspot.com/o/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        // iniciar retrofit
        FilmeApi filmeApi = retrofit.create(FilmeApi.class);
        Call<List<Filmes>>call = filmeApi.getFilmes();
        call.enqueue(new Callback<List<Filmes>>() {
            @Override
            public void onResponse(Call<List<Filmes>> call, Response<List<Filmes>> response) {
                if(response.code() != 200){
                    return;

                }
                List<Filmes> filmes = response.body();
                for ( Filmes filme: filmes){
                    filmeList.add(filme);

                }
                adapterFilmes = new AdapterFilmes(getApplicationContext(),filmeList);
                recyclerView_filmes.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                recyclerView_filmes.setHasFixedSize(true);
                recyclerView_filmes.setAdapter(adapterFilmes);
            }

            @Override
            public void onFailure(Call<List<Filmes>> call, Throwable t) {

            }
        });



    }
    private void  iniciarComponentes(){
        recyclerView_filmes = findViewById(R.id.recyclerViewFilmes);
    }


}