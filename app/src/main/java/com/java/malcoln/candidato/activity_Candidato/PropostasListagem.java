package com.java.malcoln.candidato.activity_Candidato;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.java.malcoln.candidato.Propostas;
import com.java.malcoln.candidato.PropostasAdapter;
import com.java.malcoln.candidato.R;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Malcoln T.I on 02/07/2016.
 */
public class PropostasListagem extends AppCompatActivity {
    List<Propostas> propostasList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PropostasAdapter mAdapter;
    Toolbar toolbar;
    public PropostasListagem(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_propostas);

        recyclerView = (RecyclerView) findViewById(R.id.rv_list);
        recyclerView.setHasFixedSize(true);
       // LinearLayoutManager llm = new LinearLayoutManager(this);
        android.support.v7.widget.GridLayoutManager llm = new android.support.v7.widget.GridLayoutManager(this,2);

        recyclerView.setLayoutManager(llm);

        mAdapter = new PropostasAdapter(propostasList);
        recyclerView.setAdapter(mAdapter);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getBaseContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Propostas propostas = propostasList.get(position);
                Toast.makeText(getBaseContext(), propostas.getLinha() + " Foi Selecionado", Toast.LENGTH_SHORT).show();

                //Fragment fragment = null;
                if (propostas.getId()==0){
                    startActivity(new Intent(getBaseContext(),Proposta_1_Activity.class));

                }
                else if (propostas.getId()==1){
                    startActivity(new Intent(getBaseContext(),Proposta_2_Activity.class));

                }
                if (propostas.getId()==2){
                    startActivity(new Intent(getBaseContext(),Proposta_3_Activity.class));
                }
                if (propostas.getId()==3){
                    startActivity(new Intent(getBaseContext(),Proposta_4_Activity.class));
                }
              /*  FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.commit(); */
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        prepareBusaoData();

        }

    private List<Propostas> prepareBusaoData() {

        propostasList.add(new Propostas("EDUCAÇÃO", "ESCOLAS",R.drawable.educacao3,0));
        propostasList.add(new Propostas("SAÚDE", "HOSPITAIS",R.drawable.saude,1));
        propostasList.add(new Propostas("TRANSPORTE", "MOBILIDADE",R.drawable.transporte,2));
        propostasList.add(new Propostas("ECONOMIA", "EMPREGOS",R.drawable.trabalhador,3));




        mAdapter.notifyDataSetChanged();
        return propostasList;
    }


    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private PropostasListagem.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final PropostasListagem.ClickListener clickListener) {
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

