package br.ufjf.dcc193.tbr3.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String titulo;
    
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)  
    private Set<Anotacao> anotacoes;
    @ManyToMany
    @JoinTable(name = "etiquetas")
    private Set<Etiqueta> etiquetas;
    @OneToMany(mappedBy = "itemOrigem", cascade = CascadeType.ALL)
    private Set<Vinculo> vinculosOrigem;
    @OneToMany(mappedBy = "itemDestino", cascade = CascadeType.ALL)
    private Set<Vinculo> vinculosDestino;
    
    
    public Item() {
    }

    public Item(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Set<Anotacao> getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(Set<Anotacao> anotacoes) {
        this.anotacoes = anotacoes;
    }

    public Set<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(Set<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public Set<Vinculo> getVinculosOrigem() {
        return vinculosOrigem;
    }

    public void setVinculosOrigem(Set<Vinculo> vinculosOrigem) {
        this.vinculosOrigem = vinculosOrigem;
    }

    public Set<Vinculo> getVinculosDestino() {
        return vinculosDestino;
    }

    public void setVinculosDestino(Set<Vinculo> vinculosDestino) {
        this.vinculosDestino = vinculosDestino;
    }
    
    
    
    
}