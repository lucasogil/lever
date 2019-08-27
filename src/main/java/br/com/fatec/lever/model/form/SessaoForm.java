package br.com.fatec.lever.model.form;

import br.com.fatec.lever.dao.CursoDao;
import br.com.fatec.lever.dao.SalaDao;
import br.com.fatec.lever.model.Curso;
import br.com.fatec.lever.model.Sala;
import br.com.fatec.lever.model.Sessao;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

public class SessaoForm {

    private Integer id;

    @NotNull
    private Integer salaId;

    @DateTimeFormat(pattern = "HH:mm")
    @NotNull
    private LocalTime horario;

    @NotNull
    private Integer filmeId;

    public Sessao toSessao(SalaDao salaDao, CursoDao cursoDao) {
        Sala sala = salaDao.findOne(salaId);
        Curso curso = cursoDao.findOne(filmeId);

        Sessao sessao = new Sessao(horario, sala, curso);
        sessao.setId(id);

        return sessao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSalaId() {
        return salaId;
    }

    public void setSalaId(Integer salaId) {
        this.salaId = salaId;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public Integer getFilmeId() {
        return filmeId;
    }

    public void setFilmeId(Integer filmeId) {
        this.filmeId = filmeId;
    }

}
