package com.listatarefas.controllers;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.listatarefas.models.Enum.Status;
import com.listatarefas.models.Tarefa;
import com.listatarefas.repositories.TarefaRepository;

@Controller
public class TarefaController {

	@Autowired
	TarefaRepository tr;
	
	@RequestMapping(path="/")
	public String index() {
		return "redirect:/tarefas";
	}
	
	@RequestMapping(path="/tarefas")
	public ModelAndView tarefas() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Tarefa> tarefasafazer = tr.buscaTarefas("AFAZER");
		Iterable<Tarefa> tarefasconcluidas = tr.buscaTarefas("CONCLUIDA");
		mv.addObject("tarefasafazer", tarefasafazer);
		mv.addObject("tarefasconcluidas", tarefasconcluidas);
		return mv;
	}
	
	@RequestMapping(path="/adicionar", method = RequestMethod.GET)
	public String formAdd() {
		return "formAdicionar";
	}
	
	@RequestMapping(path="/adicionar", method = RequestMethod.POST)
	public String formAdd(Tarefa tarefa) {
		
		tarefa.setData_criacao(Calendar.getInstance().getTime());
		tr.save(tarefa);
		
		return "redirect:/tarefas";
	}
	
	@RequestMapping(path="/finalizar")
	public String finalizar(@RequestParam("id") Long id, ModelMap model) {
		
		Tarefa tarefa = tr.getOne(id);
		tarefa.setStatus(Status.CONCLUIDA);
		tarefa.setData_conclusao(Calendar.getInstance().getTime());
		tr.saveAndFlush(tarefa);
		return "redirect:/tarefas";
	}
	
}
