package com.example.demo.repositories;

import com.example.demo.entities.Cargo;
import com.example.demo.entities.Pessoa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Integer> {
    Optional<Pessoa> findByEmailAndSenha(String email, String senha);
    Optional<Pessoa> findByEmail(String email);
    Optional<Pessoa> findByNome(String nome);
    List<Pessoa> getAllByCargo(Cargo cargo);
}