package com.java.malcoln.candidato;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;


import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;



import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.java.malcoln.candidato.activity_Candidato.CandidatoActivity;
import com.java.malcoln.candidato.activity_Candidato.CapaFragment;
import com.java.malcoln.candidato.activity_Candidato.EventosListagem;
import com.java.malcoln.candidato.activity_Candidato.JinglesListagem;
import com.java.malcoln.candidato.activity_Candidato.PropostasListagem;

// import com.facebook.FacebookSdk;


public class MainActivity extends AppCompatActivity {
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private int mOpcaoSelecionada;

    private ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar2);
//        toolbar.setTitle("ONE - SERVIÇOS");
        //mToolbar.setSubtitle("just a subtitle");
        //mToolbar.setLogo(R.drawable.ic_face);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener(){
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem){
                        selecionarOpcaoMenu(menuItem);
                        return true;
                    }
                });
        if (savedInstanceState == null){
            mOpcaoSelecionada = R.id.nav_intro;
        } else {
            mOpcaoSelecionada = savedInstanceState.getInt("menuItem");
        }
        selecionarOpcaoMenu(mNavigationView.getMenu().findItem(mOpcaoSelecionada));
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("menuItem", mOpcaoSelecionada);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void selecionarOpcaoMenu(MenuItem menuItem){
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar2);
        Fragment fragment = null;
        mDrawerLayout.closeDrawers();
        int id = menuItem.getItemId();
        if (id == R.id.nav_intro) {
            fragment = new CapaFragment();
            toolbar.setTitle("ELEIÇÕES 2016");
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.commit();


        } else if (id == R.id.nav_mapa) {
            startActivity(new Intent(getBaseContext(), CandidatoActivity.class ));

        } else if (id == R.id.nav_onibus) {
            startActivity(new Intent(getBaseContext(), PropostasListagem.class));
            //fragment = new PropostasListagem();


        } else if (id == R.id.nav_empregos) {
            startActivity(new Intent(getBaseContext(), WebActivity.class));

        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(getBaseContext(), EventosListagem.class));

        } else if (id == R.id.nav_chat) {
            startActivity(new Intent(getBaseContext(),Main2Activity.class));

        } else if (id == R.id.nav_send) {
            startActivity(new Intent(getBaseContext(), JinglesListagem.class));
        }
          else if (id == R.id.nav_face) {
            startActivity(new Intent(getBaseContext(), MainActivityFaceB.class));
        }
        else if (id == R.id.nav_video) {
            startActivity(new Intent(getBaseContext(), YouTubeActivity.class));
        }
        else if (id == R.id.nav_msg) {
            startActivity(new Intent(getBaseContext(), FormContato.class));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu resource file.
        getMenuInflater().inflate(R.menu.menu_compartilhar, menu);
        MenuItem shareMenuItem = menu.findItem(R.id.action_share);

        if (shareMenuItem != null) {
            ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareMenuItem);
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "https://candidatomensageiro.blogspot.com.br");
            shareActionProvider.setShareIntent(shareIntent);
            //shareMenuItem.setIcon(R.drawable.ic_share_black_24dp);
        }

        return super.onCreateOptionsMenu(menu);
    }




    // FLOATING ACTION BUTTON
    // fab = (FloatingActionMenu) findViewById(R.id.fab);
    //}
}
