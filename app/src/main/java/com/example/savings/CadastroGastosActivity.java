package com.example.savings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class CadastroGastosActivity extends AppCompatActivity
{

    Spinner spinnerCategorias;
    String categoria;
    Button btEnviar;
    EditText nome, descricao, valor;

    private BDSQLiteHelper bd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_gastos);

        spinnerCategorias = (Spinner) findViewById(R.id.spinnerCategorias);
        btEnviar = (Button) findViewById(R.id.btEnviar);
        nome = (EditText) findViewById(R.id.nomeGasto);
        descricao = (EditText) findViewById(R.id.DescricaoGasto);
        categoria = null;
        valor = (EditText) findViewById(R.id.valorGasto);
        bd = new BDSQLiteHelper(this);

        //region spinner Categorias
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.arrayCategorias, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCategorias.setAdapter(adapter);
        //endregion



        //region envio formulário
        btEnviar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                categoria = spinnerCategorias.getSelectedItem().toString();
                Gasto gasto = new Gasto();

                gasto.setTitulo(nome.getText().toString());
                gasto.setDescricao(descricao.getText().toString());
                gasto.setCategoria(categoria);
                gasto.setValor(Double.parseDouble(valor.getText().toString()));

                bd.addGasto(gasto);

                Toast.makeText(getBaseContext(), "Gasto cadastrado!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(CadastroGastosActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //endregion

    }
}