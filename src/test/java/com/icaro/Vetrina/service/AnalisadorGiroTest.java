package com.icaro.Vetrina.service;

import com.icaro.Vetrina.model.EstadoPeca;
import com.icaro.Vetrina.model.Peca;
import com.icaro.Vetrina.model.TipoPeca;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;

class AnalisadorGiroTest {

    private static final LocalDate HOJE = LocalDate.of(2026, 11, 9);

    private AnalisadorGiro analisador;
    private Peca vestidoParado;
    private List<Peca> pecas;

    @BeforeEach
    void preparar() {
        analisador = new AnalisadorGiro();

        Peca calca1 = peca(TipoPeca.CALCA, LocalDate.of(2026, 10, 1), LocalDate.of(2026, 10, 11));
        Peca calca2 = peca(TipoPeca.CALCA, LocalDate.of(2026, 10, 1), LocalDate.of(2026, 10, 21));
        Peca vestidoVendido = peca(TipoPeca.VESTIDO, LocalDate.of(2026, 6, 1), LocalDate.of(2026, 8, 30));
        vestidoParado = peca(TipoPeca.VESTIDO, LocalDate.of(2026, 7, 1), null);
        Peca blusaNova = peca(TipoPeca.BLUSA, LocalDate.of(2026, 10, 20), null);

        pecas = List.of(calca1, calca2, vestidoVendido, vestidoParado, blusaNova);
    }

    private Peca peca(TipoPeca tipo, LocalDate entrada, LocalDate venda) {
        Peca p = new Peca("Teste", tipo, "M", EstadoPeca.USADA);
        p.setDataEntrada(entrada);
        p.setDataVenda(venda);
        return p;
    }

    @Test
    void calcaLevaEmMediaQuinzeDiasParaVender() {
        Map<TipoPeca, Double> medias = analisador.mediaDiasAteVenderPorTipo(pecas);
        assertEquals(15.0, medias.get(TipoPeca.CALCA), 0.001);
    }

    @Test
    void vestidoContaSoOsQueVenderam() {
        Map<TipoPeca, Double> medias = analisador.mediaDiasAteVenderPorTipo(pecas);
        assertEquals(90.0, medias.get(TipoPeca.VESTIDO), 0.001);
    }

    @Test
    void tipoSemNenhumaVendaNaoApareceNaMedia() {
        Map<TipoPeca, Double> medias = analisador.mediaDiasAteVenderPorTipo(pecas);
        assertFalse(medias.containsKey(TipoPeca.BLUSA));
    }

    @Test
    void soOVestidoNaoVendidoHaMaisDeNoventaDiasEstaParado() {
        List<Peca> paradas = analisador.pecasParadas(pecas, HOJE, 90);
        assertEquals(1, paradas.size());
        assertSame(vestidoParado, paradas.get(0));
    }
}