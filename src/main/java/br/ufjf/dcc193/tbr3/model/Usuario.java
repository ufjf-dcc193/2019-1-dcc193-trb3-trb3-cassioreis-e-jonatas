package br.ufjf.dcc193.tbr3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;
    private String descricao;
    private String email;
    private String chave;

    @OneToMany(mappedBy = "usuario")
    private List<Anotacao> anotacoes;

    public Usuario (String email, String chave)
    {
        this.email = email;
        this.chave = chave;
        instanciarListas();
    }

    public Usuario(String nome, String descricao, String email, String chave) {
        this.nome = nome;
        this.descricao = descricao;
        this.email = email;
        this.chave = chave;
        instanciarListas();
    }

    public Usuario(){

    }
    public Usuario(boolean instanciarListas) {
        if (instanciarListas){
            instanciarListas();
        }
    }

    private void instanciarListas(){
        anotacoes = new ArrayList<Anotacao>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public List<Anotacao> getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(List<Anotacao> anotacoes) {
        this.anotacoes = anotacoes;
    }

}