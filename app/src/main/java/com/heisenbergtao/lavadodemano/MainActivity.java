package com.heisenbergtao.lavadodemano;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout botonselena, botonramito, botoncumbia,botonqueen, botonjuanga,botontusa,botonluismi,botonchona,botonrams;
    ColorDrawable dialogColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

        switch (view.getId()) {

            case R.id.btntusa:

                Intent intent11 = new Intent(this, CancionTusa.class);
                startActivity(intent11);
                finish();
                break;


            case R.id.btnramito:
                Intent intent011 = new Intent(this, CancionRamito.class);
                startActivity(intent011);
                finish();

                break;


            case R.id.btnchona:
                Intent intent111 = new Intent(this, CancionChona.class);
                startActivity(intent111);
                finish();


                break;


            case R.id.btncumbia:

                Intent intent112 = new Intent(this, CancionCumbia.class);
                startActivity(intent112);
                finish();
                break;


            case R.id.botonjuanga:
                Intent intent113 = new Intent(this, CancionJuanga.class);
                startActivity(intent113);
                finish();

                break;


            case R.id.btnluismi:

                Intent intent115 = new Intent(this, CancionLuismi.class);
                startActivity(intent115);
                finish();
                break;


            case R.id.btnqueen:
                Intent intent119 = new Intent(this, CancionQueen.class);
                startActivity(intent119);
                finish();

                finish();
                break;


            case R.id.btnselena:
                Intent intent116 = new Intent(this, CancionSelena.class);
                startActivity(intent116);
                finish();

                break;

            case R.id.btnqramst:
                Intent intent117 = new Intent(this, CancionDaHast.class);
                startActivity(intent117);
                finish();

                break;
        }

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
