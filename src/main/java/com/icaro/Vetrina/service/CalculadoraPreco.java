package com.icaro.Vetrina.service;

import com.icaro.Vetrina.model.EstadoPeca;
import com.icaro.Vetrina.model.RegraPreco;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculadoraPreco {

    private final RegraPreco regra;

    public CalculadoraPreco(RegraPreco regra) {
        this.regra = regra;
    }

    public BigDecimal calcularPrecoVenda(BigDecimal precoMedio, EstadoPeca estado) {
        return precoMedio
                .multiply(regra.getFator(estado))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calcularOfertaInicial(BigDecimal precoVenda) {
        return precoVenda
                .multiply(regra.getPercentualOferta())
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calcularOfertaMaxima(BigDecimal precoVenda) {
        return precoVenda
                .multiply(regra.getPercentualOfertaMaxima())
                .setScale(2, RoundingMode.HALF_UP);
    }
}   