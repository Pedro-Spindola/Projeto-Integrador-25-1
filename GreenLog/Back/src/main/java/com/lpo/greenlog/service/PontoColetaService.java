package com.lpo.greenlog.service;

import com.lpo.greenlog.model.PontoColeta;
import com.lpo.greenlog.repository.PontoColetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PontoColetaService {

    @Autowired
    private PontoColetaRepository pontoColetaRepository;

    public PontoColeta salvar(PontoColeta pontoColeta) {
        return pontoColetaRepository.save(pontoColeta);
    }

    public List<PontoColeta> listarTodos() {
        return pontoColetaRepository.findAll();
    }

    public PontoColeta buscarPorId(Integer id) {
        Optional<PontoColeta> opt = pontoColetaRepository.findById(id);
        return opt.orElseThrow(() -> new RuntimeException("PontoColeta não encontrado com id: " + id));
    }

    public PontoColeta editar(Integer id, PontoColeta dadosAtualizados) {
        // Garante que exista um ponto de coleta com esse id antes de editar
        PontoColeta existente = buscarPorId(id);

        existente.setBairro(dadosAtualizados.getBairro());
        existente.setNome(dadosAtualizados.getNome());
        existente.setResponsavel(dadosAtualizados.getResponsavel());
        existente.setTelefoneResponsavel(dadosAtualizados.getTelefoneResponsavel());
        existente.setEmailResponsavel(dadosAtualizados.getEmailResponsavel());
        existente.setEndereco(dadosAtualizados.getEndereco());
        existente.setHorarioFuncionamento(dadosAtualizados.getHorarioFuncionamento());
        existente.setTiposResiduoAceitos(dadosAtualizados.getTiposResiduoAceitos());

        return pontoColetaRepository.save(existente);
    }

    public void deletar(Integer id) {
        // Garante que exista antes de tentar deletar
        buscarPorId(id);
        pontoColetaRepository.deleteById(id);
    }
}
