package com.lpo.greenlog.repository;

import com.lpo.greenlog.model.RuaConexao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuaConexaoRepository extends JpaRepository<RuaConexao, Integer> {
}
