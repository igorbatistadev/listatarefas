package com.listatarefas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TarefaController {

	@RequestMapping(path="/")
	public String index() {
		return "index";
	}
	
}
