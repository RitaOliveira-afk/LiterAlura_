package dev.rita.LiterAlura.model;
import dev.rita.LiterAlura.dto.LivrosDto;
import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;
    private String idioma;
    private Double numerosDeDownloads;

    @ManyToOne
    private Autor autor;
    public Livros(){}
    public Livros(LivrosDto livrosDto, Autor autor){
        this.titulo = livrosDto.titulo();
        this.idioma = !livrosDto.idioma().isEmpty()? livrosDto.idioma().get(0):null ;
        this.numerosDeDownloads = livrosDto.numerosDeDownloads();
        this.autor = autor;

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Double getNumerosDeDownloads() {
        return numerosDeDownloads;
    }

    public void setNumerosDeDownloads(Double numerosDeDownloads) {
        this.numerosDeDownloads = numerosDeDownloads;
    }

    @Override
    public String toString() {
        return "Livros{" +
                "Titulo= ' " + titulo + '\'' +
                "Idioma= ' " + idioma + '\'' +
                "Número de Downloads = " + numerosDeDownloads +
                "Nome= " +
                '}';
    }
}
