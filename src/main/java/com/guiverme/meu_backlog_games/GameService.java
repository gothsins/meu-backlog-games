package com.guiverme.meu_backlog_games;


import com.guiverme.meu_backlog_games.dto.GameRequestDTO;
import com.guiverme.meu_backlog_games.dto.GameResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<GameResponseDTO> listarTodos() {

        List<Game> todosOsJogos = repository.findAll();
        return todosOsJogos.stream()
                .map(game -> converterParaResponseDTO(game))
                .toList();
    }

    public List<GameResponseDTO> buscarPorCategoria(String categoria) {

        List<Game> jogos = repository.findByCategoriaNomeIgnoreCase(categoria);

        return jogos.stream()
                .map(game -> converterParaResponseDTO(game))
                .toList();
    }

    public GameResponseDTO cadastrar(GameRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));

        Game jogoParaSalvar = converterParaEntidade(dto, categoria);
        Game jogoSalvo = repository.save(jogoParaSalvar);

        return converterParaResponseDTO(jogoSalvo);
    }

    public Optional<GameResponseDTO> atualizar(Long id, GameRequestDTO jogoAtualizado) {
        return repository.findById(id).map(gameAntigo -> {
            gameAntigo.setTitulo(jogoAtualizado.getTitulo());
            gameAntigo.setPlataforma(jogoAtualizado.getPlataforma());
            gameAntigo.setNota(jogoAtualizado.getNota());
            gameAntigo.setStatus(jogoAtualizado.getStatus());
            gameAntigo.setDataLancamento(jogoAtualizado.getDataLancamento());

            if (jogoAtualizado.getCategoriaId() != null) {
                Categoria novaCategoria = categoriaRepository.findById(jogoAtualizado.getCategoriaId())
                        .orElseThrow(() -> new RuntimeException("Nova categoria não encontrada!"));
                gameAntigo.setCategoria(novaCategoria);
            }

            Game jogoSalvo = repository.save(gameAntigo);

            return converterParaResponseDTO(jogoSalvo);
        });
    }

    public boolean deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<GameResponseDTO> buscarPorId(Long id) {
        return repository.findById(id).map(game -> converterParaResponseDTO(game));}

    public List<GameResponseDTO> pesquisarJogos(String titulo, String categoriaNome, Long categoriaID, String plataforma) {

        List<Game> jogosEncontrados = null;

        if (titulo != null && !titulo.isEmpty()) {
            jogosEncontrados = repository.findByTituloContainingIgnoreCase(titulo);
        }
        else if (categoriaNome != null && !categoriaNome.isEmpty()) {
            jogosEncontrados = repository.findByCategoriaNomeIgnoreCase(categoriaNome);
        }
        else if (categoriaID != null) {
            jogosEncontrados = repository.findByCategoriaId(categoriaID);
        }
        else if (plataforma != null && !plataforma.isEmpty()) {
            jogosEncontrados = repository.findByPlataformaIgnoreCase(plataforma);
        }
        else {
            jogosEncontrados = repository.findAll();
        }

        return jogosEncontrados.stream()
                .map(game -> converterParaResponseDTO(game))
                .toList();
    }

    private Game converterParaEntidade(GameRequestDTO dto, Categoria categoria) {
        Game game = new Game();
        game.setTitulo(dto.getTitulo());
        game.setPlataforma(dto.getPlataforma());
        game.setNota(dto.getNota());
        game.setStatus(dto.getStatus());
        game.setDataLancamento(dto.getDataLancamento());
        game.setCategoria(categoria);
        return game;
    }

    private GameResponseDTO converterParaResponseDTO(Game game) {
        GameResponseDTO dto = new GameResponseDTO();
        dto.setId(game.getId());
        dto.setTitulo(game.getTitulo());
        dto.setPlataforma(game.getPlataforma());
        dto.setNota(game.getNota());
        dto.setStatus(game.getStatus());

        if (game.getCategoria() != null) {
            dto.setNomeCategoria(game.getCategoria().getNome());
        }

        return dto;
    }



}
