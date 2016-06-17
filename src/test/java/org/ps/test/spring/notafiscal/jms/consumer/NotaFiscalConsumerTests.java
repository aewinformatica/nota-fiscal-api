package org.ps.test.spring.notafiscal.jms.consumer;

import org.junit.Before;
import org.junit.Test;
import org.ps.test.spring.notafiscal.AbstractTest;
import org.ps.test.spring.notafiscal.domain.Mercadoria;
import org.ps.test.spring.notafiscal.domain.NotaFiscal;
import org.ps.test.spring.notafiscal.repository.NotaFiscalRepository;

import static org.mockito.Mockito.*;

/**
 * Classe que testa a instância de {@link NotaFiscalConsumer}.
 * @see NotaFiscalConsumer
 */
public class NotaFiscalConsumerTests extends AbstractTest {

    private NotaFiscalRepository repository;

    private NotaFiscalConsumer consumer;

    @Before
    public void before() {
        repository = mock(NotaFiscalRepository.class);
        consumer = new NotaFiscalConsumer(repository);
    }

    @Test
    public void testIncluirOK() {
        NotaFiscal nf1 = createNotaFiscal("NF001");

        consumer.incluir(nf1);

        verify(repository, times(1)).saveAndFlush(nf1);
    }

    @Test(expected = ConsumerException.class)
    public void testIncluirComExcecao() {
        NotaFiscal nf1 = createNotaFiscal(null);
        when(repository.saveAndFlush(nf1)).thenThrow(new ConsumerException("", null));
        consumer.incluir(nf1);
    }

    private NotaFiscal createNotaFiscal(String codigo) {
        NotaFiscal nf1 = new NotaFiscal();
        nf1.setCodigo(codigo);
        nf1.setDescricao("Nota Fiscal" + codigo);
        return nf1;
    }

}
