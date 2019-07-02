package br.ufjf.dcc193.acervosystem.model;

import java.util.ArrayList;
import java.util.List;

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
    private List<Anotacao> anotacoes;
    @ManyToMany
    @JoinTable(name = "etiquetas")
    private List<Etiqueta> etiquetas;
    @OneToMany(mappedBy = "itemOrigem", cascade = CascadeType.ALL)
    private List<Vinculo> vinculosOrigem;
    @OneToMany(mappedBy = "itemDestino", cascade = CascadeType.ALL)
    private List<Vinculo> vinculosDestino;
    
    
    public Item() {
    }
    public Item(boolean instanciarListas) {
        if (instanciarListas){
            instanciarListas();
        }
    }

    private void instanciarListas(){
        anotacoes = new ArrayList<Anotacao>();
        etiquetas = new ArrayList<Etiqueta>();
        vinculosOrigem = new ArrayList<Vinculo>();
        vinculosDestino = new ArrayList<Vinculo>();
    }

    public Item(String titulo) {
        this.titulo = titulo;
        instanciarListas();
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

    public List<Anotacao> getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(List<Anotacao> anotacoes) {
        this.anotacoes = anotacoes;
    }

    public List<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public List<Vinculo> getVinculosOrigem() {
        return vinculosOrigem;
    }

    public void setVinculosOrigem(List<Vinculo> vinculosOrigem) {
        this.vinculosOrigem = vinculosOrigem;
    }

    public List<Vinculo> getVinculosDestino() {
        return vinculosDestino;
    }

    public void setVinculosDestino(List<Vinculo> vinculosDestino) {
        this.vinculosDestino = vinculosDestino;
    }
    
    public void addAnotacao(Anotacao a){
        anotacoes.add(a);
    }
    
    public void addEtiqueta(Etiqueta a){
        etiquetas.add(a);
    }
    
    
    public void addVinculoOrigem(Vinculo a){
        vinculosOrigem.add(a);
    }
    
    
    public void addVinculoDestino(Vinculo a){
        vinculosDestino.add(a);
    }

    @Override
    public String toString() {
        return "Item [anotacoes=" + anotacoes + ", etiquetas=" + etiquetas + ", id=" + id + ", titulo=" + titulo
                + ", vinculosDestino=" + vinculosDestino + ", vinculosOrigem=" + vinculosOrigem + "]";
    }
    
}