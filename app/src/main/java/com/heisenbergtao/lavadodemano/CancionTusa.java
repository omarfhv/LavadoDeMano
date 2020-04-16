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

    MediaPlayer[] mp = new MediaPlayer[10];
    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;
    ColorDrawable dialogColor;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private GifImageView gifImageView;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    int numpista = 0;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mp[0] = MediaPlayer.create(this, R.raw.tus);
        mp[1] = MediaPlayer.create(this, R.raw.violetas);
        mp[2] = MediaPlayer.create(this, R.raw.chona);
        mp[3] = MediaPlayer.create(this, R.raw.jefes);
        mp[4] = MediaPlayer.create(this, R.raw.noa);
        mp[5] = MediaPlayer.create(this, R.raw.marchar);
        mp[6] = MediaPlayer.create(this, R.raw.queen);
        mp[7] = MediaPlayer.create(this, R.raw.carcacha);
        mp[8] = MediaPlayer.create(this, R.raw.du);


        //botonatras
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }
        setContentView(R.layout.activity_cancion_selena);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        SharedPreferences prefs = getApplicationContext().getSharedPreferences("save",0);
        SharedPreferences.Editor editor = prefs.edit();
        numpista = prefs.getInt("pista", 0);


        mTextViewCountDown = findViewById(R.id.text_view_countdown);

        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_reset);
        gifImageView = findViewById(R.id.Secuencias);


        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mTimerRunning) {
                    pauseTimer();
                    mp[numpista].pause();

                } else {
                    mp[numpista].start();
                    startTimer();

                    Toast.makeText(getApplicationContext(),"inicio", Toast.LENGTH_SHORT).show();


                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
                mp[numpista].stop();
                mp[numpista].prepareAsync();
                Toast.makeText(getApplicationContext(),"reset", Toast.LENGTH_SHORT).show();


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
                mButtonStartPause.setText("INICIO");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();


        mTimerRunning = true;
        mButtonStartPause.setText("PAUSE");
        mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {

        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("INICIO");
        mButtonReset.setVisibility(View.VISIBLE);
        Toast.makeText(getApplicationContext(),"Reiniciar", Toast.LENGTH_SHORT).show();

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

        if (seconds==0){

            final AlertDialog.Builder builderss12 = new AlertDialog.Builder(CancionTusa.this);
            final LayoutInflater inflaters12 = getLayoutInflater();
            View viss12 = inflaters12.inflate(R.layout.felicitacion, null);
            builderss12.setView(viss12);
            final AlertDialog dialogos12 = builderss12.create();
            dialogos12.setCancelable(true);
            dialogos12.getWindow().setBackgroundDrawable(dialogColor);
            Button botonokos12 = viss12.findViewById(R.id.botoncont);
            botonokos12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialogos12.dismiss();

                }
            });


            dialogos12.show();
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