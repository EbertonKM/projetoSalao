package com.example.demo.repositories;

import com.example.demo.entities.Servico;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepository extends CrudRepository<Servico, Integer> {}