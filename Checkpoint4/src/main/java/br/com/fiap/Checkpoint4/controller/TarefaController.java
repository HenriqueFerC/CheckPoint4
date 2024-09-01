package br.com.fiap.Checkpoint4.controller;

import br.com.fiap.Checkpoint4.dto.tarefa.*;
import br.com.fiap.Checkpoint4.model.Tarefa;
import br.com.fiap.Checkpoint4.repository.TarefaRepository;
import br.com.fiap.Checkpoint4.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("tasks")
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesTarefaDto> atualizarTarefa(@PathVariable("id") Long id, @RequestBody @Valid AtualizarTarefaDto tarefaDto){
        try {
            var tarefa = tarefaRepository.getReferenceById(id);
            tarefa.atualizarTarefa(tarefaDto);
            return ResponseEntity.ok(new DetalhesTarefaDto(tarefa));
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("{id}/user/{idUser}")
    @Transactional
    public ResponseEntity<Void> deletarTarefa(@PathVariable("id") Long id, @PathVariable("idUser") Long idUser){
        try {
            var tarefa = tarefaRepository.getReferenceById(id);
            var usuario = usuarioRepository.getReferenceById(idUser);
            usuario.removerTarefa(tarefa);
            tarefaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<ListagemTarefaDto>> listarTarefa(Pageable pageable){
        var lista = tarefaRepository.findAll(pageable).stream().map(ListagemTarefaDto::new).toList();
        if(lista.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }
}
