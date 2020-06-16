package com.example.savings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;

public class CadastroGastosActivity extends AppCompatActivity
{

    ImageView imgRecibo;
    Button btUpload;
    Uri image_uri;
    Spinner spinnerCategorias;
    String itemSpinner;
    Button btEnviar;
    Integer REQUEST_CAMERA = 1, SELECT_FILE = 0;
    Bitmap thumbnail;

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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.arrayCategorias, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCategorias.setAdapter(adapter);

        //pegar o item do spinner:
        itemSpinner = spinnerCategorias.getSelectedItem().toString();

        btUpload.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               SelectImage();
            }
        });

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
                try
                {
                    imgRecibo.setImageURI(image_uri);

                    //thumbnail = (Bitmap) data.getExtras().get("data");

                    final InputStream imageStream;

                    imageStream = getContentResolver().openInputStream(image_uri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }

            }
            else if(requestCode==SELECT_FILE)
            {
                try
                {
                    imgRecibo.setImageURI(data.getData());
                    final InputStream imageStream;

                    imageStream = getContentResolver().openInputStream(image_uri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }

            }

        }
    }
}