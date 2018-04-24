package com.java.malcoln.candidato;

/**
 * Created by T.I on 30/06/2016.
 */
public class Eventos {
    private String linha, trajeto, horario;
    public int candidato;
    private Integer id;

    public Eventos() {
    }

    public Eventos(String linha, String trajeto, String horario,  int candidato, Integer id) {
        this.linha = linha;
        this.trajeto = trajeto;
        this.horario = horario;
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

    public String getHorario() {
        return horario;
    }

    public void setHorario(String trajeto) {
        this.horario = horario;
    }

    public int getCandidato() {
        return candidato;
    }

    public void setCandidato(int trajeto) {
        this.candidato = candidato;
    }


}
