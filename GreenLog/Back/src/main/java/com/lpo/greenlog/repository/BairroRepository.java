package com.lpo.greenlog.repository;

import com.lpo.greenlog.model.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Integer> {
}
