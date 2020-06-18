package com.example.savings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AlimentacaoActivity extends AppCompatActivity
{
    private BDSQLiteHelper bd;
    ArrayList<Gasto> listaGastos;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimentacao);
        bd = new BDSQLiteHelper(this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        ListView lista = (ListView) findViewById(R.id.listaAlimentacao);
        listaGastos = bd.getGastosAlimentacao();
        GastoAdapter adapter = new GastoAdapter(this, listaGastos);
        lista.setAdapter(adapter);
    }
}
