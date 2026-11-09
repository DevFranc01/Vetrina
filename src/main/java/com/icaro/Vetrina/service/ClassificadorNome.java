package com.icaro.Vetrina.service;

import com.icaro.Vetrina.model.TipoPeca;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassificadorNome {

    private static final Map<String, TipoPeca> PALAVRAS = new HashMap<>();
    private static final Pattern PADRAO_TAMANHO = Pattern.compile("\\bTAM\\.?\\s*([A-Z0-9]+)\\b");

    static {
        PALAVRAS.put("CALCA", TipoPeca.CALCA);
        PALAVRAS.put("CALCAS", TipoPeca.CALCA);
        PALAVRAS.put("BERMUDA", TipoPeca.BERMUDA);
        PALAVRAS.put("SHORT", TipoPeca.SHORTS);
        PALAVRAS.put("SHORTS", TipoPeca.SHORTS);
        PALAVRAS.put("SAIA", TipoPeca.SAIA);
        PALAVRAS.put("VESTIDO", TipoPeca.VESTIDO);
        PALAVRAS.put("MACACAO", TipoPeca.MACACAO);
        PALAVRAS.put("MACAQUINHO", TipoPeca.MACACAO);
        PALAVRAS.put("BLUSA", TipoPeca.BLUSA);
        PALAVRAS.put("BATA", TipoPeca.BATA);
        PALAVRAS.put("CAMISETA", TipoPeca.CAMISETA);
        PALAVRAS.put("CAMISA", TipoPeca.CAMISA);
        PALAVRAS.put("REGATA", TipoPeca.REGATA);
        PALAVRAS.put("CROPPED", TipoPeca.CROPPED);
        PALAVRAS.put("BODY", TipoPeca.BODY);
        PALAVRAS.put("JAQUETA", TipoPeca.JAQUETA);
        PALAVRAS.put("CASACO", TipoPeca.CASACO);
        PALAVRAS.put("BLAZER", TipoPeca.CASACO);
        PALAVRAS.put("CARDIGAN", TipoPeca.CASACO);
        PALAVRAS.put("TENIS", TipoPeca.CALCADO);
        PALAVRAS.put("SANDALIA", TipoPeca.CALCADO);
        PALAVRAS.put("RASTEIRINHA", TipoPeca.CALCADO);
        PALAVRAS.put("SAPATILHA", TipoPeca.CALCADO);
        PALAVRAS.put("ALPARGATA", TipoPeca.CALCADO);
        PALAVRAS.put("ANABELA", TipoPeca.CALCADO);
        PALAVRAS.put("BOTA", TipoPeca.CALCADO);
        PALAVRAS.put("BRINCO", TipoPeca.ACESSORIO);
        PALAVRAS.put("ARGOLA", TipoPeca.ACESSORIO);
        PALAVRAS.put("ARGOLAS", TipoPeca.ACESSORIO);
        PALAVRAS.put("COLAR", TipoPeca.ACESSORIO);
        PALAVRAS.put("PULSEIRA", TipoPeca.ACESSORIO);
        PALAVRAS.put("ANEL", TipoPeca.ACESSORIO);
        PALAVRAS.put("BOLSA", TipoPeca.ACESSORIO);
        PALAVRAS.put("CINTO", TipoPeca.ACESSORIO);
    }

    public TipoPeca classificarTipo(String nome) {
        String texto = normalizar(nome);
        for (String palavra : texto.split("[^A-Z0-9]+")) {
            TipoPeca tipo = PALAVRAS.get(palavra);
            if (tipo != null) {
                return tipo;
            }
        }
        return TipoPeca.OUTRO;
    }

    public String extrairTamanho(String nome) {
        Matcher matcher = PADRAO_TAMANHO.matcher(normalizar(nome));
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private String normalizar(String nome) {
        if (nome == null) {
            return "";
        }
        String semAcento = Normalizer.normalize(nome, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        return semAcento.toUpperCase();
    }
}