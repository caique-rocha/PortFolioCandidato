package com.java.malcoln.candidato.activity_Candidato;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.java.malcoln.candidato.R;

import java.util.concurrent.TimeUnit;


public class PlayerJingle extends AppCompatActivity {


    private ImageView iv,b1,b2,b3,b4;;
    private MediaPlayer mediaPlayer;
    private double startTime = 0;
    private double finalTime = 0;
    private Handler myHandler = new Handler();;
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private SeekBar seekbar;
    private TextView tx1,tx2,tx3;
    Toolbar toolbar;

    public PlayerJingle(){}

    public static int oneTimeOnly = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jingle);

        toolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        b1 = (ImageView) findViewById(R.id.button);
        b2 = (ImageView) findViewById(R.id.button2);
        b3=(ImageView) findViewById(R.id.button3);
        b4=(ImageView) findViewById(R.id.button4);
        iv=(ImageView)findViewById(R.id.imageView);

        tx1=(TextView)findViewById(R.id.textView2);
        tx2=(TextView)findViewById(R.id.textView3);
        tx3=(TextView)findViewById(R.id.textView4);
        tx3.setText("Jingle.mp3");

        mediaPlayer = MediaPlayer.create(this, R.raw.janio);
        seekbar=(SeekBar)findViewById(R.id.seekBar);
        seekbar.setClickable(false);
        b2.setEnabled(false);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "JINGLE TOCANDO!!!",Toast.LENGTH_SHORT).show();
                mediaPlayer.start();

                finalTime = mediaPlayer.getDuration();
                startTime = mediaPlayer.getCurrentPosition();

                if (oneTimeOnly == 0) {
                    seekbar.setMax((int) finalTime);
                    oneTimeOnly = 1;
                }
                    tx2.setText(String.format("%d min, %d sec",
                     TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                     TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) finalTime)))
                    );

                    tx1.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime)))
                    );

                    seekbar.setProgress((int)startTime);
                    myHandler.postDelayed(UpdateSongTime,100);
                    b2.setEnabled(true);
                    b3.setEnabled(false);
                    }
                    });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Parou SOM",Toast.LENGTH_SHORT).show();
                mediaPlayer.pause();
                b2.setEnabled(false);
                b3.setEnabled(true);
                }
        });

        b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        int temp = (int)startTime;

        if((temp+forwardTime)<=finalTime){
        startTime = startTime + forwardTime;
        mediaPlayer.seekTo((int) startTime);
        Toast.makeText(getApplicationContext(),"Você avançou 5 segundos",Toast.LENGTH_SHORT).show();
        }
        else{
        Toast.makeText(getApplicationContext(),"Não é possível avançar ",Toast.LENGTH_SHORT).show();
        }
        }
        });

        b4.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        int temp = (int)startTime;

        if((temp-backwardTime)>0){
        startTime = startTime - backwardTime;
        mediaPlayer.seekTo((int) startTime);
        Toast.makeText(getApplicationContext(),"Você retrocedeu 5 segundos",Toast.LENGTH_SHORT).show();
        }
        else{
        Toast.makeText(getApplicationContext(),"Não é possível retroceder",Toast.LENGTH_SHORT).show();
        }
        }
        });
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Id correspondente ao botão Up/Home da actionbar
            case android.R.id.button1:
                mediaPlayer.stop();
                finish();
            case android.R.id.home:
                mediaPlayer.stop();
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if(mediaPlayer.isPlaying())
            mediaPlayer.stop();
        else
            return;
    }

    @Override
    public void onStop()
    {
        super.onStop();
        if(mediaPlayer.isPlaying())
            mediaPlayer.stop();
        else
            return;
    }



    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
        startTime = mediaPlayer.getCurrentPosition();
        tx1.setText(String.format("%d min, %d sec",

        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
        toMinutes((long) startTime)))
        );
        seekbar.setProgress((int)startTime);
        myHandler.postDelayed(this, 100);
        }
        };


}

