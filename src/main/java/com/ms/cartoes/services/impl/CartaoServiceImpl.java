package com.ms.cartoes.services.impl;

import com.ms.cartoes.dtos.CartaoDTO;
import com.ms.cartoes.entities.Cartao;
import com.ms.cartoes.entities.Proposta;
import com.ms.cartoes.repositories.CartaoRepository;
import com.ms.cartoes.services.CartaoService;
import com.ms.cartoes.utils.CartaoUtils;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CartaoServiceImpl implements CartaoService {

    final CartaoRepository cartaoRepository;
    final RestTemplate restTemplate;

    @Value("${emissor.url}")
    private String emissorUrl;

    public CartaoServiceImpl(CartaoRepository cartaoRepository, RestTemplate restTemplate) {
        this.cartaoRepository = cartaoRepository;
        this.restTemplate = restTemplate;
    }
    @Override
    @Transactional
    public Optional<Cartao> buscarPorID(Long id) {
        return cartaoRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Cartao> buscarTodosCartaos() {
        return cartaoRepository.findAll();
    }

    @Override
    @Transactional
    public void emitirCartao(Proposta proposta) {
        Cartao cartao = new Cartao();
        cartao.setConta(CartaoUtils.gerarNumeroConta());
        cartao.setNumero(CartaoUtils.gerarNumeroCartao());
        cartao.setProposta(proposta);
        cartao.setValidade("20/32");
        cartao.setCcv(CartaoUtils.gerarCCV());
        cartao.setLimite(proposta.getLimite());
        cartao.setDataHoraRegistro(LocalDateTime.now());
        cartaoRepository.save(cartao);

        if(cartao.getId() != null){
            CartaoDTO cartaoDTO = new CartaoDTO();
            cartaoDTO.setNumeroCartao(cartao.getNumero());
            cartaoDTO.setCcv(cartao.getCcv());
            cartaoDTO.setValidade(cartao.getValidade());
            cartaoDTO.setLimite(cartao.getLimite());
            cartaoDTO.setEmail(cartao.getProposta().getCliente().getEmail());
            cartaoDTO.setNomeCliente(cartao.getProposta().getCliente().getNome());

            // Envia os dados para o microserviço Emissor.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<CartaoDTO> request = new HttpEntity<>(cartaoDTO, headers);
            restTemplate.postForEntity(emissorUrl + "/emissor", request, Void.class);
        }
    }
}
