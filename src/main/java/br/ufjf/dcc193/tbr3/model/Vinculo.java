package br.ufjf.dcc193.tbr3.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Vinculo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @ManyToOne
    private Item itemOrigem;

    @ManyToOne
    private Item itemDestino;

    @OneToMany(mappedBy = "vinculo")
    private Set<Anotacao> anotacoes;

    @ManyToMany
    @JoinTable(name = "etiquetas")
    private Set<Etiqueta> etiquetas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Set<Anotacao> getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(Set<Anotacao> anotacoes) {
        this.anotacoes = anotacoes;
    } 

    public Vinculo() {

    }

    public Item getItemOrigem() {
        return itemOrigem;
    }

    public void setItemOrigem(Item itemOrigem) {
        this.itemOrigem = itemOrigem;
    }

    public Item getItemDestino() {
        return itemDestino;
    }

    public void setItemDestino(Item itemDestino) {
        this.itemDestino = itemDestino;
    }

    public Set<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(Set<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }


    

    
}