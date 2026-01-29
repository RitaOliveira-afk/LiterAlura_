package dev.rita.LiterAlura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivrosDto(
        @JsonAlias("title") String titulo,
        @JsonAlias ("authors") List<AutorDto> autor,
        @JsonAlias("languages")List<String> idioma,
        @JsonAlias("download_count") Double numerosDeDownloads
        )
{}