package com.atv15.atividade15.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Curso {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String descricao;

  @JsonIgnore
  @ManyToMany(mappedBy = "cursos")
  private Set<Aluno> alunos;

  public Curso(Long id, String nome, String descricao, Set<Aluno> alunos) {
    this.id = id;
    this.nome = nome;
    this.descricao = descricao;
    this.alunos = alunos;
  }

  public Curso() {
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

  public Set<Aluno> getAlunos() {
    return alunos;
  }

  public void setAlunos(Set<Aluno> alunos) {
    this.alunos = alunos;
  }
}