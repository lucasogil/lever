package br.com.fatec.lever.dao;

import br.com.fatec.lever.model.Curso;
import br.com.fatec.lever.model.Sala;
import br.com.fatec.lever.model.Sessao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SessaoDao {

    @PersistenceContext
    private EntityManager manager;

    public void save(Sessao sessao) {
        manager.persist(sessao);
    }

    public List<Sessao> buscaSessoesDaSala(Sala sala){
        return manager.createQuery("select s from Sessao s where s.sala = :sala", Sessao.class)
                .setParameter("sala", sala)
                .getResultList();
    }

    public List<Sessao> buscaSessoesDoFilme(Curso curso) {
        return manager.createQuery("select s from Sessao s where s.curso = :curso", Sessao.class)
                .setParameter("curso", curso)
                .getResultList();
    }
}
