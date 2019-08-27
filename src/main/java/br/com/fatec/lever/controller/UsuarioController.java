package br.com.fatec.lever.controller;

import br.com.fatec.lever.dao.UsuarioDao;
import br.com.fatec.lever.email.EmailNovoUsuario;
import br.com.fatec.lever.email.Mailer;
import br.com.fatec.lever.email.Token;
import br.com.fatec.lever.helper.TokenHelper;
import br.com.fatec.lever.model.Usuario;
import br.com.fatec.lever.model.form.ConfirmacaoLoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class UsuarioController {

    @Autowired
    private Mailer mailer;

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private UsuarioDao usuarioDao;

    private PasswordEncoder passwordEnconder;

    @GetMapping("/usuario/request")
    public ModelAndView formSolicitacaoDeAcesso() {
        return new ModelAndView("usuario/form-email");
    }

    @PostMapping("/usuario/request")
    @Transactional
    public ModelAndView solicitacaoDeAcesso(String email) {
        ModelAndView view = new ModelAndView("usuario/adicionado");

        Token token = tokenHelper.generateFrom(email);

        mailer.send(new EmailNovoUsuario(token));

        return view;
    }

    @GetMapping("/usuario/validate")
    public ModelAndView validaLink(@RequestParam("uuid") String uuid) {

        Optional<Token> optionalToken = tokenHelper.getTokenFrom(uuid);

        if(!optionalToken.isPresent()) {
            ModelAndView view = new ModelAndView("redirect:/login");

            view.addObject("msg", "O token do link utilizado não foi encontrado!");
            return view;
        }

        Token token = optionalToken.get();
        ConfirmacaoLoginForm confirmacaoLoginForm = new ConfirmacaoLoginForm(token);

        ModelAndView view = new ModelAndView("usuario/confirmacao");

        view.addObject("confirmacaoLoginForm", confirmacaoLoginForm);

        return view;
    }

    @PostMapping("/usuario/cadastrar")
    @Transactional
    public ModelAndView cadastrar(ConfirmacaoLoginForm form){
        System.out.println("Comecou!");
        passwordEnconder = new BCryptPasswordEncoder();
        ModelAndView view = new ModelAndView("redirect:/login");
        System.out.println("Continua!");
        if ( form.isValid() ) {
            System.out.println("Valido!");
            Usuario usuario = null;
            try {
                usuario = form.toUsuario(usuarioDao, passwordEnconder);
                usuarioDao.save(usuario);
            } catch (Exception e ){
                System.out.println("Exception: "+ e);
                view.addObject("msg", "ERRO!");
                return view;
            }
            System.out.println("Cadastrou!");
            view.addObject("msg", "Usuario cadastrado	com	sucesso!");

            return view;
        }
        System.out.println("Continua!");
        view.addObject("msg", "O	token do link utilizado não foi encontrado!");

        return view;
    }

}
