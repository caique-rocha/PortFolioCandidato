package com.java.malcoln.candidato;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PropostasAdapter extends RecyclerView.Adapter<PropostasAdapter.MyViewHolder> {
    Context context;
    private List<Propostas> propostasList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView linha, trajeto;
        public ImageView imgCandidato;

        public MyViewHolder(View view) {
            super(view);
            linha = (TextView) view.findViewById(R.id.linha);
            trajeto = (TextView) view.findViewById(R.id.trajeto);
            imgCandidato = (ImageView) view.findViewById(R.id.imgCandidato);
        }
    }


    public PropostasAdapter(List<Propostas> busoesList) {
        this.propostasList = busoesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.propostas_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Propostas propostas = propostasList.get(position);
        holder.linha.setText(propostas.getLinha());
        holder.trajeto.setText(propostas.getTrajeto());
        holder.imgCandidato.setImageResource(propostas.getCandidato());
    }

    @Override
    public int getItemCount() {
        return propostasList.size();
    }
}
