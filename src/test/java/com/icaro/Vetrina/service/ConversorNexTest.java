package com.icaro.Vetrina.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ConversorNexTest {

    private final ConversorNex conversor = new ConversorNex();

    @Test
    void precoSimplesDoNex() {
        assertEquals(new BigDecimal("60.00"), conversor.converterPreco("R$ 60,00"));
    }

    @Test
    void precoComPontoDeMilhar() {
        assertEquals(new BigDecimal("1234.56"), conversor.converterPreco("R$ 1.234,56"));
    }

    @Test
    void precoSemCentavosGanhaDuasCasas() {
        assertEquals(new BigDecimal("5.00"), conversor.converterPreco("5"));
    }

    @Test
    void precoVazioViraNull() {
        assertNull(conversor.converterPreco(""));
        assertNull(conversor.converterPreco(null));
        assertNull(conversor.converterPreco("R$"));
    }

    @Test
    void dataComHorarioDoNex() {
        assertEquals(LocalDate.of(2024, 1, 12), conversor.converterData("12/01/2024 11:07"));
    }

    @Test
    void dataSemHorario() {
        assertEquals(LocalDate.of(2026, 11, 9), conversor.converterData("09/11/2026"));
    }

    @Test
    void diaEMesNaoSaoTrocados() {
        assertEquals(LocalDate.of(2025, 3, 1), conversor.converterData("01/03/2025"));
    }

    @Test
    void dataVaziaViraNull() {
        assertNull(conversor.converterData(""));
        assertNull(conversor.converterData(null));
    }
}