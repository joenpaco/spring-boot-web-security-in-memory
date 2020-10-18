package com.joenpaco.errors;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {
	
	@ExceptionHandler(ArithmeticException.class)
	public String arithmeticException(Model model, ArithmeticException e) {
		
		model.addAttribute("error", "Error de Aritmética");
		model.addAttribute("message", e.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", new Date());
		
		return "error/aritmetic";
		
	}
	
	@ExceptionHandler(NumberFormatException.class)
	public String numberFormatException(Model model, NumberFormatException e) {
		
		model.addAttribute("error", "Error: Formato de número incorrecto");
		model.addAttribute("message", e.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", new Date());
		
		return "error/number-format-exception";
		
	}
	
	@ExceptionHandler(ClienteNotFoundException.class)
	public String clienteNotFoundException(Model model, ClienteNotFoundException e) {
		
		model.addAttribute("error", "Error: Cliente no encontrado");
		model.addAttribute("message", e.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", new Date());
		
		return "error/cliente-not-found-exception";
		
	}
	

}
