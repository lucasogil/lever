package br.com.fatec.lever.helper;

import br.com.fatec.lever.dao.TokenDao;
import br.com.fatec.lever.email.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TokenHelper {

    @Autowired
    private TokenDao dao;

    public Token generateFrom(String email) {
        Token token = new Token(email);
        dao.save(token);
        return token;
    }

    public Optional<Token> getTokenFrom(String uuid){
        return dao.findByUuid(uuid);
    }
}
