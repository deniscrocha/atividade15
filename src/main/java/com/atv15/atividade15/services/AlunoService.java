package com.atv15.atividade15.services;

import com.atv15.atividade15.DTOs.AlunoDTO;
import com.atv15.atividade15.models.Aluno;
import com.atv15.atividade15.models.Curso;
import com.atv15.atividade15.repositories.AlunoRepository;
import com.atv15.atividade15.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AlunoService {
  @Autowired
  private AlunoRepository repository;
  @Autowired
  private CursoRepository cursoRepository;

  public ResponseEntity<Aluno> createAluno(AlunoDTO dto){
    Aluno novoAluno = new Aluno();
    novoAluno.setNome(dto.nome());
    novoAluno.setEmail(dto.email());
    novoAluno.setCursos(dto.cursos());
    repository.save(novoAluno);
    return ResponseEntity.ok(novoAluno);
  }
  public ResponseEntity<List<Aluno>> getAllAlunos(){
    return ResponseEntity.ok(repository.findAll());
  }
  public ResponseEntity<Aluno> getAlunoById(Long id){
    Optional<Aluno> aluno = repository.findById(id);
    if(aluno.isEmpty()){
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(aluno.get());
  }
  public ResponseEntity<Aluno> updateAluno(Long id, AlunoDTO dto){
    Optional<Aluno> aluno = repository.findById(id);
    if(aluno.isEmpty()){
      return ResponseEntity.notFound().build();
    }
    aluno.get().setNome(dto.nome());
    aluno.get().setEmail(dto.email());
    aluno.get().setCursos(dto.cursos());
    repository.save(aluno.get());
    return ResponseEntity.ok(aluno.get());
  }
  public ResponseEntity<Aluno> deleteAluno(Long id){
    Optional<Aluno> aluno = repository.findById(id);
    if(aluno.isEmpty()){
      return ResponseEntity.notFound().build();
    }
    repository.deleteById(id);
    return ResponseEntity.ok().build();
  }
  public ResponseEntity<Aluno> syncAlunoToCurso(Long alunoId, Long cursoId){
    Optional<Aluno> alunoOptional = repository.findById(alunoId);
    Optional<Curso> cursoOptional = cursoRepository.findById(cursoId);
    if(alunoOptional.isEmpty() || cursoOptional.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    Aluno aluno = alunoOptional.get();
    Curso curso = cursoOptional.get();
    Set<Curso> cursos = aluno.getCursos();
    cursos.add(curso);
    aluno.setCursos(cursos);
    repository.save(aluno);
    return ResponseEntity.ok(aluno);
  }
  public ResponseEntity<Aluno> removeAlunoFromCurso(Long alunoId, Long cursoId){
    Optional<Aluno> alunoOptional = repository.findById(alunoId);
    Optional<Curso> cursoOptional = cursoRepository.findById(cursoId);
    if(alunoOptional.isEmpty() || cursoOptional.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    Aluno aluno = alunoOptional.get();
    Curso curso = cursoOptional.get();
    Set<Curso> cursos = aluno.getCursos();
    cursos.remove(curso);
    aluno.setCursos(cursos);
    repository.save(aluno);
    return ResponseEntity.ok(aluno);
  }
  public ResponseEntity<Set<Curso>> listAllCursosOfAluno(Long id){
    Optional<Aluno> alunoOptional = repository.findById(id);
    if(alunoOptional.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.ok(alunoOptional.get().getCursos());
  }
}