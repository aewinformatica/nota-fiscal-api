package org.ps.test.spring.notafiscal.domain;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

/**
 * Created by salespaulo on 6/14/16.
 */
@Entity
public class Mercadoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String codigo;

    private String descricao;

    @Column(nullable = false)
    private BigDecimal valor;

    @ManyToMany(mappedBy = "itens")
    private Set<NotaFiscal> notasFiscais = Sets.newHashSet();

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

    public Set<NotaFiscal> getNotasFiscais() {
        return notasFiscais;
    }

    protected void setNotasFiscais(Set<NotaFiscal> notasFiscais) {
        this.notasFiscais = notasFiscais;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getId()).append(getCodigo()).append(getDescricao()).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return Optional.ofNullable(obj).map(o -> {
            if (! this.getClass().isInstance(obj)) return false;
            return this.getId().equals(((Mercadoria)obj).getId());
        }).orElse(false);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
