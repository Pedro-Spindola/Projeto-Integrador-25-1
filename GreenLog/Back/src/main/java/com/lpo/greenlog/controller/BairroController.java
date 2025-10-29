package com.lpo.greenlog.controller;

import com.lpo.greenlog.model.Bairro;
import com.lpo.greenlog.service.BairroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bairros")
public class BairroController {

    @Autowired
    private BairroService bairroService;

    @GetMapping
    public List<Bairro> listarTodos() {
        return bairroService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bairro> buscarPorId(@PathVariable Integer id) {
        Bairro bairro = bairroService.buscarPorId(id);
        return ResponseEntity.ok(bairro);
    }

    @PostMapping
    public ResponseEntity<Bairro> criar(@RequestBody Bairro bairro) {
        Bairro criado = bairroService.salvar(bairro);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bairro> editar(@PathVariable Integer id, @RequestBody Bairro bairro) {
        Bairro atualizado = bairroService.editar(id, bairro);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        bairroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
