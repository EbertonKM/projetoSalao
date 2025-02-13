package com.example.demo.services;

import com.example.demo.entities.Pessoa;
import com.example.demo.repositories.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    PessoaRepository pessoaRepository;

    public LoginService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Optional<Pessoa> validarLogin(String email, String senha) {
        return pessoaRepository.findById(1);
    }
}
