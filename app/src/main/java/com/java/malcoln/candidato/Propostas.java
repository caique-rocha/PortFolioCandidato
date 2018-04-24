package com.java.malcoln.candidato;

/**
 * Created by T.I on 30/06/2016.
 */
public class Propostas {
    private String linha, trajeto;
    public int candidato;
    private Integer id;

    public Propostas() {
    }

    public Propostas(String linha, String trajeto, int candidato, Integer id) {
        this.linha = linha;
        this.trajeto = trajeto;
        this.candidato = candidato;
        this.id = id;
    }
    public Integer getId(){ return id;}
    public void setId(Integer id){ this.id = id;}

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public String getTrajeto() {
        return trajeto;
    }

    public void setTrajeto(String trajeto) {
        this.trajeto = trajeto;
    }

    public int getCandidato() {
        return candidato;
    }

    public void setCandidato(int trajeto) {
        this.candidato = candidato;
    }


}
