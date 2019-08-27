package br.com.fatec.lever.validacao;

import br.com.fatec.lever.model.Sessao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class GerenciadorDeSessao {

    private List<Sessao> sessoesDaSala;

    public GerenciadorDeSessao(List<Sessao> sessoesDaSala) {
        this.sessoesDaSala = sessoesDaSala;
    }
    private boolean horarioIsConflitante(Sessao sessaoExistente, Sessao sessaoNova) {

        LocalDate hoje = LocalDate.now();

        LocalDateTime horarioSessaoExistente = sessaoExistente.getHorario().atDate(hoje);

        LocalDateTime horarioSessaoNova = sessaoNova.getHorario().atDate(hoje);

        boolean terminaAntes = sessaoNova.getHorarioTermino()
                .isBefore(horarioSessaoExistente.toLocalTime());

        boolean comecaDepois = sessaoExistente.getHorarioTermino()
                .isBefore(horarioSessaoNova.toLocalTime());

        if (terminaAntes || comecaDepois) {
            return false;
        }
        return true;
    }
    public boolean cabe(Sessao sessaoNova) {
        return sessoesDaSala.stream().noneMatch(sessaoExistente ->
                horarioIsConflitante(sessaoExistente, sessaoNova)
        );
    }

}
