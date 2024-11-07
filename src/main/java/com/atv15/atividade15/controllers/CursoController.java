package com.atv15.atividade15.controllers;

import com.atv15.atividade15.DTOs.CursoDTO;
import com.atv15.atividade15.models.Aluno;
import com.atv15.atividade15.models.Curso;
import com.atv15.atividade15.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/cursos")
public class CursoController {
  @Autowired
  CursoService service;

  @GetMapping
  public ResponseEntity<List<Curso>> getAllCurso(){
    return service.getAllCursos();
  }
  @GetMapping("/{id}")
  public ResponseEntity<Curso> getCursoById(@PathVariable Long id){
    return service.getCursoById(id);
  }
  @PostMapping
  public ResponseEntity<Curso> createCurso(@RequestBody CursoDTO dto){
    return service.createCurso(dto);
  }
  @PutMapping("/{id}")
  public ResponseEntity<Curso> updateCurso(@PathVariable Long id, @RequestBody CursoDTO dto){
    return service.updateCurso(id, dto);
  }
  @DeleteMapping("/{id}")
  public ResponseEntity deleteCurso(@PathVariable Long id){
    return service.deleteCurso(id);
  }
  @GetMapping("/{id}/alunos")
  public ResponseEntity<Set<Aluno>> getAlunosByCurso(@PathVariable Long id){
    return service.getAllAlunosByCurso(id);
  }
}