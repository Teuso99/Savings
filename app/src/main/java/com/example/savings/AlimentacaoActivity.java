package com.example.savings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(AlimentacaoActivity.this, AlterarGastoActivity.class);
                intent.putExtra("ID", listaGastos.get(position).getId());
                startActivity(intent);
            }
        });
    }
}
