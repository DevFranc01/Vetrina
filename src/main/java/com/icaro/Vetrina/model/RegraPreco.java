package com.icaro.Vetrina.model;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

public class RegraPreco {

    private final Map<EstadoPeca, BigDecimal> fatoresEstado = new EnumMap<>(EstadoPeca.class);
    private BigDecimal percentualOferta;
    private BigDecimal percentualOfertaMaxima;

    public RegraPreco(BigDecimal percentualOferta, BigDecimal percentualOfertaMaxima) {
        this.percentualOferta = percentualOferta;
        this.percentualOfertaMaxima = percentualOfertaMaxima;
    }

    public void definirFator(EstadoPeca estado, BigDecimal fator) {
        fatoresEstado.put(estado, fator);
    }

    public BigDecimal getFator(EstadoPeca estado) {
        BigDecimal fator = fatoresEstado.get(estado);
        if (fator == null) {
            throw new IllegalStateException("Fator não definido para o estado: " + estado);
        }
        return fator;
    }

    public BigDecimal getPercentualOferta() {
        return percentualOferta;
    }

    public void setPercentualOferta(BigDecimal percentualOferta) {
        this.percentualOferta = percentualOferta;
    }

    public BigDecimal getPercentualOfertaMaxima() {
        return percentualOfertaMaxima;
    }

    public void setPercentualOfertaMaxima(BigDecimal percentualOfertaMaxima) {
        this.percentualOfertaMaxima = percentualOfertaMaxima;
    }
}