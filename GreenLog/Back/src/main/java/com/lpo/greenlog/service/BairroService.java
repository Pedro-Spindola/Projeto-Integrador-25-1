package com.lpo.greenlog.service;

import com.lpo.greenlog.model.Bairro;
import com.lpo.greenlog.repository.BairroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BairroService {

    @Autowired
    private BairroRepository bairroRepository;

    public Bairro salvar(Bairro bairro) {
        return bairroRepository.save(bairro);
    }

    public List<Bairro> listarTodos() {
        return bairroRepository.findAll();
    }

    public Bairro buscarPorId(Integer id) {
        Optional<Bairro> opt = bairroRepository.findById(id);
        return opt.orElseThrow(() -> new RuntimeException("Bairro não encontrado com id: " + id));
    }

    public Bairro editar(Integer id, Bairro dadosAtualizados) {
        // Garante que exista um bairro com esse id antes de editar
        Bairro existente = buscarPorId(id);
        existente.setNomeBairro(dadosAtualizados.getNomeBairro());
        return bairroRepository.save(existente);
    }

    public void deletar(Integer id) {
        // Garante que exista antes de tentar deletar
        buscarPorId(id);
        bairroRepository.deleteById(id);
    }
}
