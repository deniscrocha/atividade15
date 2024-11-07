package com.atv15.atividade15.DTOs;

import com.atv15.atividade15.models.Aluno;

import java.util.Set;

public record CursoDTO(String nome, String descricao, Set<Aluno> alunos){ }