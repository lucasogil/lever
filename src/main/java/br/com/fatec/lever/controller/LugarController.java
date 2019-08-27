package br.com.fatec.lever.controller;

import br.com.fatec.lever.dao.LugarDao;
import br.com.fatec.lever.dao.SalaDao;
import br.com.fatec.lever.model.Lugar;
import br.com.fatec.lever.model.Sala;
import br.com.fatec.lever.model.form.LugarForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by nando on 03/03/17.
 */
@Controller
public class LugarController {


    @Autowired
    private SalaDao salaDao;
    @Autowired
    private LugarDao lugarDao;

    @GetMapping("/admin/lugar")
    public ModelAndView form(@RequestParam("salaId") Integer salaId, LugarForm lugarDto) {

        lugarDto.setSalaId(salaId);

        ModelAndView view = new ModelAndView("lugar/lugar");

        view.addObject("lugarDto", lugarDto);

        return view;
    }



    @PostMapping("/admin/lugar")
    @Transactional
    public ModelAndView salva(@Valid LugarForm lugarDto, BindingResult result) {

        if (result.hasErrors()) return form(lugarDto.getSalaId(), lugarDto);

        Integer salaId = lugarDto.getSalaId();

        Lugar lugar = lugarDto.toLugar();
        lugarDao.save(lugar);

        Sala sala = salaDao.findOne(salaId);
        sala.add(lugar);

        salaDao.save(sala);

        return new ModelAndView("redirect:/admin/sala/"+salaId+"/lugares/");
    }

}
