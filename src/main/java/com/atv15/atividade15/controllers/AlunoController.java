package com.atv15.atividade15.controllers;

import com.atv15.atividade15.DTOs.AlunoDTO;
import com.atv15.atividade15.models.Aluno;
import com.atv15.atividade15.models.Curso;
import com.atv15.atividade15.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
  @Autowired
  AlunoService service;

  @GetMapping
  public ResponseEntity<List<Aluno>> getAllAlunos(){
    return service.getAllAlunos();
  }
  @GetMapping("/{id}")
  public ResponseEntity<Aluno> getAlunoById(@PathVariable Long id){
    return service.getAlunoById(id);
  }
  @PostMapping
  public ResponseEntity<Aluno> createAluno(@RequestBody AlunoDTO dto){
    return service.createAluno(dto);
  }
  @PutMapping("/{id}")
  public ResponseEntity<Aluno> updateAluno(@PathVariable Long id, @RequestBody AlunoDTO dto){
    return service.updateAluno(id, dto);
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<Aluno> deleteAluno(@PathVariable Long id){
    return service.deleteAluno(id);
  }
  @PostMapping("/{alunoId}/cursos/{cursoId}")
  public ResponseEntity<Aluno> syncAlunoToCurso(@PathVariable Long alunoId, @PathVariable Long cursoId){
    return service.syncAlunoToCurso(alunoId, cursoId);
  }
  @DeleteMapping("/{alunoId}/cursos/{cursoId}")
  public ResponseEntity<Aluno> DeleteAlunoFromCurso(@PathVariable Long alunoId, @PathVariable Long cursoId){
    return service.removeAlunoFromCurso(alunoId, cursoId);
  }
  @GetMapping("/{alunoId}/cursos")
  public ResponseEntity<Set<Curso>> listAllCursosFromAluno(@PathVariable Long alunoId){
    return service.listAllCursosOfAluno(alunoId);
  }
}