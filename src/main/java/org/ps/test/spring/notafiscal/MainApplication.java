package org.ps.test.spring.notafiscal;

import com.google.common.collect.Sets;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.ps.test.spring.notafiscal.domain.Mercadoria;
import org.ps.test.spring.notafiscal.domain.NotaFiscal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Classe responsável por iniciar a aplicação.
 */
@SpringBootApplication
@EnableJms
public class MainApplication {

    /**
     * Main que inicia a aplicação.
     */
	public static void main(String[] args) {
        healthy(SpringApplication.run(MainApplication.class, args));
	}

    /**
     * Método que simula o comportamento de um cliente JMS enviando mensagens para a aplicação.
     */
    private static void healthy(ConfigurableApplicationContext context) {
        Mercadoria m1 = new Mercadoria();
        m1.setCodigo("MER001");
        m1.setDescricao("Mercadoria 001");
        m1.setValor(new BigDecimal("123.12"));

        Mercadoria m2 = new Mercadoria();
        m2.setCodigo("MER002");
        m2.setDescricao("Mercadoria 001");
        m2.setValor(new BigDecimal("987.98"));

        NotaFiscal n1 = new NotaFiscal();
        n1.setCodigo("NF001");
        n1.setDescricao("Nota Fiscal 002");

        NotaFiscal n2 = new NotaFiscal();
        n2.setCodigo("NF002");
        n2.setDescricao("Nota Fiscal 002");
        n2.setItens(Sets.newHashSet(Arrays.asList(m2)));

        // Enviando mensagens
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        jmsTemplate.convertAndSend("mercadoria-mailbox", m1);
        jmsTemplate.convertAndSend("notafiscal-mailbox", n1);
        jmsTemplate.convertAndSend("notafiscal-mailbox", n2);

        // Simula uma mensagem com erro de processamento
        Mercadoria merro = new Mercadoria();
        merro.setCodigo("MERRO");

        jmsTemplate.convertAndSend("mercadoria-mailbox", merro);

        // Simula uma mensagem com erro de processamento
        NotaFiscal nerro = new NotaFiscal();
        nerro.setCodigo("NFERRO");

        jmsTemplate.convertAndSend("notafiscal-mailbox", nerro);

        // Simula uma mensagem com erro de processamento
        Mercadoria merrounique = new Mercadoria();
        merrounique.setCodigo("MER001");
        merrounique.setDescricao("Mercadoria com erro de chave duplicada em código");
        merrounique.setValor(new BigDecimal("100"));

        jmsTemplate.convertAndSend("mercadoria-mailbox", merrounique);
    }

    /**
     * Bean que constroi a conexão com o ActveMQ. Setando propriedades específicas para o projeto.
     */
    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
        factory.setTrustAllPackages(true);
        return factory;
    }

}
