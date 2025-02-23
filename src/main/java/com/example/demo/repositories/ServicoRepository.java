package com.example.demo.repositories;

import com.example.demo.entities.Servico;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServicoRepository extends CrudRepository<Servico, Integer> {
    List<Servico> findAllByAtivo(Integer ativo);
    Optional<Servico> findByDescricao(String descricao);
}