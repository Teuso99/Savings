package com.example.savings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<Categoria>
{
    private final Context context;
    private final ArrayList<Categoria> elementos;

    public Adapter(Context context, ArrayList<Categoria> elementos)
    {
        super(context, R.layout.linha, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha, parent, false);
        TextView nomeCategoria = (TextView) rowView.findViewById(R.id.nome);
        TextView descricao = (TextView) rowView.findViewById(R.id.descricao);
        ImageView imagem = (ImageView) rowView.findViewById(R.id.imagem);
        nomeCategoria.setText(elementos.get(position).getNome());
        descricao.setText(elementos.get(position).getDescricao());
        imagem.setImageResource(elementos.get(position).getImagem());
        return rowView;
    }
}
