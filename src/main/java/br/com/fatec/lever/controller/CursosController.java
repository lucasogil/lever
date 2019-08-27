package br.com.fatec.lever.controller;

import br.com.fatec.lever.dao.CursoDao;
import br.com.fatec.lever.dao.SessaoDao;
import br.com.fatec.lever.model.Curso;
import br.com.fatec.lever.model.Sessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author Lucas Gil
 */
@Controller
public class CursosController {


    @Autowired
    private CursoDao cursoDao;

    @Autowired
    private SessaoDao sessaoDao;

    @GetMapping({"/admin/curso", "/admin/curso/{id}"})
    public ModelAndView form(@PathVariable("id") Optional<Integer> id, Curso curso){

        ModelAndView modelAndView = new ModelAndView("curso/curso");

        if (id.isPresent()){
            curso = cursoDao.findOne(id.get());
        }

        modelAndView.addObject("curso", curso);

        return modelAndView;
    }


    @PostMapping("/admin/curso")
    @Transactional
    public ModelAndView salva(@Valid Curso curso, BindingResult result){

        if (result.hasErrors()) {
            return form(Optional.ofNullable(curso.getId()), curso);
        }

        cursoDao.save(curso);

        ModelAndView view = new ModelAndView("redirect:/admin/cursos");

        return view;
    }


    @GetMapping(value="/admin/cursos")
    public ModelAndView lista(){

        ModelAndView modelAndView = new ModelAndView("curso/lista");

        modelAndView.addObject("cursos", cursoDao.findAll());

        return modelAndView;
    }

    @GetMapping("/curso/catalogo")
    public ModelAndView catalogo(){
        ModelAndView modelAndView = new ModelAndView("curso/catalogo");
        modelAndView.addObject("cursos", cursoDao.findAll());
        return modelAndView;
    }

    @GetMapping("/curso/{id}/detalhe")
    public ModelAndView detalhes(@PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView("/curso/detalhe");
        Curso curso = cursoDao.findOne(id);
        List<Sessao> sessoes = sessaoDao.buscaSessoesDoFilme(curso);
        modelAndView.addObject("sessoes", sessoes);
        return modelAndView;
    }

    @DeleteMapping("/admin/filme/{id}")
    @ResponseBody
    @Transactional
    public void delete(@PathVariable("id") Integer id){
        cursoDao.delete(id);
    }

}
