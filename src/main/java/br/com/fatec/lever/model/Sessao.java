package br.com.fatec.lever.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalTime;

@Entity
public class Sessao {

    @Id
    @GeneratedValue
    private Integer id;
    private LocalTime horario;

    @ManyToOne
    private Sala sala;

    @ManyToOne
    private Curso curso;

    /**
     * @deprecated hibernate only
     */
    public Sessao() {}

    public Sessao(LocalTime horario, Sala sala, Curso curso) {
        this.horario = horario;
        this.sala = sala;
        this.curso = curso;
    }

    public LocalTime getHorarioTermino() {
        return this.horario.plusMinutes(curso.getDuracao().toMinutes());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

}
