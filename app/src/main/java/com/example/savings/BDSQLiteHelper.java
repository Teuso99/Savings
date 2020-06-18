package com.example.savings;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class BDSQLiteHelper extends SQLiteOpenHelper
{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SavingsDB";
    private static final String TABELA_GASTOS = "gastos";
    private static final String ID = "id";
    private static final String TITULO = "titulo";
    private static final String DESCRICAO = "descricao";
    private static final String CATEGORIA = "categoria";
    private static final String VALOR = "valor";

    private static final String[] COLUNAS = {ID, TITULO, DESCRICAO, CATEGORIA, VALOR};


    public BDSQLiteHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_TABLE = "CREATE TABLE gastos" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo TEXT," +
                "descricao TEXT," +
                "categoria TEXT," +
                "valor DOUBLE)";

        //recibo BLOB NOT NULL

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS gastos");
        this.onCreate(db);
    }

    public void addGasto(Gasto gasto)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TITULO, gasto.getTitulo());
        values.put(DESCRICAO, gasto.getDescricao());
        values.put(CATEGORIA, gasto.getCategoria());
        values.put(VALOR, new Double(gasto.getValor()));

        db.insert(TABELA_GASTOS, null, values);
        db.close();
    }

    public Gasto getGasto(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_GASTOS, COLUNAS, "id = ?", new String[] {String.valueOf(id)}, null, null, null, null);

        if (cursor == null)
        {
            return null;
        }
        else
        {
            cursor.moveToFirst();
            Gasto gasto = cursorToGasto(cursor);
            return gasto;
        }
    }

    private Gasto cursorToGasto(Cursor cursor)
    {
        Gasto gasto = new Gasto();

        gasto.setId(Integer.parseInt(cursor.getString(0)));
        gasto.setTitulo(cursor.getString(1));
        gasto.setDescricao(cursor.getString(2));
        gasto.setCategoria(cursor.getString(3));
        gasto.setValor(Double.parseDouble(cursor.getString(4)));

        return gasto;
    }

    public ArrayList<Gasto> getGastosEducacao()
    {
        ArrayList<Gasto> listaGastos = new ArrayList<Gasto>();
        final String nomeCategoria = "Educação";

        String query = "SELECT * FROM " + TABELA_GASTOS + " WHERE "+CATEGORIA + " = '" +nomeCategoria+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst())
        {
            do
            {
                Gasto gasto = cursorToGasto(cursor);
                listaGastos.add(gasto);
            }
            while (cursor.moveToNext());
        }

        return listaGastos;
    }

    public ArrayList<Gasto> getGastosServicos()
    {
        ArrayList<Gasto> listaGastos = new ArrayList<Gasto>();
        final String nomeCategoria = "Serviços";

        String query = "SELECT * FROM " + TABELA_GASTOS + " WHERE "+CATEGORIA + " = '" +nomeCategoria+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst())
        {
            do
            {
                Gasto gasto = cursorToGasto(cursor);
                listaGastos.add(gasto);
            }
            while (cursor.moveToNext());
        }

        return listaGastos;
    }

    public ArrayList<Gasto> getGastosEntretenimento()
    {
        ArrayList<Gasto> listaGastos = new ArrayList<Gasto>();
        final String nomeCategoria = "Entretenimento";

        String query = "SELECT * FROM " + TABELA_GASTOS + " WHERE "+CATEGORIA + " = '" +nomeCategoria+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst())
        {
            do
            {
                Gasto gasto = cursorToGasto(cursor);
                listaGastos.add(gasto);
            }
            while (cursor.moveToNext());
        }

        return listaGastos;
    }

    public ArrayList<Gasto> getGastosAlimentacao()
    {
        ArrayList<Gasto> listaGastos = new ArrayList<Gasto>();
        final String nomeCategoria = "Alimentação";

        String query = "SELECT * FROM " + TABELA_GASTOS + " WHERE "+CATEGORIA + " = '" +nomeCategoria+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst())
        {
            do
            {
                Gasto gasto = cursorToGasto(cursor);
                listaGastos.add(gasto);
            }
            while (cursor.moveToNext());
        }

        return listaGastos;
    }

    public ArrayList<Gasto> getGastosOutros()
    {
        ArrayList<Gasto> listaGastos = new ArrayList<Gasto>();
        final String nomeCategoria = "Outros";

        String query = "SELECT * FROM " + TABELA_GASTOS + " WHERE "+CATEGORIA + " = '" +nomeCategoria+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst())
        {
            do
            {
                Gasto gasto = cursorToGasto(cursor);
                listaGastos.add(gasto);
            }
            while (cursor.moveToNext());
        }

        return listaGastos;
    }

    public int updateGasto(Gasto gasto)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TITULO, gasto.getTitulo());
        values.put(DESCRICAO, gasto.getDescricao());
        values.put(CATEGORIA, gasto.getCategoria());
        values.put(VALOR, new Double (gasto.getValor()));

        int i = db.update(TABELA_GASTOS, values, ID+"= ?", new String[] {String.valueOf(gasto.getId())});

        db.close();
        return i;
    }

    public int deleteGasto(Gasto gasto)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        int i = db.delete(TABELA_GASTOS, ID+"= ?", new String[] {String.valueOf(gasto.getId())});

        db.close();
        return i;
    }
}
