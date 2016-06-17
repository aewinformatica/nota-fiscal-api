package org.ps.test.spring.notafiscal.jms.consumer;

import org.ps.test.spring.notafiscal.domain.Mercadoria;
import org.ps.test.spring.notafiscal.repository.MercadoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Mercadoria consumer contendo listeners que tratam as mensagens que chegam à fila <b>notafiscal-mailbox</b>.
 */
@Component
public class MercadoriaConsumer {

    @Autowired
    private MercadoriaRepository repository;

    /**
     * Método que consome uma mercadoria e salva no banco de dados.
     * @param mercadoria {@link Mercadoria} consumida da fila <b>notafiscal-mailbox</b>.
     */
    @JmsListener(destination = "mercadoria-mailbox")
    public void incluir(Mercadoria mercadoria) {
        try {
            repository.saveAndFlush(mercadoria);
        } catch (Exception e) {
            throw new ConsumerException("Erro ao incluir mercadoria=" + mercadoria, e);
        }
    }
}
