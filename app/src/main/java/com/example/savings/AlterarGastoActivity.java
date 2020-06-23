package com.example.savings;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AlterarGastoActivity extends AppCompatActivity
{

    private BDSQLiteHelper bd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_gasto);

        Intent intent = getIntent();
        final int id = intent.getIntExtra("ID",0);
        bd = new BDSQLiteHelper(this);
        final Gasto gasto = bd.getGasto(id);

        final EditText nome =  (EditText) findViewById(R.id.editNome);
        final EditText descricao =  (EditText) findViewById(R.id.editDescricao);
        final EditText valor = (EditText) findViewById(R.id.editValor);
        final Spinner spinner = (Spinner) findViewById(R.id.spinnerEditar);

        nome.setText(gasto.getTitulo());
        descricao.setText(gasto.getDescricao());
        valor.setText(String.valueOf(gasto.getValor()));

        //region trabalhando spinner para setar valor correspondente ao gasto
        final String categoria = gasto.getCategoria();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.arrayCategorias, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        switch (categoria)
        {
            case "Alimentação":
                spinner.setSelection(3);
            case "Educação":
                spinner.setSelection(0);
            case "Entretenimento":
                spinner.setSelection(2);
            case "Serviços":
                spinner.setSelection(1);
            case "Outros":
                spinner.setSelection(4);
        }
        //endregion

        Button alterar = (Button) findViewById(R.id.btEditar);
        alterar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                gasto.setTitulo(nome.getText().toString());
                gasto.setDescricao(descricao.getText().toString());
                gasto.setValor(Double.parseDouble(valor.getText().toString()));
                gasto.setCategoria(spinner.getSelectedItem().toString());
                bd.updateGasto(gasto);

                Toast.makeText(getBaseContext(), "Gasto alterado!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AlterarGastoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button excluir = (Button) findViewById(R.id.btExcluir);
        excluir.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(AlterarGastoActivity.this);
                builder.setTitle(R.string.tituloExcluir);

                builder.setPositiveButton(R.string.alertPositivo, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        bd.deleteGasto(gasto);

                        Toast.makeText(getBaseContext(), "Gasto excluído!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(AlterarGastoActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton(R.string.alertNegativo, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });


                builder.show();


            }
        });

    }
}