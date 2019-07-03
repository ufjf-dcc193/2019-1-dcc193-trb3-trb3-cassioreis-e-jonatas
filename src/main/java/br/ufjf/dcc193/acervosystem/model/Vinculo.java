package br.ufjf.dcc193.acervosystem.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    private List<Anotacao> anotacoes;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "etiqueta_vinculo", 
                joinColumns = {@JoinColumn(name="vinculo_id")},
                inverseJoinColumns = {@JoinColumn(name="etiqueta_id")})
    private List<Etiqueta> etiquetas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public List<Anotacao> getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(List<Anotacao> anotacoes) {
        this.anotacoes = anotacoes;
    } 

    public Vinculo() {
    }
    public Vinculo(boolean instanciarListas) {
        if (instanciarListas){
            anotacoes = new ArrayList<Anotacao>();
            etiquetas = new ArrayList<Etiqueta>();
        }
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

    public List<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }
    
    
    public void addEtiqueta(Etiqueta e){
        etiquetas.add(e);
    }
    
    public void addAnotacao(Anotacao a){
        anotacoes.add(a);
    }

    @Override
    public String toString() {
        return "Vinculo [id=" + id + "]";
    }
    

}