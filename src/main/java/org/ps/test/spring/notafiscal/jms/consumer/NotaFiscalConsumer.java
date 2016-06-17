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

    @Autowired
    private NotaFiscalRepository repository;

    /**
     * Método que consome uma nota fiscal e salva no banco de dados.
     * @param notaFiscal {@link NotaFiscal} consumida da fila <b>notafiscal-mailbox</b>.
     */
    @JmsListener(destination = "notafiscal-mailbox")
    public void incluir(NotaFiscal notaFiscal) {
        repository.saveAndFlush(notaFiscal);
    }
}
