package com.example.demo.repositories;

import com.example.demo.entities.Atendimento;
import com.example.demo.entities.Cliente;
import com.example.demo.entities.Profissional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtendimentoRepository extends CrudRepository<Atendimento, Integer> {
    List<Atendimento> findAllByCliente(Cliente cliente);
    List<Atendimento> findAllByProfissional(Profissional profissional);
    List<Atendimento> findAll();
}