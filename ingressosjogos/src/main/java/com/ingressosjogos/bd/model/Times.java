package com.ingressosjogos.bd.model;

public class Times {
    private int id;
    private String nome;

    public Times() {
        
    }

    public Times(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Times(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
}
