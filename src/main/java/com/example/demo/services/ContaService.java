package com.example.demo.services;

import com.example.demo.entities.Cargo;
import com.example.demo.entities.Pessoa;
import com.example.demo.repositories.CargoRepository;
import com.example.demo.repositories.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContaService {

    PessoaRepository pessoaRepository;
    CargoRepository cargoRepository;

    public ContaService(PessoaRepository pessoaRepository, CargoRepository cargoRepository) {
        this.pessoaRepository = pessoaRepository;
        this.cargoRepository = cargoRepository;
    }

    public Optional<Pessoa> validarLogin(String email, String senha) {
        return pessoaRepository.findByEmailAndSenha(email, senha);
    }

    public boolean emailDisponivel(String email) {
        Optional<Pessoa> pessoa = pessoaRepository.findByEmail(email);
        return pessoa.isEmpty();
    }

    public void salvar(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
    }

    public void setCargo(Pessoa pessoa,Cargo cargo) {
        pessoa.setCargo(cargo);
        salvar(pessoa);
    }

    public Cargo cargoCliente() {
        return cargoRepository.findById(1);
    }
    public Cargo cargoProfissional() {
        return cargoRepository.findById(2);
    }
    public Cargo cargoGerente() {
        return cargoRepository.findById(3);
    }
}
