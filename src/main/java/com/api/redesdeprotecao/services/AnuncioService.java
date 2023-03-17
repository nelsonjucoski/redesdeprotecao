package com.api.redesdeprotecao.services;

import com.api.redesdeprotecao.models.AnuncioModel;
import com.api.redesdeprotecao.repositories.AnuncioRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AnuncioService {

    private final AnuncioRepository anuncioRepository;

    public AnuncioService(AnuncioRepository anuncioRepository) {
        this.anuncioRepository = anuncioRepository;
    }

    @Transactional
    public AnuncioModel salvar(AnuncioModel anuncioModel){
        return anuncioRepository.save(anuncioModel);
    }

    @Transactional(readOnly = true)
    public AnuncioModel buscaAnuncioPorId( UUID idAnuncio){
        AnuncioModel anuncioModel = anuncioRepository.findById(idAnuncio).get();
        return anuncioModel;
    }

    @Transactional
    public void deletar(UUID idAnuncio) {
        anuncioRepository.deleteById(idAnuncio);
    }

    @Transactional(readOnly = true)
    public List<AnuncioModel> listarAnuncios() {
        List<AnuncioModel> anuncioModels = anuncioRepository.findAll();

        return  anuncioModels;
    }
}
