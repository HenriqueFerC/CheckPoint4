package br.com.fiap.Checkpoint4.dto.tarefa;

import br.com.fiap.Checkpoint4.model.Status;
import br.com.fiap.Checkpoint4.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CadastrarTarefaDto(
        @NotBlank(message = "Título não pode estar em branco!")
        String titulo,
        @NotBlank(message = "Descrição não pode estar em branco!")
        String descricao,
        @NotNull(message = "Data Prevista para Conclusão não pode estar nula!")
        LocalDate dataPrevista,
        @NotEmpty(message = "Status não pode estar vazio!")
        Status status
) {
}
