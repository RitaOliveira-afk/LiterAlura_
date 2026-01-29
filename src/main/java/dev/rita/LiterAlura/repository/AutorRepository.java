package dev.rita.LiterAlura.repository;

import dev.rita.LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository <Autor, Long> {
    Optional<Autor> findByNome(String autor);
    @Query("""
    SELECT a FROM Autor a
    WHERE a.anoNascimento <= :ano
    AND (a.anoFalecimento IS NULL OR a.anoFalecimento > :ano)
""")
    List<Autor> autoresVivosEmDeterminadoAno(@Param("ano")int ano);
}
