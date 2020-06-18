package com.example.savings;

public class Gasto
{
    private int id;
    private String titulo;
    private String descricao;
    private String categoria;
    private double valor;
    //private String img;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getCategoria()
    {
        return categoria;
    }

    public void setCategoria(String categoria)
    {
        this.categoria = categoria;
    }

    public double getValor()
    {
        return valor;
    }

    public void setValor(double valor)
    {
        this.valor = valor;
    }

 /*   public String getImg()
    {
        return img;
    }

    public void setImg(String img)
    {
        this.img = img;
    }*/
}
