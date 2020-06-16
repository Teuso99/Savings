package com.example.savings;

public class Categoria
{
    private String nome;
    private int imagem; // vai armazenar o identificador do recurso
    private String descricao;

    public Categoria(String nome, String descricao, int imagem)
    {
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public int getImagem()
    {
        return imagem;
    }

    public void setImagem(int imagem)
    {
        this.imagem = imagem;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }
}
