package com.example.demo.services;

import com.example.demo.entities.Pessoa;
import com.example.demo.entities.Profissional;
import com.example.demo.entities.Servico;
import com.example.demo.repositories.PessoaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional
    public Optional<Profissional> buscarProfissionalComServicos(String email) {
        return pessoaRepository.findByEmail(email)
                .filter(p -> p instanceof Profissional)
                .map(p -> (Profissional) p);
    }
}