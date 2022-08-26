package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rlds.appfilmes.R;

import java.util.ArrayList;
import java.util.List;

import Model.Filmes;

public class AdapterFilmes  extends RecyclerView.Adapter<AdapterFilmes.MyViewHolder> {
    private Context context;
    private List<Filmes> filmesList;

    public AdapterFilmes(Context context, List<Filmes> filmesList) {
        this.context = context;
        this.filmesList = filmesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        itemLista =layoutInflater.inflate(R.layout.filme_iten,parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(filmesList.get(position).getCapa()).into(holder.imageViewCapa);
        holder.titulo.setText(filmesList.get(position).getTitulo());

    }

    @Override
    public int getItemCount() {
        return filmesList.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageViewCapa;
        private TextView titulo, descricao, elenco;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCapa = itemView.findViewById(R.id.capaFilme);
            titulo = itemView.findViewById(R.id.tituloFilme);
            descricao = itemView.findViewById(R.id.dt_descricaoFilme);
            elenco = itemView.findViewById(R.id.dt_elencoFilme);
        }
    }
}
