package com.joenpaco.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joenpaco.models.dtos.ClienteDto;
import com.joenpaco.models.entities.Cliente;
import com.joenpaco.services.ClienteService;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private Cliente cliente;
	
	@GetMapping(value = {"", "/", "/listar"})
	public String listar(Model model) {
		
		model.addAttribute("titulo", "listado de clientes");
		
		model.addAttribute("clientes", clienteService.findAll());
		
		return "listar";
		
	}
	
	@GetMapping(value = {"/form"})
	public String crear(Model model) {
		
		model.addAttribute("cliente", cliente);
		
		model.addAttribute("titulo", "Formulario de Cliente");
		
		return "form";
	}
	
	@GetMapping(value = {"/form/{id}"})
	public String editar(@PathVariable Long id, Model model) {
		
		ClienteDto clienteDto = clienteService.findById(Long.toString(id));
		
		Cliente cliente = new ObjectMapper().convertValue(clienteDto, Cliente.class);
		
		model.addAttribute("cliente", cliente);
		
		model.addAttribute("titulo", "Editar Cliente");
		
		return "form";
		
	}
	
	@PostMapping(value = {"/form"})
	public String addNewCliente(Cliente cliente) {
		
		clienteService.addNewCliente(cliente);
		
		return "redirect:listar";
	}
	
	@GetMapping(value = {"/eliminar/{id}"})
	public String eliminar(@PathVariable Long id, Model model) {
		
		clienteService.deleteById(id);
		
		return "redirect:/listar";
		
	}

}
