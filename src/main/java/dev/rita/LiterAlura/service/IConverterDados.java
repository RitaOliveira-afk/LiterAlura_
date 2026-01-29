package dev.rita.LiterAlura.service;

public interface IConverterDados {
    <T> T obterDados(String json, Class<T> classe);
}
