package service;

import entity.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.LivroRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    public Livro create(Livro livro){
        return livroRepository.save(livro);
    }

    public Optional<Livro> findById(Long id){
        return livroRepository.findById(id);
    }

    public void deleteById(Long id){
        livroRepository.deleteById(id);
    }

    public Livro update(Long id, Livro livro){

        Livro novoLivro = livroRepository.findById(id).get();

        novoLivro.setAutor(livro.getAutor());
        novoLivro.setTitulo(livro.getTitulo());
        novoLivro.setAnoPublicacao(livro.getAnoPublicacao());

        return livroRepository.save(novoLivro);
    }
}
