package com.lpo.greenlog.repository;


import com.lpo.greenlog.model.PontoColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PontoColetaRepository extends JpaRepository<PontoColeta, Integer> {
}
