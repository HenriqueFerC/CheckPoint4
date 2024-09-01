package br.com.fiap.Checkpoint4.dto.tarefa;

import br.com.fiap.Checkpoint4.model.Status;
import br.com.fiap.Checkpoint4.model.Tarefa;

import java.time.LocalDate;

public record ListagemTarefaDto(String titulo, String descricao,
                                LocalDate dataPrevista, Status status) {
    public ListagemTarefaDto(Tarefa tarefa){
        this(tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getDataPrevista(), tarefa.getStatus());
    }
}
