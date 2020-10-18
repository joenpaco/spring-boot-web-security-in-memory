package com.joenpaco.services;

import java.util.List;

import com.joenpaco.models.dtos.ClienteDto;
import com.joenpaco.models.entities.Cliente;

public interface ClienteService {
	
	public abstract List<ClienteDto> findAll();
	
	public abstract ClienteDto findById(String id);
	
	public abstract void addNewCliente(Cliente cliente);
	
	public abstract void deleteById(Long id);

}
