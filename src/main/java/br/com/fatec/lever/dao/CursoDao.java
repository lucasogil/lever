package br.com.fatec.lever.dao;

import br.com.fatec.lever.model.Curso;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by nando on 03/03/17.
 */
@Repository
public class CursoDao {

    @PersistenceContext
    private EntityManager manager;


    public Curso findOne(Integer id) {
        return manager.find(Curso.class, id);
    }

    public void save(Curso curso) {
        manager.persist(curso);
    }

    public List<Curso> findAll() {
        return manager.createQuery("select f from Curso f", Curso.class).getResultList();
    }

    public void delete(Integer id) {
        manager.remove(findOne(id));
    }
}
