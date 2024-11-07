package com.atv15.atividade15.services;

import com.atv15.atividade15.DTOs.CursoDTO;
import com.atv15.atividade15.models.Aluno;
import com.atv15.atividade15.models.Curso;
import com.atv15.atividade15.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CursoService {
  @Autowired
  private CursoRepository repository;

  public ResponseEntity<Curso> createCurso(CursoDTO dto){
    Curso novoCurso = new Curso();
    novoCurso.setNome(dto.nome());
    novoCurso.setDescricao(dto.descricao());
    novoCurso.setAlunos(dto.alunos());
    repository.save(novoCurso);
    return ResponseEntity.ok(novoCurso);
  }
  public ResponseEntity<List<Curso>> getAllCursos(){
    return ResponseEntity.ok(repository.findAll());
  }
  public ResponseEntity<Curso> getCursoById(Long id){
    Optional<Curso> curso = repository.findById(id);
    if(curso.isEmpty()){
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(curso.get());
  }
  public ResponseEntity<Curso> updateCurso(Long id, CursoDTO dto){
    Optional<Curso> curso = repository.findById(id);
    if(curso.isEmpty()){
      return ResponseEntity.notFound().build();
    }
    curso.get().setNome(dto.nome());
    curso.get().setDescricao(dto.descricao());
    curso.get().setAlunos(dto.alunos());
    repository.save(curso.get());
    return ResponseEntity.ok(curso.get());
  }
  public ResponseEntity deleteCurso(Long id){
    Optional<Curso> curso = repository.findById(id);
    if(curso.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    repository.deleteById(id);
    return ResponseEntity.ok().build();
  }
  public ResponseEntity<Set<Aluno>> getAllAlunosByCurso(Long id){
    Optional<Curso> curso = repository.findById(id);
    if(curso.isEmpty()){
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(curso.get().getAlunos());
  }
}