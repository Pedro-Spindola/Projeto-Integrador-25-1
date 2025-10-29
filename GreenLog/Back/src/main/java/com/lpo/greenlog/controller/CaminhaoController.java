package com.lpo.greenlog.controller;

import com.lpo.greenlog.model.Caminhao;
import com.lpo.greenlog.repository.CaminhaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/caminhoes")
@CrossOrigin(origins = "http://localhost:4200")
public class CaminhaoController {

    @Autowired
    private CaminhaoRepository repository;

    @GetMapping
    public List<Caminhao> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    public Caminhao salvar(@RequestBody Caminhao caminhao) {
        return repository.save(caminhao);
    }

    @PutMapping("/{placa}")
    public ResponseEntity<Caminhao> atualizar(@PathVariable String placa, @RequestBody Caminhao caminhaoAtualizado) {
        Optional<Caminhao> caminhaoExistente = repository.findById(placa);
        if (caminhaoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        caminhaoAtualizado.setPlaca(placa); // garantir que placa está correta
        Caminhao atualizado = repository.save(caminhaoAtualizado);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{placa}")
    public ResponseEntity<Void> deletar(@PathVariable String placa) {
        Optional<Caminhao> caminhao = repository.findById(placa);
        if (caminhao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(placa);
        return ResponseEntity.noContent().build();
    }
}
