package com.lpo.greenlog.service;

import com.lpo.greenlog.model.RuaConexao;
import com.lpo.greenlog.repository.RuaConexaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuaConexaoService {

    @Autowired
    private RuaConexaoRepository ruaConexaoRepository;

    public RuaConexao salvar(RuaConexao ruaConexao) {
        return ruaConexaoRepository.save(ruaConexao);
    }

    public List<RuaConexao> listarTodos() {
        return ruaConexaoRepository.findAll();
    }

    public RuaConexao buscarPorId(Integer id) {
        Optional<RuaConexao> opt = ruaConexaoRepository.findById(id);
        return opt.orElseThrow(() -> new RuntimeException("RuaConexao não encontrada com id: " + id));
    }

    public RuaConexao editar(Integer id, RuaConexao dadosAtualizados) {
        // Garante que exista uma conexão com esse id antes de editar
        RuaConexao existente = buscarPorId(id);
        existente.setBairroOrigem(dadosAtualizados.getBairroOrigem());
        existente.setBairroDestino(dadosAtualizados.getBairroDestino());
        existente.setDistanciaKm(dadosAtualizados.getDistanciaKm());
        return ruaConexaoRepository.save(existente);
    }

    public void deletar(Integer id) {
        // Garante que exista antes de tentar deletar
        buscarPorId(id);
        ruaConexaoRepository.deleteById(id);
    }
}
