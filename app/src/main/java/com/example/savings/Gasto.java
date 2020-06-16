package com.example.savings;

public class Gasto
{
    private String titulo;
    private String descricao;
    private String categoria;
    private double preco;
    //private String img;

    public Gasto(String titulo, String descricao, String categoria, double preco/*, String img*/)
    {
        this.titulo = titulo;
        this.descricao = descricao;
        this.categoria = categoria;
        this.preco = preco;
        //this.img = img;
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

    public double getPreco()
    {
        return preco;
    }

    public void setPreco(double preco)
    {
        this.preco = preco;
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
