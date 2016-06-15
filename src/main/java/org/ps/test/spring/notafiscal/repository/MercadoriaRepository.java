package org.ps.test.spring.notafiscal.repository;

import org.ps.test.spring.notafiscal.domain.Mercadoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by salespaulo on 6/14/16.
 */
@RepositoryRestResource(path = "mercadoria", collectionResourceRel = "mercadoria")
public interface MercadoriaRepository extends JpaRepository<Mercadoria, Long> {

}
