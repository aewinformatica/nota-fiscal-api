package org.ps.test.spring.notafiscal.jms.consumer;

/**
 * Classe que representa um erro no processamento dos consumidores JMS.
 */
public class ConsumerException extends RuntimeException {

    /**
     * Construtor que recebe a mensagem e a causa do erro.
     * @param msg Mensagem do erro.
     * @param cause Causa do erro.
     */
    public ConsumerException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
