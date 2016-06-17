package org.ps.test.spring.notafiscal.domain;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

/**
 * Entidade que representa uma Nota Fiscal no sistema. Ela possui propriedades e uma lista de itens que são instâncias
 * mercadorias.
 * @see Mercadoria
 */
@Entity
public class NotaFiscal implements Serializable {

    private static final long serialVersionUID = -8014090543203035390L;

    @Id
    @GeneratedValue (strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false)
    private String descricao;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Mercadoria> itens = Sets.newHashSet();

    public Long getId() {
        return id;
    }

    public void setId (Long id) {
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

    public Set<Mercadoria> getItens() {
        return itens;
    }

    public void setItens(Set<Mercadoria> itens) {
        this.itens = itens;
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
        return this.getId() != null && this.getId().equals(((Mercadoria)obj).getId());
    }
}
