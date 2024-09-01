package br.com.fiap.Checkpoint4.repository;

import br.com.fiap.Checkpoint4.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{
}
