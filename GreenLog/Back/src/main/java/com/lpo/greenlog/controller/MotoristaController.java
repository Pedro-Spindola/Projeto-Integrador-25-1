package com.lpo.greenlog.controller;

import com.lpo.greenlog.model.Motorista;
import com.lpo.greenlog.repository.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/motoristas")
@CrossOrigin(origins = "http://localhost:4200")
public class MotoristaController {

    @Autowired
    private MotoristaRepository motoristaRepository;

    // 🟢 Listar todos os motoristas
    @GetMapping
    public List<Motorista> listarTodos() {
        return motoristaRepository.findAll(Sort.by("nome"));
    }

    // 🟢 Criar novo motorista
    @PostMapping
    public Motorista criar(@RequestBody Motorista motorista) {  
        return motoristaRepository.save(motorista);
    }

    // 🟢 Editar motorista existente
    @PutMapping("/{cpf}")
    public Motorista editar(@PathVariable String cpf, @RequestBody Motorista motoristaAtualizado) {
        Optional<Motorista> motoristaExistente = motoristaRepository.findById(cpf);
        if (motoristaExistente.isPresent()) {
            Motorista motorista = motoristaExistente.get();
            motorista.setNome(motoristaAtualizado.getNome());
            motorista.setDataNascimento(motoristaAtualizado.getDataNascimento());
            return motoristaRepository.save(motorista);
        } else {
            throw new RuntimeException("Motorista com CPF " + cpf + " não encontrado");
        }
    }

    // 🟢 Deletar motorista
    @DeleteMapping("/{cpf}")
    public void deletar(@PathVariable String cpf) {
        motoristaRepository.deleteById(cpf);
    }
}
