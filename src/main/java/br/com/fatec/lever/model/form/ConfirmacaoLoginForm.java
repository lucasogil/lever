package br.com.fatec.lever.model.form;

import br.com.fatec.lever.dao.UsuarioDao;
import br.com.fatec.lever.email.Token;
import br.com.fatec.lever.model.Permissao;
import br.com.fatec.lever.model.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

public class ConfirmacaoLoginForm {

    private Token token;
    private String password;
    private String confirmPassword;

    public ConfirmacaoLoginForm() {}

    public ConfirmacaoLoginForm(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isValid() {
        return password.equals(confirmPassword);
    }

    public Usuario toUsuario(UsuarioDao dao, PasswordEncoder encoder) {
        System.out.println("Log1");
        String encriptedPassword = encoder.encode(this.password);
        System.out.println("Log2");
        String email = token.getEmail();
        System.out.println("Log3");
        Usuario	usuario = dao.findByEmail(email).orElse(novoUsuario(email, encriptedPassword));
        System.out.println("Log5");
        System.out.println(usuario.getAuthorities().iterator().next().getAuthority().toString());
        usuario.setPassword(encriptedPassword);

        return usuario;
    }

    private Usuario novoUsuario(String email, String encriptedPassword) {
        Set<Permissao> permissoes = new HashSet<>();
        permissoes.add(Permissao.ADMIN);
        System.out.println("Log4");
        return new Usuario(email, encriptedPassword, permissoes);
    }

}
