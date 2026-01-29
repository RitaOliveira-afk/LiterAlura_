package dev.rita.LiterAlura.repository;

import dev.rita.LiterAlura.model.Livros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository <Livros, Long> {
    List<Livros> findByIdioma(String idioma);
}
