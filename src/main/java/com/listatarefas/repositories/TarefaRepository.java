package com.listatarefas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.listatarefas.models.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

	@Query(value = "select t.* from tarefa t where t.status = ?1", nativeQuery = true)
	Iterable<Tarefa> buscaTarefas(String status);

}
