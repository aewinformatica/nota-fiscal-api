package org.ps.test.spring.notafiscal.jms.consumer;

import org.ps.test.spring.notafiscal.domain.NotaFiscal;
import org.ps.test.spring.notafiscal.repository.NotaFiscalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Nota Fiscal consumer contendo listeners que tratam as mensagens que chegam à fila <b>notafiscal-mailbox</b>.
 */
@Component
public class NotaFiscalConsumer {

    private NotaFiscalRepository repository;

    /**
     * Contrutor que recebe a instância do repositório que é a classe necessária.
     * @param repository {@link NotaFiscalRepository} instância do repositório.
     */
    @Autowired
    public NotaFiscalConsumer(NotaFiscalRepository repository) {
        this.repository = repository;
    }

    /**
     * Método que consome uma nota fiscal e salva no banco de dados.
     * @param notaFiscal {@link NotaFiscal} consumida da fila <b>notafiscal-mailbox</b>.
     */
    @JmsListener(destination = "notafiscal-mailbox")
    public void incluir(NotaFiscal notaFiscal) {
        try {
            repository.saveAndFlush(notaFiscal);
        } catch (Exception e) {
            throw new ConsumerException("Erro ao incluir notaFiscal=" + notaFiscal, e);
        }
    }
}
