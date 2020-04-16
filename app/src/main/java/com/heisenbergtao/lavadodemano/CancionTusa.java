package com.heisenbergtao.lavadodemano;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;

public class CancionTusa extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 40000;

    MediaPlayer[] mp = new MediaPlayer[9];


    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;
    ColorDrawable dialogColor;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    ImageView pasos;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    int numpista = 0;
    private AdView mAdView;
    int imagenes[] = new int[]{R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5, R.drawable.c66, R.drawable.c7, R.drawable.c8};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //botonatras
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }
        setContentView(R.layout.activity_cancion_selena);

        mp = new MediaPlayer[]{MediaPlayer.create(this, R.raw.tus), MediaPlayer.create(this, R.raw.violetas),
                MediaPlayer.create(this, R.raw.chona), MediaPlayer.create(this, R.raw.jefes), MediaPlayer.create(this, R.raw.noa),
                MediaPlayer.create(this, R.raw.marchar), MediaPlayer.create(this, R.raw.queen), MediaPlayer.create(this, R.raw.carcacha),
                MediaPlayer.create(this, R.raw.du)};


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        SharedPreferences prefs = getApplicationContext().getSharedPreferences("save", 0);
        numpista = prefs.getInt("pista", 0);


        mTextViewCountDown = findViewById(R.id.text_view_countdown);

        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_reset);
        pasos = findViewById(R.id.secuencias);


        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mTimerRunning) {
                    pauseTimer();
                    mp[numpista].pause();

                } else {
                    mp[numpista].start();
                    startTimer();



                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
                mp[numpista].stop();
                mp[numpista].prepareAsync();


            }
        });

        updateCountDownText();
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();


            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Inicio");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();


        mTimerRunning = true;
        mButtonStartPause.setText("Pausa");
        mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {

        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Continuar");
        mButtonReset.setVisibility(View.VISIBLE);

    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);

    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);

        switch (seconds) {
            case 40:
                pasos.setImageResource(imagenes[0]);
                break;
            case 35:
                pasos.setImageResource(imagenes[1]);
                break;
            case 30:
                pasos.setImageResource(imagenes[2]);
                break;
            case 25:
                pasos.setImageResource(imagenes[3]);
                break;
            case 20:
                pasos.setImageResource(imagenes[4]);
                break;
            case 15:
                pasos.setImageResource(imagenes[5]);
                break;


            case 0:
                final AlertDialog.Builder builder = new AlertDialog.Builder(CancionTusa.this);
                final LayoutInflater inflaters = getLayoutInflater();
                View vis = inflaters.inflate(R.layout.felicitacion, null);
                builder.setView(vis);
                final AlertDialog dialogo = builder.create();
                dialogo.setCancelable(true);
                dialogo.getWindow().setBackgroundDrawable(dialogColor);
                Button botonokos12 = vis.findViewById(R.id.botoncont);
                botonokos12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialogo.dismiss();

                    }
                });


                dialogo.show();
                break;

        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            startActivity(new Intent(getBaseContext(), MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
            finish();
            mp[numpista].stop();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(getBaseContext(), MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
            finish();
            mp[numpista].stop();

        }


        return super.onOptionsItemSelected(item);
    }

}