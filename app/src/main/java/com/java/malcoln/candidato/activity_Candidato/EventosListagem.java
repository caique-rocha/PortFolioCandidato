package com.java.malcoln.candidato.activity_Candidato;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.java.malcoln.candidato.Eventos;
import com.java.malcoln.candidato.R;
import com.java.malcoln.candidato.EventosAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by T.I on 02/07/2016.
 */
public class EventosListagem extends AppCompatActivity {
    List<Eventos> eventosList = new ArrayList<>();
    private RecyclerView recyclerView;
    private EventosAdapter mAdapter;
    Toolbar toolbar;
    public EventosListagem(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_eventos);

        recyclerView = (RecyclerView) findViewById(R.id.rv_lista);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        //android.support.v7.widget.GridLayoutManager llm = new android.support.v7.widget.GridLayoutManager(this,2);

        recyclerView.setLayoutManager(llm);

        mAdapter = new EventosAdapter(eventosList);
        recyclerView.setAdapter(mAdapter);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getBaseContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Eventos eventos = eventosList.get(position);
                Toast.makeText(getBaseContext(), eventos.getLinha() + " Foi Selecionado", Toast.LENGTH_SHORT).show();

                //Fragment fragment = null;
                if (eventos.getId()==0){
                    startActivity(new Intent(getBaseContext(),Proposta_1_Activity.class));

                }
                else if (eventos.getId()==1){
                    startActivity(new Intent(getBaseContext(),Proposta_2_Activity.class));

                }
                if (eventos.getId()==2){
                    startActivity(new Intent(getBaseContext(),Proposta_3_Activity.class));
                }
                if (eventos.getId()==3){
                    startActivity(new Intent(getBaseContext(),Proposta_1_Activity.class));
                }
              /*  FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.commit();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        })); */
        prepareEventosData();

        }

    private List<Eventos> prepareEventosData() {

        eventosList.add(new Eventos("Evento Beneficiente", "Rua XXXX,105","20:00",R.drawable.arte_calendario,0));
        eventosList.add(new Eventos("COMICIO PARTIDO", "PRAÇA DA REPÚBLICA","19:00",R.drawable.arte_calendario2,1));
        eventosList.add(new Eventos("EVENTO SOLIDÁRIO", "COMUNIDADE LOCAL","20:00",R.drawable.arte_calendario3,2));
        eventosList.add(new Eventos("Evento Beneficiente", "Rua XXXX,105","20:00",R.drawable.arte_calendario,0));
        eventosList.add(new Eventos("CAMPANHA", "COMITÊ PARTIDO - Rua Montes Claros,20","20:00",R.drawable.arte_calendario4,3));
        eventosList.add(new Eventos("CAMPANHA", "COMITÊ PARTIDO - Rua Montes Claros,20","20:00",R.drawable.arte_calendario4,3));
        eventosList.add(new Eventos("EVENTO SOLIDÁRIO", "COMUNIDADE LOCAL","20:00",R.drawable.arte_calendario3,2));
        eventosList.add(new Eventos("CAMPANHA", "COMITÊ PARTIDO - Rua Montes Claros,20","20:00",R.drawable.arte_calendario4,3));
        eventosList.add(new Eventos("CAMPANHA", "COMITÊ PARTIDO - Rua Montes Claros,20","20:00",R.drawable.arte_calendario4,3));





        mAdapter.notifyDataSetChanged();
        return eventosList;
    }


    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private EventosListagem.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final EventosListagem.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Id correspondente ao botão Up/Home da actionbar
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }



}


 /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_propostas);
        listView = new ListView(this);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));

        busoes = new ArrayList<Busao>();
        busoes.add(new Busao("14557","Cú.rralinhos-Reguinhos"));
        busoes.add(new Busao("14557","Cú.rralinhos-Reguinhos"));
        busoes.add(new Busao("14557","Cú.rralinhos-Reguinhos"));
        busoes.add(new Busao("14557","Cú.rralinhos-Reguinhos"));
        busoes.add(new Busao("14557","Cú.rralinhos-Reguinhos"));
        busoes.add(new Busao("14557","Cú.rralinhos-Reguinhos"));
        busoes.add(new Busao("14557","Cú.rralinhos-Reguinhos"));
        busoes.add(new Busao("14557","Cú.rralinhos-Reguinhos"));
        adapter = new BusaoAdapter(this, busoes);
       listView.setAdapter(adapter);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
  /*  @Override
    public Intent getParentActivityIntent() {
        Intent it = super.getParentActivityIntent();
        if (it != null) {
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        return it;
    } */

