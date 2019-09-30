package com.example.appga.Model;

import java.io.Serializable;

public class Categoria_Leitor implements Serializable {

    private int codigo;
    private String descricao;
    private int aluguel_Maximo;

    @Override
    public String toString() {
        return codigo+"";
    }

    public int getAluguel_Maximo() {
        return aluguel_Maximo;
    }

    public void setAluguel_Maximo(int aluguel_Maximo) {
        this.aluguel_Maximo = aluguel_Maximo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
