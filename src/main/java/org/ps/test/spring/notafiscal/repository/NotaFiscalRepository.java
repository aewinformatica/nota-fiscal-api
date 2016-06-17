package org.ps.test.spring.notafiscal.repository;

import org.ps.test.spring.notafiscal.domain.NotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Interface que representa o contrato de um repositório de {@link NotaFiscal}.
 * Está utilizando o <b>spring-data-rest</b> para realizar as consultas à base de dados e disponibilizar
 * endpoints RESTFull HATEOAS para manipular o recurso NotaFiscal.
 * @see NotaFiscal
 */
@RepositoryRestResource(path = "notafiscal", collectionResourceRel = "notafiscal")
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Long> {

}
