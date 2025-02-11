package com.example.demo.services;

import com.example.demo.entities.Contato;
import com.example.demo.repositories.ContatoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContatoService {

    private ContatoRepository contatoRepository;

    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    @Transactional
    public void excluir(Contato contato) {
        contatoRepository.delete(contato);
    }

    @Transactional
    public List<Contato> buscarPorAlunoId(int id){
        List<Contato> contatos = new ArrayList<>();
        contatoRepository.findByAlunoId(id).forEach(contatos::add);
        return contatos;
    }

    @Transactional
    public Contato salvar(Contato contato){
        return contatoRepository.save(contato);
    }

}
