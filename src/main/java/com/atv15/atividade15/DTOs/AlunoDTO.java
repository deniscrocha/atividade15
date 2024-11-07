package com.atv15.atividade15.DTOs;

import com.atv15.atividade15.models.Curso;

import java.util.Set;

public record AlunoDTO(String nome, String email, Set<Curso> cursos) { }