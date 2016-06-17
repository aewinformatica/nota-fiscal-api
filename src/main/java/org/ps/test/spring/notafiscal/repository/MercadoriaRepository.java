package org.ps.test.spring.notafiscal.repository;

import org.ps.test.spring.notafiscal.domain.Mercadoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Interface que representa o contrato de um repositório de {@link Mercadoria}s.
 * Está utilizando o <b>spring-data-rest</b> para realizar as consultas à base de dados e disponibilizar
 * endpoints RESTFull HATEOAS para manipular o recurso Mercadoria.
 * @see Mercadoria
 */
@RepositoryRestResource(path = "mercadoria", collectionResourceRel = "mercadoria")
public interface MercadoriaRepository extends JpaRepository<Mercadoria, Long> {

}
