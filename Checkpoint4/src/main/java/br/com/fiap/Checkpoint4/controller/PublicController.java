package br.com.fiap.Checkpoint4.controller;

import br.com.fiap.Checkpoint4.dto.tarefa.ListagemTarefaPublicDto;
import br.com.fiap.Checkpoint4.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("public")
public class PublicController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @GetMapping
    public ResponseEntity<List<ListagemTarefaPublicDto>> listarTarefa(Pageable pageable){
        var lista = tarefaRepository.findAll(pageable).stream().map(ListagemTarefaPublicDto::new).toList();
        if(lista.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

}
