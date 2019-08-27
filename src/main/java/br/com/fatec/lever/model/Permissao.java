package br.com.fatec.lever.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Permissao implements GrantedAuthority {

    public static final Permissao ADMIN = new Permissao("ADMIN");
    public static final Permissao ALUNO = new Permissao("ALUNO");

    @Id
    private String nome;

    public Permissao(String nome) {
        this.nome = nome;
    }

    public Permissao() {}


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getAuthority() {
        return this.nome;
    }
}
