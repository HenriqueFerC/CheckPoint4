package br.com.fiap.Checkpoint4.model;

import br.com.fiap.Checkpoint4.dto.tarefa.AtualizarTarefaDto;
import br.com.fiap.Checkpoint4.dto.tarefa.CadastrarTarefaDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter@Setter@NoArgsConstructor

@Entity
@Table(name = "TB_TAREFA")
public class Tarefa {

    @Id
    @GeneratedValue
    @Column(name = "ID_TAREFA")
    private Long id;


    @Column(name = "TITULO_TAREFA", nullable = false, length = 30)
    private String titulo;

    @Column(name = "DESCRICAO_TAREFA", nullable = false)
    private String descricao;

    @Column(name = "DATA_DE_CONCLUSAO", nullable = false)
    private LocalDate dataPrevista;

    @Column(name = "STATUS_TAREFA", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    public Tarefa(CadastrarTarefaDto tarefaDto, Usuario usuario){
        titulo = tarefaDto.titulo();
        descricao = tarefaDto.descricao();
        dataPrevista = tarefaDto.dataPrevista();
        status = tarefaDto.status();
        this.usuario = usuario;
    }

    public void atualizarTarefa(AtualizarTarefaDto tarefaDto){
        titulo = tarefaDto.titulo();
        descricao = tarefaDto.descricao();
        dataPrevista = tarefaDto.dataPrevista();
        status = tarefaDto.status();
    }

}
