package br.com.fiap.Checkpoint4.dto.tarefa;

import br.com.fiap.Checkpoint4.model.Status;
import br.com.fiap.Checkpoint4.model.Tarefa;

public record ListagemTarefaPublicDto(String titulo, Status status) {

    public ListagemTarefaPublicDto(Tarefa tarefa){
        this(tarefa.getTitulo(), tarefa.getStatus());
    }
}
