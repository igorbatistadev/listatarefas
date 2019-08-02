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
	public String formAdd(ModelMap model) {
		
		model.addAttribute("edit", false);
		
		return "formTarefa";
	}
	
	@RequestMapping(path="/adicionar", method = RequestMethod.POST)
	public String formAdd(Tarefa tarefa) {
		
		tarefa.setData_criacao(Calendar.getInstance().getTime());
		tr.save(tarefa);
		
		return "redirect:/tarefas";
	}
	
	@RequestMapping(path="/editar")
	public String editar(@RequestParam("id") Long id, ModelMap model) {
		
		Tarefa tarefa = tr.getOne(id);
		if(tarefa.getStatus().equals(Status.CONCLUIDA)){
			return "redirect:/tarefas";
		} else {
			model.addAttribute("tarefa", tarefa);
			model.addAttribute("edit", true);
			
			return "formTarefa";
		}	
	}
	
	@RequestMapping(path="/editar", method = RequestMethod.POST)
	public String atualizar(Tarefa tarefa) {
		
		tr.saveAndFlush(tarefa);
		
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
	
	@RequestMapping(path="/deletar")
	public String deletarTarefa(@RequestParam("id") Long id) {
		tr.deleteById(id);	
		return "redirect:/tarefas";
	}
}
