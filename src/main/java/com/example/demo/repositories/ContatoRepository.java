package com.example.demo.repositories;

import com.example.demo.entities.Contato;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends CrudRepository<Contato, Integer> {

    public Iterable<Contato> findByAlunoId(int id);

}
