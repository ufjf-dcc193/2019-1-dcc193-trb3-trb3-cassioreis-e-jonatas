package br.ufjf.dcc193.acervosystem.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Etiqueta {

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank(message="Título obrigatório")
    private String titulo;
    @NotBlank(message="Descrição obrigatória")
    private String descricao;
    @NotBlank(message="Url obrigatória")
    private String url;


    
    @ManyToMany(mappedBy = "etiquetas", cascade = CascadeType.ALL)  
    private List<Item> itens;

    @ManyToMany(mappedBy = "etiquetas", cascade = CascadeType.ALL)  
    private List<Vinculo> vinculos; 

    //Atributo auxiliar para checklist
    private boolean checked;

    public boolean getChecked(){
        return this.checked;
    }

    public void setChecked(){
        this.checked = true;
    }

    public void setUnChecked(){
        this.checked = false;
    }

    public Etiqueta(Long id, String titulo, String descricao, String url) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
    }

    public Etiqueta(String titulo, String descricao, String url) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        //instanciarListas();
    }

    public Etiqueta() {
    }
    public Etiqueta(boolean instanciarListas) {
        if (instanciarListas){
          //  instanciarListas();
        }
    }
/*
    private void instanciarListas(){
        itens = new ArrayList<Item>();
            vinculos = new ArrayList<Vinculo>();
    } */

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public List<Vinculo> getVinculos() {
        return vinculos;
    }

    public void setVinculos(List<Vinculo> vinculos) {
        this.vinculos = vinculos;
    }
    
    
    public void addVinculo(Vinculo v){
        vinculos.add(v);
    }
    
    public void addItem(Item i){
        itens.add(i);
    }

    @Override
    public String toString() {
        return "Etiqueta [descricao=" + descricao + ", id=" + id + ", titulo=" + titulo + ", url="
                + url + "]";
    }

   
}