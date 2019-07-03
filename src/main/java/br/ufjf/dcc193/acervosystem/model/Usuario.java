package br.ufjf.dcc193.acervosystem.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;;


@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank(message="Nome obrigatório")
    private String nome;
    private String descricao;
    @NotBlank(message="E-mail obrigatório")
    private String email;
    @NotBlank(message="Senha obrigatória")
    private String password;
/*
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Set<Anotacao> anotacoes; */


    public Usuario (String email, String password)
    {
        this.email = email;
        this.password = password;
        instanciarListas();
    }

    public Usuario(String nome, String descricao, String email, String password) {
        this.nome = nome;
        this.descricao = descricao;
        this.email = email;
        this.password = password;
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
        //anotacoes = new ArrayList<Anotacao>();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
/*
    public Set<Anotacao> getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(Set<Anotacao> anotacoes) {
        this.anotacoes = anotacoes;
    } */

    @Override
    public String toString() {
        return "Usuario [descricao=" + descricao + ", email=" + email + ", id=" + id
                + ", nome=" + nome + ", password=" + password + "]";
    }

}