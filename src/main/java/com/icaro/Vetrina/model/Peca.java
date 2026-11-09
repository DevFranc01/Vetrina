package com.icaro.Vetrina.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Peca {

    private String marca;
    private TipoPeca tipo;
    private String tamanho;
    private EstadoPeca estado;
    private BigDecimal precoSugerido;
    private BigDecimal precoVenda;
    private LocalDate dataEntrada;
    private LocalDate dataVenda;

    public Peca(String marca, TipoPeca tipo, String tamanho, EstadoPeca estado) {
        this.marca = marca;
        this.tipo = tipo;
        this.tamanho = tamanho;
        this.estado = estado;
        this.dataEntrada = LocalDate.now();
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public TipoPeca getTipo() {
        return tipo;
    }

    public void setTipo(TipoPeca tipo) {
        this.tipo = tipo;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public EstadoPeca getEstado() {
        return estado;
    }

    public void setEstado(EstadoPeca estado) {
        this.estado = estado;
    }

    public BigDecimal getPrecoSugerido() {
        return precoSugerido;
    }

    public void setPrecoSugerido(BigDecimal precoSugerido) {
        this.precoSugerido = precoSugerido;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public boolean estaVendida() {
        return dataVenda != null;
    }

    public long diasNoEstoque(LocalDate hoje) {
        LocalDate fim = estaVendida() ? dataVenda : hoje;
        return ChronoUnit.DAYS.between(dataEntrada, fim);
    }

    @Override
    public String toString() {
        return tipo + " " + marca + " (" + tamanho + ") - " + estado
                + " | sugerido: R$ " + precoSugerido
                + " | entrada: " + dataEntrada;
    }
}
