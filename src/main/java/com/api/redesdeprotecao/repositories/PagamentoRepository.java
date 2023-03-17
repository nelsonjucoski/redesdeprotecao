package com.api.redesdeprotecao.repositories;

import com.api.redesdeprotecao.models.PagamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PagamentoRepository extends JpaRepository<PagamentoModel, UUID> {

}
