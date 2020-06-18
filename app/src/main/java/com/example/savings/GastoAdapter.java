package com.example.savings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GastoAdapter extends ArrayAdapter<Gasto>
{
    private final Context context;
    private final ArrayList<Gasto> elementos;

    public GastoAdapter(Context context, ArrayList<Gasto> elementos)
    {
        super(context, R.layout.linha2, elementos);

        this.context = context;
        this.elementos = elementos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha2, parent, false);
        TextView nome = (TextView) rowView.findViewById(R.id.nome);
        TextView descricao = (TextView) rowView.findViewById(R.id.descricao);
        TextView valor = (TextView) rowView.findViewById(R.id.valor);
        nome.setText(elementos.get(position).getTitulo());
        descricao.setText(elementos.get(position).getDescricao());
        valor.setText(Double.toString(elementos.get(position).getValor()));

        return rowView;
    }
}
