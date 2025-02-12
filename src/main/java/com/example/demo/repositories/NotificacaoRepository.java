package com.example.demo.repositories;

import com.example.demo.entities.Notificacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacaoRepository extends CrudRepository<Notificacao, Integer> { }