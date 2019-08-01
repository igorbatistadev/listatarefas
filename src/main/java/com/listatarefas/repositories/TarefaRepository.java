package com.listatarefas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.listatarefas.models.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

}
