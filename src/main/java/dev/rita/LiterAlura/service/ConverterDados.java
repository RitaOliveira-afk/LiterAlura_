package dev.rita.LiterAlura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverterDados implements IConverterDados {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        if (json == null || json.isBlank()) {
            throw new RuntimeException("JSON vazio ou nulo recebido da API");
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, classe);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter JSON para " + classe.getSimpleName(), e);
        }
    }
}
