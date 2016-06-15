package org.ps.test.spring.notafiscal.repository;

import org.ps.test.spring.notafiscal.domain.NotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by salespaulo on 6/14/16.
 */
@RepositoryRestResource(path = "notafiscal", collectionResourceRel = "notafiscal")
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Long> {

}
