package org.ps.test.spring.notafiscal.domain;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * Entidade que representa uma Mercadoria no sistema. Ela possui propriedades que representam os dados de uma mercadoria
 * no sistema.
 */
@Entity
public class Mercadoria implements Serializable {

    private static final long serialVersionUID = -2242181296810260286L;

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    private String descricao;

    @Column(nullable = false)
    private BigDecimal valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getId()).append(getCodigo()).append(getDescricao()).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return Optional.ofNullable(obj).map(this::toEquals).orElse(false);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    private boolean toEquals(Object obj) {
        if (! this.getClass().isInstance(obj)) return false;
        return this.getId() != null && this.getId().equals(((NotaFiscal)obj).getId());
    }
}
