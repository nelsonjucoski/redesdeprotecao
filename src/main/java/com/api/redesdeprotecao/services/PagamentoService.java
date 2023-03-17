package com.api.redesdeprotecao.services;

import com.api.redesdeprotecao.models.PagamentoModel;
import com.api.redesdeprotecao.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PagamentoService {

    final
    PagamentoRepository pagamentoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public PagamentoModel salvar(PagamentoModel pagamentoModel){
        return pagamentoRepository.save(pagamentoModel);
    }

    public List<PagamentoModel> listaPagamentos(){
        return pagamentoRepository.findAll();
    }

    public void deletar(UUID PagId){
        pagamentoRepository.deleteById(PagId);
    }


}
