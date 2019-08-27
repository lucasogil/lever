package br.com.fatec.lever.controller;

import br.com.fatec.lever.dao.CursoDao;
import br.com.fatec.lever.dao.SalaDao;
import br.com.fatec.lever.dao.SessaoDao;
import br.com.fatec.lever.model.Sessao;
import br.com.fatec.lever.model.form.SessaoForm;
import br.com.fatec.lever.validacao.GerenciadorDeSessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SessaoController {

    @Autowired
    private SalaDao salaDao;

    @Autowired
    private CursoDao cursoDao;

    @Autowired
    private SessaoDao sessaoDao;

    @GetMapping("/admin/sessao")
    public ModelAndView form(@RequestParam("salaId") Integer salaId, SessaoForm form){

        form.setSalaId(salaId);

        ModelAndView modelAndView = new ModelAndView("sessao/sessao");

        modelAndView.addObject("sala", salaDao.findOne(salaId));
        modelAndView.addObject("filmes", cursoDao.findAll());
        modelAndView.addObject("form", form);

        return modelAndView;
    }

    @PostMapping(value = "/admin/sessao")
    @Transactional
    public ModelAndView salva(@Valid SessaoForm form, BindingResult result) {

        if (result.hasErrors()) return form(form.getSalaId(), form);

        Sessao sessao = form.toSessao(salaDao, cursoDao);

        List<Sessao> sessoesDaSala = sessaoDao.buscaSessoesDaSala(sessao.getSala());

        GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoesDaSala);

        if (gerenciador.cabe(sessao)) {
            sessaoDao.save(sessao);
            return new ModelAndView("redirect:/admin/sala/" + form.getSalaId() + "/sessoes");
        }

        return form(form.getSalaId(), form);
    }


}
