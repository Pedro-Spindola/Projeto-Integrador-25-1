package com.lpo.greenlog.repository;

import com.lpo.greenlog.model.Caminhao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaminhaoRepository extends JpaRepository<Caminhao, String> {
}
