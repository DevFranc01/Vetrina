package com.icaro.Vetrina.service;

import com.icaro.Vetrina.model.EstadoPeca;
import com.icaro.Vetrina.model.RegraPreco;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculadoraPrecoTest {

    private CalculadoraPreco calculadora;

    @BeforeEach
    void preparar() {
        RegraPreco regra = new RegraPreco(new BigDecimal("0.35"), new BigDecimal("0.45"));
        regra.definirFator(EstadoPeca.NOVA_COM_ETIQUETA, new BigDecimal("1.20"));
        regra.definirFator(EstadoPeca.SEMINOVA, new BigDecimal("1.00"));
        regra.definirFator(EstadoPeca.USADA, new BigDecimal("0.75"));
        calculadora = new CalculadoraPreco(regra);
    }

    @Test
    void pecaUsadaValeSetentaECincoPorCentoDoPrecoMedio() {
        BigDecimal preco = calculadora.calcularPrecoVenda(new BigDecimal("80.00"), EstadoPeca.USADA);
        assertEquals(new BigDecimal("60.00"), preco);
    }

    @Test
    void pecaNovaComEtiquetaValeMaisQueOPrecoMedio() {
        BigDecimal preco = calculadora.calcularPrecoVenda(new BigDecimal("80.00"), EstadoPeca.NOVA_COM_ETIQUETA);
        assertEquals(new BigDecimal("96.00"), preco);
    }

    @Test
    void ofertaInicialEhTrintaECincoPorCentoDoPrecoDeVenda() {
        BigDecimal oferta = calculadora.calcularOfertaInicial(new BigDecimal("60.00"));
        assertEquals(new BigDecimal("21.00"), oferta);
    }

    @Test
    void ofertaMaximaEhQuarentaECincoPorCentoDoPrecoDeVenda() {
        BigDecimal oferta = calculadora.calcularOfertaMaxima(new BigDecimal("60.00"));
        assertEquals(new BigDecimal("27.00"), oferta);
    }

    @Test
    void precoEhArredondadoParaCentavos() {
        BigDecimal preco = calculadora.calcularPrecoVenda(new BigDecimal("33.33"), EstadoPeca.USADA);
        assertEquals(new BigDecimal("25.00"), preco);
    }

    @Test
    void estadoSemFatorConfiguradoLancaErro() {
        assertThrows(IllegalStateException.class,
                () -> calculadora.calcularPrecoVenda(new BigDecimal("80.00"), EstadoPeca.COM_DEFEITO));
    }
}