package com.example.savings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lista = (ListView) findViewById(R.id.listaCategoria);
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        final ArrayList<Categoria> categorias = adicionarCategorias();
        ArrayAdapter adapter = new Adapter(this, categorias);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if (position == 0)
                {
                    Intent intent = new Intent(MainActivity.this, EducacaoActivity.class);
                    startActivity(intent);
                }
                else if (position == 1)
                {
                    Intent intent = new Intent(MainActivity.this, ServicosActivity.class);
                    startActivity(intent);
                }
                else if (position == 2)
                {
                    Intent intent = new Intent(MainActivity.this, EntretenimentoActivity.class);
                    startActivity(intent);
                }
                else if (position == 3)
                {
                    Intent intent = new Intent(MainActivity.this, AlimentacaoActivity.class);
                    startActivity(intent);
                }
                else if (position == 4)
                {
                    Intent intent = new Intent(MainActivity.this, OutrosActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getBaseContext(), "Ocorreu um erro. Por favor, tente novamente.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, CadastroGastosActivity.class);
                startActivity(intent);
            }
        });
    }

    private ArrayList<Categoria> adicionarCategorias()
    {
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();

        Categoria c = new Categoria("Educação", "Gastos com educação", R.drawable.ic_baseline_book_24);
        categorias.add(c);

        c = new Categoria("Serviços", "Gastos com serviços", R.drawable.ic_baseline_build_24);
        categorias.add(c);

        c = new Categoria("Entretenimento", "Gastos com entretenimento", R.drawable.ic_baseline_golf_course_24);
        categorias.add(c);

        c = new Categoria("Alimentação", "Gastos com alimentação", R.drawable.ic_baseline_fastfood_24);
        categorias.add(c);

        c = new Categoria("Outros", "Outros gastos que não se enquadram nas categorias acimas", R.drawable.ic_baseline_emoji_flags_24);
        categorias.add(c);

        return categorias;
    }
}
