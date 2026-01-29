package dev.rita.LiterAlura.principal;
import dev.rita.LiterAlura.dto.LivrosDto;
import dev.rita.LiterAlura.dto.ResultadoDto;
import dev.rita.LiterAlura.model.Autor;
import dev.rita.LiterAlura.model.Livros;
import dev.rita.LiterAlura.repository.AutorRepository;
import dev.rita.LiterAlura.repository.LivroRepository;
import dev.rita.LiterAlura.service.ApiConsumer;
import dev.rita.LiterAlura.service.ConverterDados;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private final ApiConsumer consumer = new ApiConsumer();
    private final ConverterDados converte = new ConverterDados();
    private final Scanner leitura = new Scanner(System.in);
    private String json;
    private static final String URL_BASE ="https://gutendex.com/books/";


    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu () {
            var opcao = -1;
            while (opcao != 0) {
                var menu =

                        """     
                                ******************
                                    LiterAlura
                                ******************
                                
                                Escolha a sua opção:
                                
                                1-Buscar Livro pelo Título
                                2-Listar livros registrados
                                3-Listar autores registrados
                                4-Listar autores vivos em um determinado ano
                                5-Listar livros em um determinado idioma
                                
                                0-Sair
                                
                                Digite a opção desejada:
                                
                                """;
                System.out.println(menu);
                try {
                    opcao = leitura.nextInt();
                    leitura.nextLine();
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Opção inválida! Digite novamente.");
                    leitura.nextLine();
                    continue;
                }


                switch (opcao) {
                    case 1:
                        buscarLivro();
                        break;
                    case 2:
                        listarLivros();
                        break;
                    case 3:
                        listarAutores();
                        break;
                    case 4:
                        listarAutoresVivos();
                        break;
                    case 5:
                        listarIdiomas();
                        break;
                    case 0:
                        System.out.println("Encerrando a aplicação!");
                        break;
                    default:
                        System.out.println("Opção inválida!");

                }
            }
        }

    private void buscarLivro() {

        System.out.println("Infome o titulo do livro: ");
        String titulo = leitura.nextLine();
        json = consumer.obterDados(URL_BASE + "?search=" + URLEncoder.encode(titulo, StandardCharsets.UTF_8));
        LivrosDto livrosDto = buscarLivrosDto(titulo);
        System.out.println(livrosDto);
    }

    private LivrosDto buscarLivrosDto(String titulo) {
        ResultadoDto dados = converte.obterDados(json, ResultadoDto.class);
        LivrosDto livrosDto = dados.resultados().stream()
                .filter(livros -> livros.titulo().toUpperCase().contains(titulo.toUpperCase()))
                .findFirst().orElse(null);
        return livrosDto;
    }

    private void listarLivros() {
        List <Livros> livros = livroRepository.findAll();
        livros.forEach(System.out::println);
    }

    private void listarAutores() {
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivos() {
        System.out.println("Insira o ano da sua pesquisa: ");
        var ano = leitura.nextInt();
        leitura.nextLine();
        List<Autor> autores = autorRepository.autoresVivosEmDeterminadoAno(ano);;
        autores.forEach(System.out::println);
    }

    private void listarIdiomas() {
        System.out.println("Insira o idioma de busca (pt, en, fr): ");
        var idioma =leitura.nextLine();
        List<Livros>livros = livroRepository.findByIdioma(idioma);
        livros.forEach(System.out::println);
    }
}
