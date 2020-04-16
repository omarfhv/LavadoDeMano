package com.heisenbergtao.lavadodemano;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout botonselena, botonramito, botoncumbia,botonqueen, botonjuanga,botontusa,botonluismi,botonchona,botonrams;
    ColorDrawable dialogColor;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {
                }
            });
            mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);



        botonselena = findViewById(R.id.btnselena);
        botonselena.setOnClickListener(this);

        botonramito = findViewById(R.id.btnramito);
        botonramito.setOnClickListener(this);

        botonrams = findViewById(R.id.btnqramst);
        botonrams.setOnClickListener(this);

        botoncumbia = findViewById(R.id.btncumbia);
        botoncumbia.setOnClickListener(this);

        botonqueen = findViewById(R.id.btnqueen);
        botonqueen.setOnClickListener(this);

        botontusa = findViewById(R.id.btntusa);
        botontusa.setOnClickListener(this);

        botonchona = findViewById(R.id.btnchona);
        botonchona.setOnClickListener(this);

        botonluismi = findViewById(R.id.btnluismi);
        botonluismi.setOnClickListener(this);

        botonjuanga = findViewById(R.id.botonjuanga);
        botonjuanga.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        SharedPreferences prefs = getApplicationContext().getSharedPreferences("save",0);
        SharedPreferences.Editor editor = prefs.edit();

        switch (view.getId()) {

            case R.id.btntusa:
                editor.putInt("pista", 0);
                editor.commit();
                break;


            case R.id.btnramito:
                editor.putInt("pista", 1);
                editor.commit();
                break;


            case R.id.btnchona:
                editor.putInt("pista", 2);
                editor.commit();
                break;


            case R.id.btncumbia:
                editor.putInt("pista", 3);
                editor.commit();
                break;


            case R.id.botonjuanga:
                editor.putInt("pista", 4);
                editor.commit();
                break;


            case R.id.btnluismi:
                editor.putInt("pista", 5);
                editor.commit();
                break;


            case R.id.btnqueen:
                editor.putInt("pista", 6);
                editor.commit();
                break;


            case R.id.btnselena:
                editor.putInt("pista", 7);
                editor.commit();
                break;

            case R.id.btnqramst:
                editor.putInt("pista", 8);
                editor.commit();
                break;
        }
        Intent intent = new Intent(this, CancionTusa.class);
        startActivity(intent);
        finish();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();

            View vi = inflater.inflate(R.layout.dialogconfirm, null);
            builder.setView(vi);


            final AlertDialog dialog = builder.create();
            dialog.getWindow().setBackgroundDrawable(dialogColor);

            //decidir despues si sera cancelable o no
            dialog.setCancelable(false);
            Button botonsi = vi.findViewById(R.id.botonsi);
            botonsi.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                            MainActivity.super.onDestroy();
                            System.exit(0);
                        }
                    }
            );
            Button botonno = vi.findViewById(R.id.botonno);
            botonno.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();

                        }
                    }
            );
            dialog.show();
            //Metodos.dialogo( this, getLayoutInflater(), "¿seguro deseas salir de la aplicacion?", 0 );
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
