package com.example.savings;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


public class CadastroGastosActivity extends AppCompatActivity
{

    ImageView imgRecibo;
    Button btUpload;
    Uri image_uri;
    Spinner spinnerCategorias;
    String categoria;
    Button btEnviar;
    Integer REQUEST_CAMERA = 1, SELECT_FILE = 0;
    EditText nome, descricao, valor;

    private BDSQLiteHelper bd;
    private static final int IMAGE_PICK_CODE = 1001;
    private static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_CAPTURE_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_gastos);

        imgRecibo = (ImageView) findViewById(R.id.imgRecibo);
        btUpload = (Button) findViewById(R.id.btImagem);
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


        //region upload imagem
        btUpload.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               SelectImage();
            }
        });
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
    
    private void SelectImage()
    {
        final CharSequence[] itens = {"Câmera", "Galeria", "Cancelar"};

        AlertDialog.Builder builder = new AlertDialog.Builder(CadastroGastosActivity.this);
        builder.setTitle(R.string.tituloAlerta);

        builder.setItems(itens, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                if (itens[which].equals("Câmera"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                }
                else if (itens[which].equals("Galeria"))
                {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent, SELECT_FILE);
                }
                else if (itens[which].equals("Cancelar"))
                {
                    dialog.dismiss();
                }
            }
        });

        builder.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== Activity.RESULT_OK)
        {

            if(requestCode==REQUEST_CAMERA)
            {
                    imgRecibo.setImageURI(image_uri);
            }
            else if(requestCode==SELECT_FILE)
            {
                    imgRecibo.setImageURI(data.getData());
            }

        }
    }
}