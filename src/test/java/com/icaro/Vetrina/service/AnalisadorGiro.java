package com.icaro.Vetrina.service;

import com.icaro.Vetrina.model.Peca;
import com.icaro.Vetrina.model.TipoPeca;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class AnalisadorGiro {

    public Map<TipoPeca, Double> mediaDiasAteVenderPorTipo(List<Peca> pecas) {
        Map<TipoPeca, Long> somaDias = new EnumMap<>(TipoPeca.class);
        Map<TipoPeca, Integer> quantidade = new EnumMap<>(TipoPeca.class);

        for (Peca peca : pecas) {
            if (!peca.estaVendida()) {
                continue;
            }
            TipoPeca tipo = peca.getTipo();
            long dias = peca.diasNoEstoque(peca.getDataVenda());

            somaDias.put(tipo, somaDias.getOrDefault(tipo, 0L) + dias);
            quantidade.put(tipo, quantidade.getOrDefault(tipo, 0) + 1);
        }

        Map<TipoPeca, Double> medias = new EnumMap<>(TipoPeca.class);
        for (TipoPeca tipo : quantidade.keySet()) {
            double media = (double) somaDias.get(tipo) / quantidade.get(tipo);
            medias.put(tipo, media);
        }
        return medias;
    }

    public List<Peca> pecasParadas(List<Peca> pecas, LocalDate hoje, int limiteDias) {
        List<Peca> paradas = new ArrayList<>();
        for (Peca peca : pecas) {
            if (!peca.estaVendida() && peca.diasNoEstoque(hoje) > limiteDias) {
                paradas.add(peca);
            }
        }
        return paradas;
    }
}