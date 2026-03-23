package com.example.demo.controller;

import com.example.demo.entity.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.LivroService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping
    public ResponseEntity<Livro> save(@RequestBody Livro novoLivro){
        Livro livro = livroService.save(novoLivro);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<Livro>> findAll(){
        List<Livro> livrosList = livroService.findAll();
        return livrosList.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok().body(livrosList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Long id){
        Optional<Livro> livroAchado = livroService.findById(id);

        return livroAchado.isPresent()
                ? ResponseEntity.ok().body(livroAchado.get())
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> update(@PathVariable Long id, @RequestBody Livro livroAtualizado){
        Livro livro = livroService.update(id, livroAtualizado);
        return ResponseEntity.ok(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        livroService.deleteById(id);
        return ResponseEntity.status(204).build();
    }
}
