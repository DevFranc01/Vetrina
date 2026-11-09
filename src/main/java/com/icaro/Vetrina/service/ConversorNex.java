package com.icaro.Vetrina.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConversorNex {

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public BigDecimal converterPreco(String texto) {
        if (texto == null || texto.isBlank()) {
            return null;
        }
        String numeros = texto.replaceAll("[^0-9,\\-]", "");
        if (numeros.isEmpty()) {
            return null;
        }
        return new BigDecimal(numeros.replace(",", "."))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public LocalDate converterData(String texto) {
        if (texto == null || texto.isBlank()) {
            return null;
        }
        String soData = texto.trim().split(" ")[0];
        return LocalDate.parse(soData, FORMATO_DATA);
    }
}