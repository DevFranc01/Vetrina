package com.icaro.Vetrina;

import com.icaro.Vetrina.model.EstadoPeca;
import com.icaro.Vetrina.model.Peca;
import com.icaro.Vetrina.model.RegraPreco;
import com.icaro.Vetrina.model.TipoPeca;
import com.icaro.Vetrina.service.CalculadoraPreco;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        RegraPreco regraJoy = new RegraPreco(
                new BigDecimal("0.35"),
                new BigDecimal("0.45")
        );
        regraJoy.definirFator(EstadoPeca.NOVA_COM_ETIQUETA, new BigDecimal("1.20"));
        regraJoy.definirFator(EstadoPeca.SEMINOVA, new BigDecimal("1.00"));
        regraJoy.definirFator(EstadoPeca.USADA, new BigDecimal("0.75"));
        regraJoy.definirFator(EstadoPeca.COM_DEFEITO, new BigDecimal("0.40"));

        CalculadoraPreco calculadora = new CalculadoraPreco(regraJoy);

        Peca peca = new Peca("Zara", TipoPeca.CALCA, "38", EstadoPeca.USADA);
        BigDecimal precoMedio = new BigDecimal("80.00");

        BigDecimal precoVenda = calculadora.calcularPrecoVenda(precoMedio, peca.getEstado());
        peca.setPrecoSugerido(precoVenda);

        BigDecimal ofertaInicial = calculadora.calcularOfertaInicial(precoVenda);
        BigDecimal ofertaMaxima = calculadora.calcularOfertaMaxima(precoVenda);

        System.out.println(peca);
        System.out.println("Preço de venda sugerido: R$ " + precoVenda);
        System.out.println("Oferta inicial p/ fornecedora: R$ " + ofertaInicial);
        System.out.println("Oferta máxima p/ fornecedora:  R$ " + ofertaMaxima);
    }
}   