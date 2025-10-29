package com.lpo.greenlog.controller;

import com.lpo.greenlog.model.PontoColeta;
import com.lpo.greenlog.service.PontoColetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pontos-coleta")
public class PontoColetaController {

    @Autowired
    private PontoColetaService pontoColetaService;

    @GetMapping
    public ResponseEntity<List<PontoColeta>> listarTodos() {
        List<PontoColeta> lista = pontoColetaService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PontoColeta> buscarPorId(@PathVariable Integer id) {
        try {
            PontoColeta ponto = pontoColetaService.buscarPorId(id);
            return ResponseEntity.ok(ponto);
        } catch (RuntimeException ex) {
            // Se não encontrar, retorna 404 Not Found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<PontoColeta> criar(@RequestBody PontoColeta pontoColeta) {
        PontoColeta criado = pontoColetaService.salvar(pontoColeta);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }


    @PutMapping("/{id}")
    public ResponseEntity<PontoColeta> editar(
            @PathVariable Integer id,
            @RequestBody PontoColeta pontoColeta
    ) {
        try {
            PontoColeta atualizado = pontoColetaService.editar(id, pontoColeta);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException ex) {
            // Se não encontrar para editar, retorna 404 Not Found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            pontoColetaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            // Se não encontrar para deletar, retorna 404 Not Found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
