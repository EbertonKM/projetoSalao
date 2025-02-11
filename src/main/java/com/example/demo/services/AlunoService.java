package com.example.demo.services;

import com.example.demo.entities.Aluno;
import com.example.demo.repositories.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public Optional<Aluno> buscarPorId(int id){
        return alunoRepository.findById(id);
    }

    @Transactional
    public void excluir(Aluno aluno){
        alunoRepository.delete(aluno);
    }

    @Transactional
    public Aluno salvar(Aluno aluno){
        return alunoRepository.save(aluno);
    }

    @Transactional
    public List<Aluno> buscarTodos(){
        ArrayList<Aluno> alunos = new ArrayList<>();
        alunoRepository.findAll().forEach(alunos::add);
        return alunos;
    }

}
