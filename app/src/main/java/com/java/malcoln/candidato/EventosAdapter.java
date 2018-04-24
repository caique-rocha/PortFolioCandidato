package com.java.malcoln.candidato;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class EventosAdapter extends RecyclerView.Adapter<EventosAdapter.MyViewHolder> {
    Context context;
    private List<Eventos> eventosList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView evento, trajeto, horario;
        public ImageView imgCandidato;

        public MyViewHolder(View view) {
            super(view);
            evento = (TextView) view.findViewById(R.id.evento);
            trajeto = (TextView) view.findViewById(R.id.trajeto);
            horario = (TextView) view.findViewById(R.id.horario);
            imgCandidato = (ImageView) view.findViewById(R.id.imgCandidato);
        }
    }


    public EventosAdapter(List<Eventos> eventosList) {
        this.eventosList = eventosList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.eventos_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Eventos eventos = eventosList.get(position);
        holder.evento.setText(eventos.getLinha());
        holder.trajeto.setText(eventos.getTrajeto());
        holder.horario.setText(eventos.getHorario());
        holder.imgCandidato.setImageResource(eventos.getCandidato());
    }

    @Override
    public int getItemCount() {
        return eventosList.size();
    }
}
