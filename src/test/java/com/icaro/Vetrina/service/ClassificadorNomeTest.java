package com.icaro.Vetrina.service;

import com.icaro.Vetrina.model.TipoPeca;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ClassificadorNomeTest {

    private final ClassificadorNome classificador = new ClassificadorNome();

    @Test
    void bataComTamanhoM() {
        String nome = "BATA CREPE AZUL TAM M SEM MARCA";
        assertEquals(TipoPeca.BATA, classificador.classificarTipo(nome));
        assertEquals("M", classificador.extrairTamanho(nome));
    }

    @Test
    void bataComTamanhoXL() {
        String nome = "BATA PINK FUNDAMENTAL TAM XL";
        assertEquals(TipoPeca.BATA, classificador.classificarTipo(nome));
        assertEquals("XL", classificador.extrairTamanho(nome));
    }

    @Test
    void bermudaSemTamanho() {
        String nome = "BERMUDA PLUS ESTAMP";
        assertEquals(TipoPeca.BERMUDA, classificador.classificarTipo(nome));
        assertNull(classificador.extrairTamanho(nome));
    }

    @Test
    void brincoEhAcessorio() {
        assertEquals(TipoPeca.ACESSORIO, classificador.classificarTipo("BRINCO MADEIRA"));
    }

    @Test
    void argolasEhAcessorio() {
        assertEquals(TipoPeca.ACESSORIO, classificador.classificarTipo("ARGOLAS PRATA"));
    }

    @Test
    void nomeDesconhecidoViraOutro() {
        assertEquals(TipoPeca.OUTRO, classificador.classificarTipo("AGRIDOCE SAIDA AZUL"));
    }

    @Test
    void acentoEMinusculaNaoAtrapalham() {
        String nome = "Calça jeans tam 38";
        assertEquals(TipoPeca.CALCA, classificador.classificarTipo(nome));
        assertEquals("38", classificador.extrairTamanho(nome));
    }

    @Test
    void primeiraPalavraReconhecidaDefineOTipo() {
        String nome = "JAQUETA JEANS TAM. G";
        assertEquals(TipoPeca.JAQUETA, classificador.classificarTipo(nome));
        assertEquals("G", classificador.extrairTamanho(nome));
    }

    @Test
    void calcadoNaoViraCalca() {
        assertEquals(TipoPeca.OUTRO, classificador.classificarTipo("CALCADOS DIVERSOS"));
    }

    @Test
    void nomeVazioViraOutro() {
        assertEquals(TipoPeca.OUTRO, classificador.classificarTipo(null));
        assertNull(classificador.extrairTamanho(null));
    }
}