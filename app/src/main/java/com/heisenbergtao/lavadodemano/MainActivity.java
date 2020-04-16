package com.heisenbergtao.lavadodemano;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout botonselena, botonramito, botoncumbia,botonqueen, botonjuanga,botontusa,botonluismi,botonchona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        botonselena = findViewById(R.id.btnselena);
        botonselena.setOnClickListener(this);

        botonramito = findViewById(R.id.btnramito);
        botonramito.setOnClickListener(this);

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
        }

    }
}
