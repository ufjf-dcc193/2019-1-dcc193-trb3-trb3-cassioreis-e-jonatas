package br.ufjf.dcc193.acervosystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import br.ufjf.dcc193.acervosystem.helper.Helper;

/**
 * Revisao
 */
@Entity
public class Anotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)   
    private Long id;
    private String titulo;
    private String descricao;
    private String url;

   
    @DateTimeFormat(pattern = "dd-MM-YY")
    private String dataDeInicio = null;
    @DateTimeFormat(pattern = "dd-MM-YY")
    private String dataDeAtualizacao = null;
 
    @ManyToOne(targetEntity = Usuario.class)
    private Usuario usuario;

    @ManyToOne(targetEntity = Item.class)
    private Item item;

    @ManyToOne(targetEntity = Vinculo.class)
    private Vinculo vinculo;

    public Anotacao() {
    }

    public Anotacao(String titulo, String descricao, String url, String dataDeInicio) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.dataDeInicio = dataDeInicio;
        atualizaDataDeAtualizacao();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDataDeInicio() {
        return dataDeInicio;
    }

    public void setDataDeInicio(String dataDeInicio) {
        this.dataDeInicio = dataDeInicio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataDeAtualizacao() {
        return dataDeAtualizacao;
    }

    public void atualizaDataDeAtualizacao(){
        dataDeAtualizacao = Helper.getDataAtualFormatada();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Vinculo getVinculo() {
        return vinculo;
    }

    public void setVinculo(Vinculo vinculo) {
        this.vinculo = vinculo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Anotacao [dataDeAtualizacao=" + dataDeAtualizacao + ", dataDeInicio=" + dataDeInicio + ", descricao="
                + descricao + ", id=" + id + "]";
    }
  

}