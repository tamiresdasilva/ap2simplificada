package br.com.ulbra.ap2simplificada.services;

import br.com.ulbra.ap2simplificada.model.Livro;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class LivroService {
    List<Livro> livros = new ArrayList<>();

    public LivroService() {
        livros.add(new Livro(1, "O Pequeno Príncipe", "Antoine de Saint-Exupéry", "Agir", 96, 2015));
        livros.add(new Livro(2, "Dom Casmurro", "Machado de Assis", "Ática", 216, 1997));
        livros.add(new Livro(3, "A Divina Comédia", "Dante Alighieri", "Garnier", 814, 2022));
        livros.add(new Livro(4, "Mrs. Dalloway", "Virginia Woolf", "Penguin-Companhia", 240, 2017));
    }

    public Livro cadastrar(Livro livro){
        this.livros.add(livro);

        return livro;
    }

    public List<Livro> listarTodos(){
        return livros;
    }

    public Livro consultarPorId(int id){
        return livros.stream()
                .filter(l -> l.getId() == id)
                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "O livro com o ID " + id + " não foi encontrado."));
    }


    public Livro atualizar(Livro livro, int id){
        Livro livroFiltrado = livros.stream()
                .filter(l -> l.getId() == id)
                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "O livro com o ID " + id + " não foi encontrado."));

        livro.setId(livroFiltrado.getId());

        int indice = livros.indexOf(livroFiltrado);

        livros.set(indice, livro);

        return livro;
    }

    public void deletar(int id){
        Livro livroFiltrado = livros.stream()
                .filter(l -> l.getId() == id)
                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "O livro com o ID " + id + " não foi encontrado."));

        livros.remove(livroFiltrado);
    }
}
