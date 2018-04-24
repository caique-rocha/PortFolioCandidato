package com.java.malcoln.candidato.activity_Candidato;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.java.malcoln.candidato.FormContato;
import com.java.malcoln.candidato.Main2Activity;
import com.java.malcoln.candidato.MainActivityFaceB;
import com.java.malcoln.candidato.R;
import com.java.malcoln.candidato.WebActivity;
import com.java.malcoln.candidato.YouTubeActivity;


/**
 * Created by T.I on 30/06/2016.
 */
public class CapaFragment extends Fragment implements View.OnClickListener{

    ShareActionProvider mShareActionProvider;
    //private AdView mAdView;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.capa_fragment, container, false);
        ImageView proposta = (ImageView) layout.findViewById(R.id.nav_onibus);
        proposta.setOnClickListener(this);
        ImageView candidato = (ImageView)layout.findViewById(R.id.nav_mapa);
        candidato.setOnClickListener(this);
        ImageView eventos = (ImageView)layout.findViewById(R.id.nav_manage);
        eventos.setOnClickListener(this);
        ImageView jingle = (ImageView)layout.findViewById(R.id.nav_send);
        jingle.setOnClickListener(this);
        ImageView chat = (ImageView)layout.findViewById(R.id.nav_chat);
        chat.setOnClickListener(this);
        ImageView face = (ImageView)layout.findViewById(R.id.nav_face);
        face.setOnClickListener(this);
        ImageView msg = (ImageView)layout.findViewById(R.id.nav_msg);
        msg.setOnClickListener(this);
        ImageView video = (ImageView)layout.findViewById(R.id.nav_video);
        video.setOnClickListener(this);
        ImageView noticias = (ImageView)layout.findViewById(R.id.nav_noticias);
        noticias.setOnClickListener(this);

        // Initialize and request AdMob ad.
        //mAdView = (AdView) layout.findViewById(R.id.adView);
        //AdRequest adRequest = new AdRequest.Builder().build();
        //mAdView.loadAd(adRequest);

        return layout;
    }
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.nav_onibus:
                startActivity(new Intent(getContext(), PropostasListagem.class));
            break;
            case R.id.nav_mapa:
                startActivity(new Intent(getContext(), CandidatoActivity.class));
            break;
            case R.id.nav_manage:
                startActivity(new Intent(getContext(), EventosListagem.class));
                break;
            case R.id.nav_send:
                startActivity(new Intent(getContext(), JinglesListagem.class));
                break;
            case R.id.nav_chat:
                startActivity(new Intent(getContext(), Main2Activity.class));
                break;
            case R.id.nav_face:
                startActivity(new Intent(getContext(), MainActivityFaceB.class));
                break;
            case R.id.nav_msg:
                startActivity(new Intent(getContext(), FormContato.class));
                break;
            case R.id.nav_video:
                startActivity(new Intent(getContext(), YouTubeActivity.class));
                break;
            case R.id.nav_noticias:
                startActivity(new Intent(getContext(), WebActivity.class));
                break;
        }
    }

   /* @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    } */


}