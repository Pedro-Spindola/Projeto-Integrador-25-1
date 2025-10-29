package com.lpo.greenlog.controller;

import com.lpo.greenlog.model.RuaConexao;
import com.lpo.greenlog.service.RuaConexaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ruas-conexoes")
public class RuaConexaoController {

    @Autowired
    private RuaConexaoService ruaConexaoService;

    @GetMapping
    public ResponseEntity<List<RuaConexao>> listarTodos() {
        List<RuaConexao> lista = ruaConexaoService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RuaConexao> buscarPorId(@PathVariable Integer id) {
        try {
            RuaConexao conexao = ruaConexaoService.buscarPorId(id);
            return ResponseEntity.ok(conexao);
        } catch (RuntimeException ex) {
            // Se não encontrar, devolve 404 Not Found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PostMapping
    public ResponseEntity<RuaConexao> criar(@RequestBody RuaConexao ruaConexao) {
        RuaConexao criado = ruaConexaoService.salvar(ruaConexao);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RuaConexao> editar(
            @PathVariable Integer id,
            @RequestBody RuaConexao ruaConexao
    ) {
        try {
            RuaConexao atualizado = ruaConexaoService.editar(id, ruaConexao);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException ex) {
            // Se não encontrar para editar, devolve 404 Not Found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            ruaConexaoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            // Se não encontrar para deletar, devolve 404 Not Found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
