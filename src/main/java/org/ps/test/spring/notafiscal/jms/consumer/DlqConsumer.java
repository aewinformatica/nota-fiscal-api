package org.ps.test.spring.notafiscal.jms.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

/**
 * Death letter queue consumer para tratar as mensagens com algum erro de processamento. As mensagens que são processadas
 * aqui seguiram a seguinte política antes de entrar na DLQ:
 * <ul>
 *     <li>maximumRedeliveries=6</li>
 *     <li>maximumRedeliveryDelay=-1</li>
 *     <li>initialRedeliveryDelay=1000L</li>
 *     <li>backOffMultiplier=5</li>
 * </ul>
 */
@Component
public class DlqConsumer {

    private static final Logger logger = LoggerFactory.getLogger(DlqConsumer.class);

    @JmsListener(destination = "ActiveMQ.DLQ")
    public void dlq(GenericMessage<?> msg) {
        logger.error("code='ERRO' message='Erro ao incluir {}' nested={}", msg.getPayload().getClass().getSimpleName(), msg);
    }

}
