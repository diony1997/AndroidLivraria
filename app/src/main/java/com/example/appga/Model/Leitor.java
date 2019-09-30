package com.example.appga.Model;

import java.io.Serializable;

public class Leitor implements Serializable {

    private String Nome, endereco, celular, email, cpf, dt_Nascimento;
    private int categoria;

    @Override
    public String toString() {
        return Nome;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getDt_Nascimento() {
        return dt_Nascimento;
    }

    public void setDt_Nascimento(String dt_Nascimento) {
        this.dt_Nascimento = dt_Nascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }
}
