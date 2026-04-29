package com.guiverme.meu_backlog_games;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<Categoria> listarTodos() {
        return repository.findAll();
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Categoria cadastrar(Categoria novaCategoria) {
        return repository.save(novaCategoria);
    }

    public Optional<Categoria> atualizar(Long id, Categoria categoriaAtualizada) {
        return repository.findById(id).map(categoria -> {
            categoria.setNome(categoriaAtualizada.getNome());

            return repository.save(categoria);
        });
    }

    public boolean deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
