package com.ms.cartoes.services.impl;

import com.ms.cartoes.dtos.PropostaResponse;
import com.ms.cartoes.entities.Cliente;
import com.ms.cartoes.entities.Proposta;
import com.ms.cartoes.enums.StatusCliente;
import com.ms.cartoes.enums.StatusProposta;
import com.ms.cartoes.exception.PropostaException;
import com.ms.cartoes.repositories.PropostaRepository;
import com.ms.cartoes.services.AnaliseLimiteCreditoService;
import com.ms.cartoes.services.CartaoService;
import com.ms.cartoes.services.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PropostaServiceImplTest {

    @Mock
    private PropostaRepository propostaRepository;

    @Mock
    private ClienteService clienteService;

    @Mock
    private AnaliseLimiteCreditoService analiseLimiteCreditoService;

    @Mock
    private CartaoService cartaoService;

    @InjectMocks
    private PropostaServiceImpl propostaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarPropostaClienteNaoCadastrado() {
        Long idCliente = 1L;
        Cliente cliente = new Cliente(idCliente,"SERGIO DIOGO","sergiodiogo@gmail.com","Rua Aldemir", new BigDecimal(8000), StatusCliente.ATIVO);
        Proposta proposta = new Proposta(1L,"CARTAO-CREDITO", new BigDecimal(5000),cliente,StatusProposta.APROVADA, LocalDateTime.now());


        when(clienteService.buscarPorID(idCliente)).thenReturn(Optional.empty());

        PropostaException exception = assertThrows(PropostaException.class, () -> {
            propostaService.criarProposta(idCliente, proposta);
        });

        assertEquals("Não foi possivel criar proposta, Cliente não esta cadastrado!", exception.getMessage());
        verify(clienteService, times(1)).buscarPorID(idCliente);
        verifyNoMoreInteractions(clienteService, propostaRepository, analiseLimiteCreditoService, cartaoService);
    }

    @Test
    public void testCriarPropostaClienteComPropostaAprovada() {
        Long idCliente = 1L;
        Cliente cliente = new Cliente(idCliente,"SERGIO DIOGO","sergiodiogo@gmail.com","Rua Aldemir", new BigDecimal(8000), StatusCliente.ATIVO);
        Proposta proposta = new Proposta(1L,"CARTAO-CREDITO", new BigDecimal(5000),cliente,StatusProposta.APROVADA, LocalDateTime.now());


        when(clienteService.buscarPorID(idCliente)).thenReturn(Optional.of(cliente));
        when(propostaRepository.findByClienteAndStatus(cliente, StatusProposta.APROVADA)).thenReturn(Optional.of(new Proposta()));

        PropostaException exception = assertThrows(PropostaException.class, () -> {
            propostaService.criarProposta(idCliente, proposta);
        });

        assertEquals("Não foi possivel criar uma nova proposta, Cliente já tem uma proposta Aprovada!", exception.getMessage());
        verify(clienteService, times(1)).buscarPorID(idCliente);
        verify(propostaRepository, times(1)).findByClienteAndStatus(cliente, StatusProposta.APROVADA);
        verifyNoMoreInteractions(clienteService, propostaRepository, analiseLimiteCreditoService, cartaoService);
    }

    @Test
    public void testCriarPropostaAprovada() {
        Long idCliente = 1L;
        Cliente cliente = new Cliente(idCliente,"SERGIO DIOGO","sergiodiogo@gmail.com","Rua Aldemir", new BigDecimal(8000), StatusCliente.ATIVO);
        Proposta proposta = new Proposta(1L,"CARTAO-CREDITO", new BigDecimal(5000),cliente,StatusProposta.APROVADA, LocalDateTime.now());

        proposta.setStatus(StatusProposta.APROVADA);

        when(clienteService.buscarPorID(idCliente)).thenReturn(Optional.of(cliente));
        when(propostaRepository.findByClienteAndStatus(cliente, StatusProposta.APROVADA)).thenReturn(Optional.empty());
        doNothing().when(analiseLimiteCreditoService).analisar(proposta);
        when(propostaRepository.save(proposta)).thenReturn(proposta);

        PropostaResponse response = propostaService.criarProposta(idCliente, proposta);

        assertNotNull(response);
        assertEquals("Sua proposta foi aprovada, seu cartão esta sendo emitido em breve chegará até você.", response.getMensagem());
        verify(clienteService, times(1)).buscarPorID(idCliente);
        verify(propostaRepository, times(1)).findByClienteAndStatus(cliente, StatusProposta.APROVADA);
        verify(analiseLimiteCreditoService, times(1)).analisar(proposta);
        verify(propostaRepository, times(1)).save(proposta);
        verify(cartaoService, times(1)).emitirCartao(proposta);
    }

}
