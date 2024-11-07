package com.atv15.atividade15.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Aluno {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String email;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "alunos_cursos",
          joinColumns = @JoinColumn(name = "aluno_id"),
          inverseJoinColumns = @JoinColumn(name = "curso_id")
  )
  private Set<Curso> cursos;

  public Aluno(Long id, String email, String nome, Set<Curso> cursos) {
    this.id = id;
    this.email = email;
    this.nome = nome;
    this.cursos = cursos;
  }

  public Aluno() {
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
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<Curso> getCursos() {
    return cursos;
  }

  public void setCursos(Set<Curso> cursos) {
    this.cursos = cursos;
  }
}