package com.example.projeto.client;

/**
 * Lançada quando o serviço de alunos ou de professores não responde
 * (fora do ar, timeout, erro 5xx etc.).
 */
public class ServicoIndisponivelException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ServicoIndisponivelException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
