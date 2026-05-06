package com.guiverme.meu_backlog_games.service;

import com.guiverme.meu_backlog_games.entity.Categoria;
import com.guiverme.meu_backlog_games.repository.CategoriaRepository;
import com.guiverme.meu_backlog_games.dto.CategoriaRequestDTO;
import com.guiverme.meu_backlog_games.dto.CategoriaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<CategoriaResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public Optional<CategoriaResponseDTO> buscarPorId(Long id) {
        return repository.findById(id).map(this::converterParaResponseDTO);
    }

    public CategoriaResponseDTO cadastrar(CategoriaRequestDTO dto) {
        Categoria categoria = converterParaEntidade(dto);
        Categoria salva = repository.save(categoria);
        return converterParaResponseDTO(salva);
    }

    public Optional<CategoriaResponseDTO> atualizar(Long id, CategoriaRequestDTO dto) {
        return repository.findById(id).map(categoriaExistente -> {
            categoriaExistente.setNome(dto.getNome());
            Categoria atualizada = repository.save(categoriaExistente);

            return converterParaResponseDTO(atualizada);
        });
    }

    public boolean deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }


    private Categoria converterParaEntidade(CategoriaRequestDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        return categoria;
    }

    private CategoriaResponseDTO converterParaResponseDTO(Categoria categoria) {
        return new CategoriaResponseDTO(categoria.getId(), categoria.getNome());
    }
}