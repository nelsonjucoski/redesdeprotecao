package com.api.redesdeprotecao.repositories;

import com.api.redesdeprotecao.models.AnuncioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnuncioRepository  extends JpaRepository<AnuncioModel, UUID> {

}
