package org.ps.test.spring.notafiscal.jms.consumer;

import org.junit.Before;
import org.junit.Test;
import org.ps.test.spring.notafiscal.AbstractTest;
import org.ps.test.spring.notafiscal.domain.Mercadoria;
import org.ps.test.spring.notafiscal.repository.MercadoriaRepository;

import static org.mockito.Mockito.*;

/**
 * Classe que testa a instância de {@link MercadoriaConsumer}.
 * @see MercadoriaConsumer
 */
public class MercadoriaConsumerTests extends AbstractTest {

    private MercadoriaRepository repository;

    private MercadoriaConsumer consumer;

    @Before
    public void before() {
        repository = mock(MercadoriaRepository.class);
        consumer = new MercadoriaConsumer(repository);
    }

    @Test
    public void testIncluirOK() {
        Mercadoria m1 = createMercadoria("MER001");

        consumer.incluir(m1);

        verify(repository, times(1)).saveAndFlush(m1);
    }

    @Test(expected = ConsumerException.class)
    public void testIncluirComExcecao() {
        Mercadoria m1 = createMercadoria(null);
        when(repository.saveAndFlush(m1)).thenThrow(new ConsumerException("", null));
        consumer.incluir(m1);
    }

    private Mercadoria createMercadoria(String codigo) {
        Mercadoria m1 = new Mercadoria();
        m1.setCodigo(codigo);
        m1.setDescricao("Mercadoria " + codigo);
        return m1;
    }
}
